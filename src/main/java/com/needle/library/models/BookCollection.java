package com.needle.library.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book_collection")
@Getter
@Setter
@Builder
@NoArgsConstructor
public class BookCollection extends Book {


}
