package com.project.stackoverflow.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.project.stackoverflow.entities.Questions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@Entity
@Data
@Table(name="answer_votes")
public class AnswerVotes {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name="userid")
    private Long userid;

    @Column(name = "answerid")
    private Long answerid;

    @Column(name = "vote_type")
    private Boolean vote_type;

    @Column(name="created_at",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime created_at;
}
