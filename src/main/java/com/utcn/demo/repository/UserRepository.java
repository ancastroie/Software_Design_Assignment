package com.utcn.demo.repository;

import com.utcn.demo.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends CrudRepository<Users, Integer>{

}
