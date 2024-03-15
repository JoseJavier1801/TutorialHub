package com.josejavier.DTO;

import com.josejavier.model.Teacher;

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
    private String duration;

    private static double Price;
    private int teacherID;

    private String video;

    private Teacher teacher;

    public ClassroomDTO(Integer id, String description, String type, String category, Double lat, Double lng, String direction, String postalCode, String province, String localidad,String duration ,int teacherID,String video,Teacher teacher,double Price) {
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
        this.duration=duration;
        this.video=video;
        this.Price=Price;
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
    public void setLocation(String location) {
        // Puedes hacer cualquier procesamiento adicional aquí si es necesario
        // Por ejemplo, si location es una cadena en formato específico que necesitas analizar
        // Para este ejemplo, no necesitamos procesamiento adicional, simplemente establecemos lat y lng

        // Supongamos que location es una cadena en formato "latitud,longitud"
        String[] parts = location.split(",");
        if (parts.length == 2) {
            this.lat = (Double) Double.parseDouble(parts[0]);
            this.lng = (Double) Double.parseDouble(parts[1]);
        } else {
            // Manejo de errores si la cadena no está en el formato esperado
            // Puedes lanzar una excepción o realizar otra acción apropiada aquí
        }
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher=teacher;
    }
    public Teacher getTeacher() {
        return teacher;
    }

    public static String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public static double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }


    @Override
    public String toString() {
        return "ClassroomDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", direction='" + direction + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", province='" + province + '\'' +
                ", localidad='" + localidad + '\'' +
                ", duration='" + duration + '\'' +
                ", teacherID=" + teacherID +
                ", video=" + video +
                ", teacher=" + teacher +
                '}';
    }
}