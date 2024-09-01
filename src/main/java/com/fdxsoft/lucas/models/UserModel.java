package com.fdxsoft.lucas.models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String name;
    @Column(length = 100)
    private String lastname;
    @Column(length = 50)
    private String email;
    @Column(length = 10)
    private String phone;
    @Column(length = 255)
    private String password;

}
