package com.utcn.demo.repository;
import com.utcn.demo.entity.Answers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AnswerRepository extends CrudRepository<Answers,Integer>{

}
