package com.project.stackoverflow.repositories;
import com.project.stackoverflow.entities.AnswerVotes;
import com.project.stackoverflow.entities.QuestionVotes;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerVoteRepository extends JpaRepository<AnswerVotes,Long> {
    List<AnswerVotes> findByAnswerid(int answerid);

    List<AnswerVotes> findByUserid(Long userid);

    Optional<AnswerVotes> findByUseridAndAnswerid(Long userid, Long answerid);
}
