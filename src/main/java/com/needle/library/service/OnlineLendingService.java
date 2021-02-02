package com.needle.library.service;

import com.needle.library.models.BookAvailability;
import com.needle.library.models.BookItem;
import com.needle.library.models.Lending;
import com.needle.library.models.User;
import com.needle.library.repository.BookItemRepository;
import com.needle.library.repository.LendingRepository;
import com.needle.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OnlineLendingService implements LendingService {

    public final int MAX_BOOK_COUNT = 5;
    private final UserRepository userRepository;
    private final BookItemRepository bookItemRepository;
    private final LendingRepository lendingRepository;

    public OnlineLendingService(LendingRepository lendingRepository, UserRepository userRepository, BookItemRepository bookItemRepository) {
        this.userRepository = userRepository;
        this.bookItemRepository = bookItemRepository;
        this.lendingRepository = lendingRepository;

    }

    @Override
    public Lending unReserveBook(Long userId, Long lendingId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new Exception("User not found with id " + userId);
        }
        Optional<Lending> lending = lendingRepository.findById(lendingId);
        if (!lending.isPresent()) {
            throw new Exception("Lending not found with id " + lending);
        }

        Optional<BookItem> bookItem = bookItemRepository.findById(lending.get().getBookItem().getId());

        bookItem.get().setBookAvailability(BookAvailability.AVAILABE);
        user.get().setCurrentLendingBookCount(user.get().getCurrentLendingBookCount()-1);
        lending.get().setReturnDate(new Date());

        userRepository.save(user.get());
        lendingRepository.save(lending.get());
        bookItemRepository.save(bookItem.get());
        return lending.get();
    }

    @Override
    public Lending reserveBook(Long userId, String isbn) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new Exception("User not found with id " + userId);
        }
        if (user.get().getCurrentLendingBookCount() >= MAX_BOOK_COUNT) {
            throw new Exception("Can't lend more books since the limit for user has reached " + user.get().getCurrentLendingBookCount());
        }
        Optional<BookItem> bookItem = bookItemRepository.findBookItemByIsbnAndBookAvailability(isbn, BookAvailability.AVAILABE);
        if(!bookItem.isPresent()){
            throw new Exception("Book is not available, Please try in some day");
        }
        Lending lending = Lending.builder().
                bookItem(bookItem.get()).
                user(user.get()).
                dueDate(new Date()).
                build();
        user.get().setCurrentLendingBookCount(user.get().getCurrentLendingBookCount()+1);
        bookItem.get().setBookAvailability(BookAvailability.NOT_AVAILABLE);
        bookItem.get().setBorrowed(new Date());
        lendingRepository.save(lending);
        userRepository.save(user.get());
        bookItemRepository.save(bookItem.get());
        return lending;
    }
}
