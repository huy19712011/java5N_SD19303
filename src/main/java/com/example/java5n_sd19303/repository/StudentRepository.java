package com.example.java5n_sd19303.repository;

import com.example.java5n_sd19303.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private final EntityManager entityManager;

    public StudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Student> getAllStudents() {

        return entityManager
                .createQuery("FROM Student", Student.class)
                .getResultList();
    }
}
