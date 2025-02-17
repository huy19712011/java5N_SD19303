package com.example.java5n_sd19303;

import com.example.java5n_sd19303.entity.Role;
import com.example.java5n_sd19303.entity.Student;
import com.example.java5n_sd19303.entity.User;
import com.example.java5n_sd19303.repository.StudentRepositoryV2;
import com.example.java5n_sd19303.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class Java5NSd19303Application implements CommandLineRunner {

    private final StudentRepositoryV2 studentRepositoryV2;
    private final UserService userService;
    public Java5NSd19303Application(StudentRepositoryV2 studentRepositoryV2, UserService userService) {
        this.studentRepositoryV2 = studentRepositoryV2;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Java5NSd19303Application.class, args);

        System.out.println("running...");
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // 2. example
        studentRepositoryV2
                .findByNameContaining("stu")
                .forEach(s -> System.out.println(s.getName()));

        // 3.
        studentRepositoryV2
                .findByNameOrIdV3b("student 1", 1004)
                .forEach(s -> System.out.println(s.getName()));

        // 4.
        studentRepositoryV2
                .findByNameV4b("student 1")
                .forEach(s -> System.out.println(s.getName()));

        // 5. Pagination
        int pageNo = 0; // index = 0 by default
        int pageSize = 2;

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Student> page = studentRepositoryV2.findAll(pageable);

        // all items of page
        List<Student> students = page.getContent();
        students.forEach(s -> System.out.println(s.getName()));

        // all pages
        int totalPages = page.getTotalPages();
        System.out.println(totalPages);

        // all elements (of DB)
        long totalElements = page.getTotalElements();
        System.out.println(totalElements);

        // last, first, size, ...

        // 6. Sorting
        String sortBy = "name";
        String sortDir = "desc"; // "asc"

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy) : Sort.by(sortBy).descending();

        studentRepositoryV2
                .findAll(sort)
                .forEach(s -> System.out.println(s.getName()));

        // multiple fields
        Sort.by("name").descending()
                .and(Sort.by("email").descending());

        // 7. Pagination + Sort
        studentRepositoryV2
                .findAll(PageRequest.of(pageNo, pageSize, sort))
                .forEach(s -> System.out.println(s.getName()));

        // users and roles
        User user = new User("user 1", "password 1", "phone 1", "email 1");
        Role role = new Role("admin");
        user.addRole(role);
        userService.addUser(user);

        User user2 = new User("user 2", "password 2", "phone 2", "email 2");
        user2.addRole(role);
        userService.addUser(user2);


        User user3 = new User("user 3", "password 3", "phone 3", "email 3");
        Role role2 = new Role("user");
        user3.addRole(role2);
        user3.addRole(role);
        userService.addUser(user3);





    }
}
