package com.shad.journalApp.service;
import static org.junit.jupiter.api.Assertions.*;

import com.shad.journalApp.entity.User;
import com.shad.journalApp.repository.UserRespository;
import com.shad.journalApp.services.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {}

    @BeforeAll
    static void setUpAll() {}

    @AfterAll
    static void tearDownAll() {}

    @Disabled
    @ParameterizedTest
//    @CsvSource({
//            "ram",
//            "izhn",
//            "fa",
//            "gand mari"
//    })
    @ValueSource(strings = {
            "ram",
            "izhn",
            "fa",
            "gand mari"
    })

    public void testFindByUsername(String  userName) {
//        assertEquals(4,2+2);
//        User user=userRespository.findByUserName(username);
        assertNotNull(userRespository.findByUserName(userName),"failed for "+userName);
//        assertTrue(5>3);
//        assertFalse(!user.getJournalEntryList().isEmpty());
    }
    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));
    }
     @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a,int b,int expected){
           assertEquals(expected,a+b);
    }
}
