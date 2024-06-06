package com.project.stackoverflow.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "tags")
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tagid")
    private int tagid;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Questions> questions = new HashSet<>();


    public Tags() {

    }
}
