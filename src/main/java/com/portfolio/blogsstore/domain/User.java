package com.portfolio.blogsstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Document(collection = "User")
public class User{

    @Id
    private Long id;

    private String username;

    private String password;

    private String firstName;
    private String lastName;

    private String email;

    private String address;

    private Set<Role> roles;
}
