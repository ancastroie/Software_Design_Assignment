package com.utcn.demo.controller;

import com.utcn.demo.entity.Users;
import com.utcn.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Users> getAllUsers() {
        List<Users> users =this.userService.retrieveUsers();
        return users;
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public Users getUserById(@PathVariable int id) {
        Users user =this.userService.retrieveUserById(id);
        return user;
    }
    @GetMapping("/getById")
    @ResponseBody
    public Users getUserByIdVar2(@RequestParam("id") int id) {
        Users user =this.userService.retrieveUserById(id);
        return user;
    }
    @PostMapping("/addUser")
    @ResponseBody
    public Users addUser(@RequestBody Users user){
        return  this.userService.addUser(user);
    }

    @PutMapping("/updateUser")
    @ResponseBody
    public Users updateUser(@RequestBody Users user){
        return  this.userService.addUser(user);
    }


    @DeleteMapping("/deleteById")
    @ResponseBody
    public String deleteUserById(@RequestParam int id){
        return this.userService.deleteUserById(id);
    }
}
