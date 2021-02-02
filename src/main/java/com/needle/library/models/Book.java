package com.needle.library.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
@Getter
@Setter
public abstract class Book extends Auditing {
    private String title;
    private String publisher;
    private String language;
    private String subject;

    @Column(unique = true)
    private String isbn;
    @ManyToMany
    private List<Author> authors;
}
