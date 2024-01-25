package com.josejavier.model;

import jakarta.persistence.Column;
import org.locationtech.jts.geom.Point;

public class ClassroomDTO {
    private Integer id;

    private String description;

    private String type;

    private String category;


    private Double lat;
    private Double lng;


    private String direction;

    private String postalCode;

    private String province;
    private String localidad;
    private int teacherID;
    public ClassroomDTO(Integer id, String description, String type, String category, Double lat, Double lng, String direction, String postalCode, String province, String localidad,int teacherID) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.category = category;
        this.lat = lat;
        this.lng = lng;
        this.direction = direction;
        this.postalCode = postalCode;
        this.province = province;
        this.localidad = localidad;
        this.teacherID = teacherID;
    }

    public ClassroomDTO() {
    }

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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
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
    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }
}
