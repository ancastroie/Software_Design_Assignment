package com.project.stackoverflow.entities;
import com.project.stackoverflow.entities.Questions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="answers")
public class Answers {
    @Id
    @Column(name = "answerid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerid;

    @Column(name = "questionid")
    private int questionid;

    @Column(name = "authorid")
    private int authorid;

    @Column(name = "text")
    private String text;

    @Column(name = "creationdatetime", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime creationdatetime;

    @Column(name = "pictureurl")
    private String pictureurl;

    @Column(name = "votecount")
    private int votecount;

    @Column(name = "isdeleted")
    private Boolean isdeleted;

    @Transient
    private String authorName;

    public Answers() {

    }


}

