package com.josejavier.service;

import com.josejavier.DTO.ClassroomDTO;
import com.josejavier.model.Classroom;
import com.josejavier.model.Teacher;
import com.josejavier.repository.ClassroomRepository;
import com.josejavier.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public Classroom createOrUpdateClassroom(Classroom classroom) {
        Classroom newClass=null;
        try {
            // Verificar si el profesor existe antes de guardar el aula
            Integer teacherId = classroom.getTeacher() != null ? classroom.getTeacher().getId() : null;

            if (teacherId == null || getTeacherById(teacherId) == null) {
                // El profesor no existe, puedes manejarlo según tus necesidades
                throw new RuntimeException("Teacher does not exist");
            }

            newClass= classroomRepository.save(classroom);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating/updating classroom", e);
        }
        return newClass;
    }

    public void deleteClassroom(Integer id) {
        try {
            classroomRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting classroom", e);
        }
    }

    public List<Classroom> getAllClassrooms() {
        try {
            List<Classroom> classrooms = classroomRepository.findAll();
            return classrooms;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving classrooms", e);
        }
    }

    public Classroom getClassroomById(Integer id) {
        try {
            Optional<Classroom> optionalClassroom = classroomRepository.findById(id);
            return optionalClassroom.orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving classroom by ID", e);
        }
    }



    public Teacher getTeacherById(Integer teacherId) {
        return teacherRepository.findById(teacherId).orElse(null);
    }
    public List<Object[]> getUserClassroomDetails(Integer userId) {
        return classroomRepository.getUserClassroomDetails(userId);
    }
    public List<ClassroomDTO> getClassroomsByTeacherId(Integer teacherId) {
        List<Object[]> results = classroomRepository.findByTeacherId(teacherId);
        List<ClassroomDTO> classrooms = new ArrayList<>();

        for (Object[] result : results) {
            ClassroomDTO classroom = new ClassroomDTO();

            // Asignación de propiedades según el orden de la consulta SQL
            classroom.setId((Integer) result[0]);
            classroom.setDescription((String) result[1]);
            classroom.setType((String) result[2]);
            classroom.setCategory((String) result[3]);
            org.geolatte.geom.Point p = (org.geolatte.geom.Point) result[4];

            classroom.setLat(p.getPosition().getCoordinate(1)); // Latitud
            classroom.setLng(p.getPosition().getCoordinate(0)); // Longitud

            classroom.setDirection((String) result[5]);
            classroom.setPostalCode((String) result[6]);
            classroom.setProvince((String) result[7]);
            classroom.setLocalidad((String) result[8]);
            classroom.setDuration((String) result[9]);

            // Crear un objeto Teacher y asignar sus propiedades
            Teacher teacher = new Teacher();
            teacher.setId((Integer) result[10]); // Suponiendo que el índice 10 es el campo id del profesor
            teacher.setName((String) result[11]); // Suponiendo que el índice 11 es el campo name del profesor
            teacher.setPhoto((byte[]) result[12]); // Suponiendo que el índice 12 es el campo photo del profesor
            teacher.setMail((String) result[13]); // Suponiendo que el índice 13 es el campo mail del profesor
            teacher.setPhone((String) result[14]); // Suponiendo que el índice 14 es el campo phone del profesor
            teacher.setBiography((String) result[15]); // Suponiendo que el índice 15 es el campo biography del profesor
            teacher.setTitle((String) result[16]); // Suponiendo que el índice 16 es el campo title del profesor

            // Establecer el objeto Teacher en el ClassroomDTO
            classroom.setTeacher(teacher);

            // Establecer el ID del profesor en ClassroomDTO
            classroom.setTeacherID((Integer) result[10]); // Suponiendo que el índice 10 es el campo id_teacher

            // Agregar el Classroom a la lista
            classrooms.add(classroom);
        }

        // Imprimir los datos
        System.out.println("Datos devueltos por getClassroomsByTeacherId:");
        for (ClassroomDTO classroom : classrooms) {
            System.out.println(classroom);
        }

        return classrooms;
    }


    public List<ClassroomDTO> getAllClassroomDetails() {
        List<Object[]> results = classroomRepository.getallClassroomDetails();
        List<ClassroomDTO> classrooms = new ArrayList<>();

        for (Object[] result : results) {
            ClassroomDTO classroom = new ClassroomDTO();

            // Asignación de propiedades según el orden de la consulta SQL
            classroom.setId((Integer) result[0]);
            classroom.setDescription((String) result[1]);
            classroom.setType((String) result[2]);
            classroom.setCategory((String) result[3]);
            org.geolatte.geom.Point p = (org.geolatte.geom.Point) result[4];

            classroom.setLat(p.getPosition().getCoordinate(1)); // Latitud
            classroom.setLng(p.getPosition().getCoordinate(0)); // Longitud

            classroom.setDirection((String) result[5]);
            classroom.setPostalCode((String) result[6]);
            classroom.setProvince((String) result[7]);
            classroom.setLocalidad((String) result[8]);
            classroom.setDuration((String) result[9]);

            // Crear un objeto Teacher y asignar sus propiedades
            Teacher teacher = new Teacher();
            teacher.setId((Integer) result[10]); // Suponiendo que el índice 10 es el campo id_teacher
            teacher.setName((String) result[11]); // Suponiendo que el índice 11 es el campo name
            teacher.setPhoto((byte[]) result[12]); // Suponiendo que el índice 12 es el campo photo
            teacher.setMail((String) result[13]); // Suponiendo que el índice 13 es el campo mail
            teacher.setPhone((String) result[14]); // Suponiendo que el índice 14 es el campo phone
            teacher.setBiography((String) result[15]); // Suponiendo que el índice 15 es el campo biography
            teacher.setTitle((String) result[16]); // Suponiendo que el índice 16 es el campo title

            // Establecer el objeto Teacher en el ClassroomDTO
            classroom.setTeacher(teacher);

            // Establecer el ID del profesor en ClassroomDTO
            classroom.setTeacherID((Integer) result[10]); // Suponiendo que el índice 10 es el campo id_teacher

            // Agregar el Classroom a la lista
            classrooms.add(classroom);
        }
        return classrooms;
    }
    public List<ClassroomDTO> getAllClassroomSeeker(String category, String localidad, String postalCode) {
        List<Object[]> results = classroomRepository.searchClassrooms(category, localidad, postalCode);
        List<ClassroomDTO> classrooms = new ArrayList<>();

        for (Object[] result : results) {
            ClassroomDTO classroom = new ClassroomDTO();

            // Asignación de propiedades según el orden de la consulta SQL
            classroom.setId((Integer) result[0]);
            classroom.setDescription((String) result[1]);
            classroom.setType((String) result[2]);
            classroom.setCategory((String) result[3]);
            org.geolatte.geom.Point p = (org.geolatte.geom.Point) result[4];

            classroom.setLat(p.getPosition().getCoordinate(1)); // Latitud
            classroom.setLng(p.getPosition().getCoordinate(0)); // Longitud

            classroom.setDirection((String) result[5]);
            classroom.setPostalCode((String) result[6]);
            classroom.setProvince((String) result[7]);
            classroom.setLocalidad((String) result[8]);
            classroom.setDuration((String) result[9]);

            // Crear un objeto Teacher y asignar sus propiedades
            Teacher teacher = new Teacher();
            teacher.setId((Integer) result[10]); // Suponiendo que el índice 10 es el campo id_teacher
            teacher.setName((String) result[11]); // Suponiendo que el índice 11 es el campo name
            teacher.setPhoto((byte[]) result[12]); // Suponiendo que el índice 12 es el campo photo
            teacher.setMail((String) result[13]); // Suponiendo que el índice 13 es el campo mail
            teacher.setPhone((String) result[14]); // Suponiendo que el índice 14 es el campo phone
            teacher.setBiography((String) result[15]); // Suponiendo que el índice 15 es el campo biography
            teacher.setTitle((String) result[16]); // Suponiendo que el índice 16 es el campo title

            // Establecer el objeto Teacher en el ClassroomDTO
            classroom.setTeacher(teacher);

            // Establecer el ID del profesor en ClassroomDTO
            classroom.setTeacherID((Integer) result[10]); // Suponiendo que el índice 10 es el campo id_teacher

            // Agregar el Classroom a la lista
            classrooms.add(classroom);
        }
        return classrooms;
    }





}