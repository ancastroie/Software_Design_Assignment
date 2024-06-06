package com.project.stackoverflow.entities;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private Double userscore=0.0;

    private boolean ismoderator;

    private boolean banned;
}
