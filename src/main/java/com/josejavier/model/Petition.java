package com.josejavier.model;



import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "petition")
public class Petition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "message", length = 256, nullable = false)
    private String message;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "Date",columnDefinition = "DATE")
    private LocalDate Date;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_classroom", nullable = false)
    private Classroom classroom;

    // Constructor, getters y setters

    public Petition() {
        this.id = -1;
        this.message = "";
        this.state = "";
        this.Date = LocalDate.now();
        this.client = new Client(); // Aquí puede ser User, ya que Teacher extiende User
        this.classroom = new Classroom();
    }

    public Petition(int id, String message, String state, LocalDate date, Client client, Classroom classroom) {
        this.id = id;
        this.message = message;
        this.state = state;
        this.Date = date;
        this.client = client;
        this.classroom = classroom;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        this.Date = date;
    }

    public Client getUser() {
        return client;
    }

    public void setUser(Client client) {
        this.client = client;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
