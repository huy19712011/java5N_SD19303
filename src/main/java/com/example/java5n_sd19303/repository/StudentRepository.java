package com.example.java5n_sd19303.repository;

import com.example.java5n_sd19303.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
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

    public void saveStudent(Student student) {

        entityManager.persist(student);
    }

    public void deleteStudentById(long id) {

        entityManager.remove(entityManager.find(Student.class, id));
    }

    public Student getStudentById(long id) {

        return entityManager.find(Student.class, id);
    }

    public void updateStudent(Student student) {

        entityManager.merge(student);
    }
}
