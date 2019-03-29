package com.spring.rest.RestwithSpring.repositories;

import com.spring.rest.RestwithSpring.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer> {
}
