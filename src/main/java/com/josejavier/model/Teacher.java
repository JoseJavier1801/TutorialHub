package com.josejavier.model;

import com.josejavier.model.User;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Teacher")
@DiscriminatorValue("Teacher")
public class Teacher extends User {

    @Column(name = "Title")
    private String title;
    @Column(name = "Biography")
    private String biography;

    public Teacher() {
    }

    public Teacher(int id, String photo, String name, String username, String mail, String password, int age, Date date, String phone, String title, String biography) {
        super(id, photo, name, username, mail, password, age, date, phone);
        this.title = title;
        this.biography = biography;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
