package com.needle.library;


import com.needle.library.models.*;
import com.needle.library.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initUser(UserRepository repository, AccountRepository accountRepository) {

        Account userAccount = Account.builder()
                .accountType(AccountType.USER)
                .username("naruto")
                .password("someText")
                .build();

        accountRepository.save(userAccount);

        return args -> {
            log.info("Preloading " + repository.save(User.builder()
                    .accountStatus(AccountStatus.CREATED)
                    .name("Abhishek")
                    .account(userAccount)
                    .gender(Gender.MALE)
                    .dataOfBirth(new Date())
                    .build()));
        };
    }

    @Bean
    CommandLineRunner initBooks(BookItemRepository bookItemRepository, BookCollectionRepository collectionRepository, AuthorRepository authorRepository) {

        Author author = Author.builder()
                .name("charles")
                .build();

        authorRepository.save(author);


        ArrayList<Author> authors = new ArrayList<>();
        authors.add(author);

        BookItem bookItem = BookItem.builder()
                .bookAvailability(BookAvailability.AVAILABE)
                .build();
        bookItem.setIsbn("isbn1");
        bookItem.setPublisher("Pearson");
        bookItem.setTitle(" string contains random text");
        bookItem.setAuthors(authors);

        BookCollection collection = BookCollection.builder()
                .build();
        collection.setIsbn("isbn1");
        collection.setPublisher("Pearson");
        collection.setAuthors(authors);

        collection.setTitle(" string contains random text");

        collectionRepository.save(collection);

        bookItemRepository.save(bookItem);


        return args -> {
            log.info("Preloading " + bookItemRepository.save(bookItem));
        };
    }
}