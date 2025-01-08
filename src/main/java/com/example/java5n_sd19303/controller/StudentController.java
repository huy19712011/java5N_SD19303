package com.example.java5n_sd19303.controller;

import com.example.java5n_sd19303.entity.Student;
import com.example.java5n_sd19303.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String listStudents(Model model) {

        // get data from DB
        List<Student> students = studentService.getAllStudents();

        // add data to model
        model.addAttribute("students", students);

        // return view name
        return "/views/students";

    }
}
