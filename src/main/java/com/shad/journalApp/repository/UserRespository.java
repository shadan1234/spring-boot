package com.shad.journalApp.repository;

import com.shad.journalApp.entity.JournalEntry;
import com.shad.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRespository extends  MongoRepository<User, ObjectId> {
    User findByUserName(String userName);

    void deleteByUserName(String userName);
}
