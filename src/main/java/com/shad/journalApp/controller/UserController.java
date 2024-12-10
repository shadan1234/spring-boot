package com.shad.journalApp.controller;

import com.shad.journalApp.api.response.WeatherResponse;
import com.shad.journalApp.entity.User;
import com.shad.journalApp.repository.UserRespository;
import com.shad.journalApp.services.UserService;
import com.shad.journalApp.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private WeatherService weatherService;



    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody  User user) // this is also an example of json to pojo as json from client is converted to pojo user

     {
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       String userName = authentication.getName();
        User userInDb= userService.findUserByUsername(userName);
//        if(userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
//        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        userRespository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting="";
        if(weatherResponse != null){
            greeting=" weather feels like "+weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi "+authentication.getName()+greeting,HttpStatus.OK);
    }

}
