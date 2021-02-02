package com.needle.library.controllers;

import com.needle.library.models.BookCollection;
import com.needle.library.models.Lending;
import com.needle.library.repository.BookCollectionRepository;
import com.needle.library.repository.UserRepository;
import com.needle.library.service.LendingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookItemController {

    final BookCollectionRepository bookCollectionRepository;
    final UserRepository userRepository;
    final LendingService lendingService;

    public BookItemController(LendingService lendingService, BookCollectionRepository bookCollectionRepository, UserRepository userRepository) {
        this.lendingService = lendingService;
        this.bookCollectionRepository = bookCollectionRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{isbn}")
    public BookCollection getByIsbn(@PathVariable(name = "isbn") String isbn) {
        return bookCollectionRepository.findByIsbn(isbn);
    }

    @GetMapping("/title/{rawText}")
    public List<BookCollection> getBookByTitle(@PathVariable(name = "rawText") String rawText) {
        return bookCollectionRepository.findByTitleContaining(rawText);
    }

    @PostMapping("/{userId}/reserve/{isbn}")
    public Lending reserveABook(@PathVariable(name = "userId") Long userId,
                                @PathVariable(name = "isbn") String isbn) throws Exception {


        return lendingService.reserveBook(userId, isbn);

    }

    @PatchMapping("/{userId}/unreserve/{lendingId}")
    public Lending unReserveBook(@PathVariable(name = "userId") Long userId,
                                 @PathVariable(name = "lendingId") Long lendingId) throws Exception {


        return lendingService.unReserveBook(userId, lendingId);

    }


}
