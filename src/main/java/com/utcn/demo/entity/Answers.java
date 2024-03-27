package com.utcn.demo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.dialect.function.ListaggStringAggEmulation;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name="answers")
public class Answers {
    @Id
    @Column(name="answerid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int answerid;

    @Column(name = "questionid")
    private int questionid;

    @Column(name = "authorid")
    private int authorid;

    @Column(name = "text")
    private String text;

    @Column(name="creationdatetime",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime creationdatetime;

    @Column(name="pictureurl")
    private String pictureurl;

    @Column(name="votecount")
    private int votecount;

    @Column(name="isdeleted")
    private Boolean isdeleted;

    public Answers(){

    }
    public Answers(int answerid,int questionid,int authorid,String text,LocalDateTime creationdatetime,String pictureurl,int votecount,boolean isdeleted){
        this.answerid=answerid;
        this.questionid=questionid;
        this.authorid=authorid;
        this.text=text;
        this.creationdatetime=creationdatetime;
        this.pictureurl=pictureurl;
        this.votecount=votecount;
        this.isdeleted=isdeleted;
    }

    public int getAnswerid() {
        return answerid;
    }

    public void setAnswerid(int answerid) {
        this.answerid = answerid;
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

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
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
