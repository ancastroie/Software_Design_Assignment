package com.project.stackoverflow.repositories;

import com.project.stackoverflow.entities.Questions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Questions,Integer> {
    List<Questions> findByTagsNameContainingIgnoreCase(String tagName);

    List<Questions> findByTitleContainingIgnoreCase(String searchText);

    List<Questions> findByAuthorid(Long authorid);
}
