package com.josejavier.service;

import com.josejavier.model.Classroom;
import com.josejavier.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    public List<Object[]> getUserClassroomDetails(Integer userId) {
        return classroomRepository.getUserClassroomDetails(userId);
    }

    public List<Object[]> searchClassrooms(String keyword) {
        return classroomRepository.searchClassrooms(keyword);
    }

    public Classroom createOrUpdateClassroom(Classroom classroom) {

        return classroomRepository.save(classroom);
    }

    public void deleteClassroom(Integer id) {
        classroomRepository.deleteById(id);
    }

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }
}
