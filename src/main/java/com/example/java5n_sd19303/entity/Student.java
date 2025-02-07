package com.example.java5n_sd19303.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// JPQL
@NamedQuery(
        name = "Student.findByNameV4a",
        query = "SELECT s FROM Student s WHERE s.name=?1"
)
// Native
@NamedNativeQuery(
        name = "Student.findByNameV4b",
        query = "SELECT * FROM students s WHERE s.name=?1",
        resultClass = Student.class
)
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;

    @Email(message = "Please enter a valid email")
    @Pattern(regexp = "^(.+)@(fpt\\.edu\\.vn)$", message = "Invalid FPT Poly Email")
    private String email;

    @Pattern(regexp = "^(0)\\d{9}", message = "Invalid phone number")
    private String phone;

    public Student() {
    }

    public Student(long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
