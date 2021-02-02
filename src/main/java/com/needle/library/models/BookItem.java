package com.needle.library.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="book_item")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookItem extends Book{

    private Date borrowed;

    private Date dueDate;

    @Enumerated(value = EnumType.STRING)
    private BookAvailability bookAvailability;
}
