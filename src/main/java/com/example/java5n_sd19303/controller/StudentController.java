package com.example.java5n_sd19303.controller;

import com.example.java5n_sd19303.entity.Student;
import com.example.java5n_sd19303.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/students/showNewStudentForm")
    public String showNewStudentForm(Model model) {

        Student student = new Student();
        model.addAttribute("student", student);

        return "/views/new_student";
    }

    @PostMapping("/students/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {

        // save student to DB
        studentService.saveStudent(student);

        // return view
        return "redirect:/students";
    }

    @GetMapping("/students/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") long id) {

        // delete from DB
        studentService.deleteStudentById(id);

        // return view
        return "redirect:/students";

    }

    @GetMapping("/students/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable("id") long id, Model model) {

        Student student = studentService.getStudentById(id);

        model.addAttribute("student", student);

        return "views/update_student";
    }

    @PostMapping("/students/updateStudent")
    public String updateStudent(@ModelAttribute("student") Student student) {

        studentService.updateStudent(student);

        return "redirect:/students";

    }
}
