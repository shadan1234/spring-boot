package com.shad.journalApp.services;
import com.shad.journalApp.entity.User;
import com.shad.journalApp.repository.UserRespository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRespository userRespository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public void saveNewAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("ADMIN"));
        userRespository.save(user);
    }
    public boolean saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of("USER"));
            userRespository.save(user);

            return true;
        } catch (Exception e) {
            log.error("Error occured for {}",user.getUserName(),e.getMessage());
            log.info(e.getMessage(),"vahooahsdfihjasiufdhasilufdhas");
            log.trace("vahooahsdfihjasiufdhasilufdhas");
            log.debug("vahooahsdfihjasiufdhasilufdhas");
            log.warn("vahooahsdfihjasiufdhasilufdhas");
          return false;
        }

    }
    public void saveUser(User user) {

        userRespository.save(user);
    }

    public List<User> getAllUsers() {
        return userRespository.findAll();
    }

    public User getUserById(ObjectId id) {
        return userRespository.findById(id).orElse(null);
    }

    public void deleteById(ObjectId id) {
        userRespository.deleteById(id);
    }

    public User findUserByUsername(String userName) {
        return userRespository.findByUserName(userName);
    }

}
