package com.utcn.demo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.dialect.function.ListaggStringAggEmulation;

import java.time.LocalDateTime;
@Entity
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
    @Lob
    private byte[] pictureurl;

    @Column(name="tagid")
    private int tagid;

    @Column(name="votecount")
    private int votecount;

    @Column(name="isdeleted")
    private Boolean isdeleted;

    public Questions(){

    }
    public Questions(int questionid,int authorid,String title,String text,LocalDateTime creationdatetime, byte[] pictureurl,int tagid, int votecount,Boolean isdeleted){
        this.questionid=questionid;
        this.authorid=authorid;
        this.title=title;
        this.text=text;
        this.creationdatetime=creationdatetime;
        this.pictureurl=pictureurl;
        this.tagid=tagid;
        this.votecount=votecount;
        this.isdeleted=isdeleted;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(LocalDateTime creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    public byte[] getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(byte[] pictureurl) {
        this.pictureurl = pictureurl;
    }

    public int getTagid() {
        return tagid;
    }

    public void setTagid(int tagid) {
        this.tagid = tagid;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public Boolean getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }
}
