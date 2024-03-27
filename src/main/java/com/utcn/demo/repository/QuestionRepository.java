package com.utcn.demo.repository;
import com.utcn.demo.entity.Questions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface QuestionRepository extends CrudRepository<Questions,Integer> {
}
