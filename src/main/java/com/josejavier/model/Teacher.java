package com.josejavier.model;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher extends Client {

    @Column(name = "title")
    private String title;
    @Column(name = "biography")
    private String biography;

    @OneToMany(mappedBy = "teacher")
    private List<Classroom> classrooms;

    @OneToMany(mappedBy = "teacher")
    private List<Assessment> assessments;


    public Teacher() {
    }

    public Teacher(int id, String photo, String name, String username, String mail, String password, int age, LocalDate date, String phone, String title, String biography) {
        super(id, photo, name, username, mail, password, date, phone);
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
