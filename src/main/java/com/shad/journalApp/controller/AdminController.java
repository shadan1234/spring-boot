package com.shad.journalApp.controller;

import com.shad.journalApp.entity.User;
import com.shad.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> all=userService.getAllUsers();
        if(all.size()>0) {
            return ResponseEntity.ok(all);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("create-admin-user")
    public void createAdminUser(@RequestBody User user) {
            userService.saveNewAdmin(user);
    }

}
