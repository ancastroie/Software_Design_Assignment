package com.project.stackoverflow.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="questions")
public class Questions {
    @Id
    @Column(name="questionid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionid;

    @Column(name="authorid")
    private int authorid;

    @Column(name="title")
    private String title;

    @Column(name="text")
    private String text;

    @Column(name="creationdatetime",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime creationdatetime;

    @Column(name="pictureurl")
    private String pictureurl;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "question_tags",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tags> tags;


    @Column(name="votecount")
    private int votecount;

    @Column(name="isdeleted")
    private Boolean isdeleted;

    @Transient
    private String authorName;

    @Transient
    private Double userscore;


    public Questions(){

    }

}
