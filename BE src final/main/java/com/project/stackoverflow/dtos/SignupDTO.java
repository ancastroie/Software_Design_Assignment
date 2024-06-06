package com.project.stackoverflow.dtos;
import lombok.Data;
@Data
public class SignupDTO {
    private Long id;
    private String name;
    private String password;
    private String email;
    private boolean ismoderator;
    private  boolean banned;
}
