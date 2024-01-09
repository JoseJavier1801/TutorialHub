package com.josejavier.model;


import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class user {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    @Column(name = "name",length = 256,nullable = false)
    private String name;
    @Column(name = "age",nullable = false)
    private int age;

    public user() {
        this(-1," ", -1);
    }

    public user(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
