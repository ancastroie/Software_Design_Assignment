package com.project.stackoverflow.repositories;

import com.project.stackoverflow.entities.QuestionVotes;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionVoteRepository extends JpaRepository<QuestionVotes,Long> {

    List<QuestionVotes> findByQuestionid(int questionId);

    List<QuestionVotes> findByUserid(Long userid);

    Optional<QuestionVotes> findByUseridAndQuestionid(Long userid, int questionid);
}
