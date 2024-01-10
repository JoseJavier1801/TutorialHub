package com.josejavier.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "teacher")
public class teacher extends user {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    @Column(name = "Title",length = 256,nullable = false)
    private String Title;

    @Column(name = "biography",length = 256,nullable = false)
    private String biography;

    public teacher(){

    }

    public teacher(int id, String name, String username, String password, String email, String phone, String photo, Date date, int age, String Title, String biography){
        super(id,name,username,password,email,phone,photo,date,age);
        this.Title=Title;
        this.biography=biography;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
