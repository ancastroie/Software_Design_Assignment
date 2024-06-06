package com.project.stackoverflow.repositories;

import com.project.stackoverflow.entities.Tags;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRespository extends CrudRepository<Tags,Integer> {
}
