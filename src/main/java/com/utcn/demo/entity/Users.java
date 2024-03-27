package com.utcn.demo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.dialect.function.ListaggStringAggEmulation;

@Entity
@Table(name="users")
public class Users {
    @Id
    @Column(name="userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phonenumber")
    private String phonenumber;

    @Column(name = "userscore")
    private Double userscore;

    @Column(name = "ismoderator")
    private boolean ismoderator;

    public Users(){

    }
    public Users(int userid, String username, String password, String email,String phonenumber,Double userscore,boolean ismoderator){
        this.userid=userid;
        this.username=username;
        this.password=password;
        this.email=email;
        this.phonenumber=phonenumber;
        this.userscore=userscore;
        this.ismoderator=ismoderator;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Double getUserscore() {
        return userscore;
    }

    public void setUserscore(Double userscore) {
        this.userscore = userscore;
    }

    public boolean isIsmoderator() {
        return ismoderator;
    }

    public void setIsmoderator(boolean ismoderator) {
        this.ismoderator = ismoderator;
    }
}
