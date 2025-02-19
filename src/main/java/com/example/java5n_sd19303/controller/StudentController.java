package com.example.java5n_sd19303.controller;

import com.example.java5n_sd19303.entity.Student;
import com.example.java5n_sd19303.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String listStudents(Model model) {

        //// get data from DB
        //List<Student> students = studentService.getAllStudents();
        //
        //// add data to model
        //model.addAttribute("students", students);
        //
        //// return view name
        //return "views/students";

        return findPaginated(1, "name", "asc", model);

    }

    @GetMapping("/students/showNewStudentForm")
    public String showNewStudentForm(Model model) {

        Student student = new Student();
        model.addAttribute("student", student);

        return "/views/new_student";
    }

    @PostMapping("/students/saveStudent")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "views/new_student";
        }

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

    @GetMapping("/students/page/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 1;

        // read data from DB
        Page<Student> page = studentService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Student> students = page.getContent();

        // forward data to html
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("students", students);

        // return html
        return "views/students";

    }
}
