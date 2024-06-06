package com.project.stackoverflow.dtos;
import lombok.Data;
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private boolean ismoderator;
    private boolean banned;
}
