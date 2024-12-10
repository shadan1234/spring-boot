package com.shad.journalApp.repository;

import com.shad.journalApp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryImplTests {

    @Autowired
    private UserRepositoryImpl userRespositoryImpl;
    @Test
    public void testSaveNewUser() {
        userRespositoryImpl.getUserForSA();
    }
}
