package com.needle.library.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "user")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends Auditing {
    private String name;
    private Date dataOfBirth;

    private int currentLendingBookCount;

    @OneToOne
    private Account account;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;

}
