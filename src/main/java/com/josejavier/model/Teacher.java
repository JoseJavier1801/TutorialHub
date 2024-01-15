package com.josejavier.model;

import jakarta.persistence.*;


@Entity
@Table(name = "Teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Biography")
    private String biography;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User u;

    public Teacher() {

    }

    public Teacher(int id, String title, String biography, User u) {
        this.id = id;
        this.title = title;
        this.biography = biography;
        this.u = u;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
}
