package com.example.java5n_sd19303.service;

import com.example.java5n_sd19303.entity.Student;
import com.example.java5n_sd19303.repository.StudentRepository;
import com.example.java5n_sd19303.repository.StudentRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    /*
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {

        return studentRepository.getAllStudents();
    }

    public void saveStudent(Student student) {

        studentRepository.saveStudent(student);
    }

    public void deleteStudentById(long id) {

        studentRepository.deleteStudentById(id);
    }

    public Student getStudentById(long id) {

        return studentRepository.getStudentById(id);
    }

    public void updateStudent(Student student) {

        studentRepository.updateStudent(student);
    }
    */

    private final StudentRepositoryV2 studentRepositoryV2;
    public StudentService(StudentRepositoryV2 studentRepositoryV2) {
        this.studentRepositoryV2 = studentRepositoryV2;
    }

    public List<Student> getAllStudents() {

        return studentRepositoryV2.findAll();
    }

    public void saveStudent(Student student) {

        studentRepositoryV2.save(student);
    }

    public void deleteStudentById(long id) {

        studentRepositoryV2.deleteById(id);
    }

    public Student getStudentById(long id) {

        return studentRepositoryV2.findById(id).get();
    }

    public void updateStudent(Student student) {

        studentRepositoryV2.save(student);
    }


    public Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {

        return studentRepositoryV2
                .findAll(PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortField)));
    }
}
