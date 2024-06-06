package com.project.stackoverflow.dtos;

import jdk.jfr.DataAmount;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnswerDTO {

    private Long id;
    private String body;
    private Long userId; // Assuming you need the user ID in the DTO
    private Long questionId; // Assuming you need the question ID in the DTO
    private LocalDateTime creationDateTime;
    private String pictureUrl;
    private int voteCount;
    private Boolean isDeleted;

    // Constructors, getters, and setters
}

