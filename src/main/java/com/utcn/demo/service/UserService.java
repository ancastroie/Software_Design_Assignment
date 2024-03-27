package com.utcn.demo.service;

import com.utcn.demo.entity.Users;
import com.utcn.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<Users> retrieveUsers(){
        return (List<Users>) this.userRepository.findAll();
    }

    public Users retrieveUserById(int id){

        Optional<Users> user=this.userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }

    }
    public Users addUser(Users user){
        return this.userRepository.save(user);
    }

    public String deleteUserById(int id){
        try{
            this.userRepository.deleteById(id);
            return "Entry successfully deleted!";
        }catch (Exception e){
            return "Failed to delete entry with id:" + id;
        }
    }
}
