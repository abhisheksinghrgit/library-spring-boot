package com.needle.library.repository;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.needle.library.models.BookAvailability;
import com.needle.library.models.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookItemRepository extends JpaRepository<BookItem, Long> {
    BookItem findBookItemByIsbn(String isbn);

    List<BookItem> findBookItemByAuthors(long authorId);

    BookItem findBookItemByTitle(String title);

    Optional<BookItem> findBookItemByIsbnAndBookAvailability(String isbn, BookAvailability bookAvailability);
}
