package com.example.java5n_sd19303;

import com.example.java5n_sd19303.repository.StudentRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Java5NSd19303Application implements CommandLineRunner {

    private final StudentRepositoryV2 studentRepositoryV2;
    public Java5NSd19303Application(StudentRepositoryV2 studentRepositoryV2) {
        this.studentRepositoryV2 = studentRepositoryV2;
    }

    public static void main(String[] args) {
        SpringApplication.run(Java5NSd19303Application.class, args);

        System.out.println("running...");
    }

    @Override
    public void run(String... args) throws Exception {

        // 2. example
        studentRepositoryV2
                .findByNameContaining("stu")
                .forEach(s -> System.out.println(s.getName()));

        // 3.
        studentRepositoryV2
                .findByNameOrIdV3b("student 1", 1004)
                .forEach(s -> System.out.println(s.getName()));
    }
}
