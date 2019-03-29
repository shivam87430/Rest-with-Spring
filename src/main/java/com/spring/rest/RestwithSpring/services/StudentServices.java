package com.spring.rest.RestwithSpring.services;

import com.spring.rest.RestwithSpring.entities.Student;
import com.spring.rest.RestwithSpring.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServices {

    @Autowired
    StudentRepository studentRepository;

    public void save(Student student){
        studentRepository.save(student);
    }

    public Student getStudentById(Integer id){
        Optional<Student> optionalStudent=studentRepository.findById(id);
        return optionalStudent.isPresent()? optionalStudent.get(): null;
    }

    public List<Student> getAllStudent(){
        return (List<Student>) studentRepository.findAll();
    }

}
