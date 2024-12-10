package com.shad.journalApp.repository;

import com.shad.journalApp.entity.ConfigJournalAppEntity;
import com.shad.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ConfigJournalAppRespository extends  MongoRepository<ConfigJournalAppEntity, ObjectId> {

}
