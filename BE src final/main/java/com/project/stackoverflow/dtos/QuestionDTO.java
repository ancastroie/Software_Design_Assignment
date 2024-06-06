package com.project.stackoverflow.dtos;

import com.project.stackoverflow.entities.Tags;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class QuestionDTO {
    private int id;
    private String title;
    private int authorid;
    private String text;
    private LocalDateTime creationDateTime;
    private String pictureUrl;
    private String tag;
    private int voteCount;
    private Boolean isDeleted;
}
