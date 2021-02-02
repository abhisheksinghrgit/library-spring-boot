package com.needle.library.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "librarian")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Librarian extends Auditing {
    private String name;

    @OneToOne
    private Account account;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
