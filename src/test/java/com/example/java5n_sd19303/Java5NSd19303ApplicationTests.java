package com.example.java5n_sd19303;

import com.example.java5n_sd19303.entity.User;
import com.example.java5n_sd19303.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class Java5NSd19303ApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    void testReadUserAndAssociatedRoles() {

        User user = userService.readUserAndAssociatedRoles(3L);
        System.out.println("Roles: " + user.getRoles());
    }

}
