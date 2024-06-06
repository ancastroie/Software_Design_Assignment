package com.project.stackoverflow.controllers;

import com.project.stackoverflow.entities.User;
import com.project.stackoverflow.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public String getUserNameById(@PathVariable int id) {
        return userService.getUserNameById(id);
    }

    @GetMapping("/ismoderator/{id}")
    @ResponseBody
    public boolean getIsModeratorById(@PathVariable Long id) {
        return userService.getIsmoderatorById(id);
    }
    @PostMapping("/ban/{id}")
    public ResponseEntity<?> banUser(@PathVariable Long id) {
        User user = userService.banUser(id);
        return ResponseEntity.ok().body(Map.of("message", "User banned successfully", "user", user));
    }

    @PostMapping("/unban/{id}")
    public ResponseEntity<?> unbanUser(@PathVariable Long id) {
        User user = userService.unbanUser(id);
        return ResponseEntity.ok().body(Map.of("message", "User unbanned successfully", "user", user));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/isbanned/{id}")
    public ResponseEntity<Boolean> getIsBannedById(@PathVariable Long id) {
        boolean isBanned = userService.getIsBannedById(id);
        return ResponseEntity.ok(isBanned);
    }


}
