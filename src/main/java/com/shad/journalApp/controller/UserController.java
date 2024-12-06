package com.shad.journalApp.controller;

import com.shad.journalApp.entity.User;
import com.shad.journalApp.repository.UserRespository;
import com.shad.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRespository userRespository;



    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody  User user) {
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


}
