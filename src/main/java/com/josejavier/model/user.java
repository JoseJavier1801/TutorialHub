package com.josejavier.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user")
public class user {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    @Column(name = "name",length = 256,nullable = false)
    private String name;
    @Column(name = "username",length = 256,nullable = false)
    private String username;
    @Column(name = "password",length = 256,nullable = false)
    private String password;
    @Column(name = "email",length = 256,nullable = false)
    private String email;
    @Column(name = "phone",length = 256,nullable = false)
    private String phone;
    @Column(name="photo",length = 256,nullable = false)
    private String photo;
    @Column(name="date",nullable = false)
    private Date date;
    @Column(name = "age",nullable = false)
    private int age;

    public user(){

    }
    public user(int id,String name,String username,String password,String email,String phone,String photo,Date date,int age){
        this.id=id;
        this.name=name;
        this.username=username;
        this.password=password;
        this.email=email;
        this.phone=phone;
        this.photo=photo;
        this.date=date;
        this.age=age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
