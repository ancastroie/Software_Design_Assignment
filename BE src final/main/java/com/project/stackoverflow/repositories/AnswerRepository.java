package com.project.stackoverflow.repositories;

import com.project.stackoverflow.entities.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answers,Long> {
    List<Answers> findByQuestionid(Long questionid);

    void deleteByQuestionid(int questionid);
}
