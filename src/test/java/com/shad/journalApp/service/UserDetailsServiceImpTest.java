package com.shad.journalApp.service;

import com.shad.journalApp.repository.UserRespository;
import com.shad.journalApp.services.UserDetailsServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ActiveProfiles("dev")
public class UserDetailsServiceImpTest {

    @InjectMocks
    private UserDetailsServiceImp userDetailsServiceImp;

    @Mock
    private UserRespository userRespository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void loadUserByUsernameTest() {
        // Mock the custom User entity returned by the repository
        com.shad.journalApp.entity.User mockUser = new com.shad.journalApp.entity.User();
        mockUser.setUserName("ram");
        mockUser.setPassword("inni");
        mockUser.setRoles(new ArrayList<>());

        // Mock the repository method
        when(userRespository.findByUserName(ArgumentMatchers.anyString())).thenReturn(mockUser);

        // Call the method to test
        UserDetails userDetails = userDetailsServiceImp.loadUserByUsername("ram");

        // Assertions
        Assertions.assertNotNull(userDetails, "UserDetails should not be null");
        Assertions.assertEquals("ram", userDetails.getUsername(), "Username should match");
        Assertions.assertEquals("inni", userDetails.getPassword(), "Password should match");
    }
}

