package com.needle.library.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lending")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lending extends Auditing {

    @Temporal(value = TemporalType.DATE)
    private Date returnDate;

    @Temporal(value = TemporalType.DATE)
    private Date dueDate;


    @OneToOne
    private BookItem bookItem;
    @ManyToOne
    private User user;
}
