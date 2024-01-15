package com.josejavier.model;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    @Column(name = "Photo")
    private String photo;

    @Column(name = "Name")
    private String name;

    @Column(name = "Username")
    private String username;

    @Column(name = "Mail")
    private String mail;

    @Column(name = "Password")
    private String password;

    @Column(name = "Age")
    private int age;

    @Column(name = "Date")
    private java.sql.Date date;

    @Column(name = "Phone")
    private String phone;

    public User(){

    }

    public User(int id, String photo, String name, String username, String mail, String password, int age, Date date, String phone) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.age = age;
        this.date = date;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
