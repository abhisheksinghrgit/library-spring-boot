package com.needle.library.repository;

import com.needle.library.models.BookCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCollectionRepository extends JpaRepository<BookCollection, Long> {
    BookCollection findByIsbn(String isbn);
    List<BookCollection> findByTitleContaining(String title);
}
