package com.josejavier.model;



import jakarta.persistence.*;



@Entity
@Table(name = "Assessment")
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "Assessment")
    private double assessment;

    @ManyToOne
    @JoinColumn(name = "Teacher_ID", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "User_ID", nullable = false)
    private Client client;

    public Assessment() {
    }

    public Assessment(String comment, double assessment, Teacher teacher, Client client) {
        this.comment = comment;
        this.assessment = assessment;
        this.teacher = teacher;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getAssessment() {
        return assessment;
    }

    public void setAssessment(double assessment) {
        this.assessment = assessment;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Client getUser() {
        return client;
    }

    public void setUser(Client client) {
        this.client = client;
    }
}
