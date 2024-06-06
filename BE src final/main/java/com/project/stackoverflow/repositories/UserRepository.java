package com.project.stackoverflow.repositories;

import com.project.stackoverflow.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findFirstByEmail(String email);
    Optional<User> findById(int id);

    Optional<User> findById(Long userId);
}
