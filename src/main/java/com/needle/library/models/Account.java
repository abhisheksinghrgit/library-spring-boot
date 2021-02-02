package com.needle.library.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "account")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account extends Auditing  {
    private String username;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;
}
