package com.project.stackoverflow.services.user;

import com.project.stackoverflow.dtos.SignupDTO;
import com.project.stackoverflow.dtos.UserDTO;
import com.project.stackoverflow.entities.User;
import com.project.stackoverflow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SignupDTO signupDTO) {
        User user =new User();
        user.setEmail(signupDTO.getEmail());
        user.setName(signupDTO.getName());
        user.setIsmoderator(signupDTO.isIsmoderator());
        user.setBanned(signupDTO.isBanned());
        user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
        User createdUser =userRepository.save(user);
        UserDTO createdUserDTO=new UserDTO();
        createdUserDTO.setId(createdUser.getId());
        return createdUserDTO;
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
    public String getUserNameById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(User::getName).orElse(null);
    }

    @Override
    public boolean getIsmoderatorById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(User::isIsmoderator).orElse(false);
    }

    public User banUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setBanned(true);
        return userRepository.save(user);
    }

    public User unbanUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setBanned(false);
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean getIsBannedById(Long id) {
        // Implement logic to retrieve banned status from database
        // For example:
        return userRepository.findById(id).map(User::isBanned).orElse(false);
    }

    @Override
    public User getUserById(int authorId) {
        return userRepository.findById((long) authorId).orElse(null);
    }
}