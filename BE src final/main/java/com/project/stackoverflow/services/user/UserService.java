package com.project.stackoverflow.services.user;

import com.project.stackoverflow.dtos.SignupDTO;
import com.project.stackoverflow.dtos.UserDTO;
import com.project.stackoverflow.entities.User;

import java.util.List;

public interface UserService {

    UserDTO createUser(SignupDTO signupDTO);

    boolean hasUserWithEmail(String email);

    String getUserNameById(int userId);

    boolean getIsmoderatorById(Long userId);

    User banUser(Long id);

    User unbanUser(Long id);

    List<User> getAllUsers();

    boolean getIsBannedById(Long id);

    User getUserById(int authorid);
}
