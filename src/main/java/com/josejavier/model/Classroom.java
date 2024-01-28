package com.josejavier.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;


import java.io.Serializable;


@Entity
@Table(name = "classroom")
public class Classroom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", length = 256, nullable = false)
    private String description;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "category")
    private String category;


    @Column (name="location", columnDefinition="Geometry(Point,4326)", nullable=true)
    private Point location;


    @Column(name = "direction")
    private String direction;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "province")
    private String province;

    @Column(name = "localidad")
    private String localidad;

    @ManyToOne
    @JoinColumn(name = "id_teacher", nullable = false)
    @JsonBackReference
    private Teacher teacher;


    public Classroom(ClassroomDTO dto){
        this.id = dto.getId();
        this.description = dto.getDescription();
        this.type = dto.getType();
        this.category = dto.getCategory();

        GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);
        Point p = factory.createPoint(new Coordinate(dto.getLng(), dto.getLat()));
        this.location = p;
        this.direction= dto.getDirection();
        this.postalCode = dto.getPostalCode();
        this.province = dto.getProvince();
        this.localidad = dto.getLocalidad();
        this.teacher = new Teacher();
        this.teacher.setId(dto.getTeacherID());
    }

    public Classroom() {
        this(null, "", "", "", null, "", "", "", "", new Teacher());
    }

    public Classroom(Integer id, String description, String type, String category, Point location,
                     String direction, String postalCode, String province, String localidad, Teacher teacher) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.category = category;
        this.location = location;
        this.direction = direction;
        this.postalCode = postalCode;
        this.province = province;
        this.localidad = localidad;
        this.teacher = teacher;
    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {

        this.location = location;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
