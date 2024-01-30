package com.josejavier.service;

import com.josejavier.model.Classroom;
import com.josejavier.model.Teacher;
import com.josejavier.repository.ClassroomRepository;
import com.josejavier.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // Otros métodos según tus necesidades
}