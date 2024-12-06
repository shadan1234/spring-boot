package com.shad.journalApp.services;

import com.shad.journalApp.entity.JournalEntry;
import com.shad.journalApp.entity.User;
import com.shad.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(JournalEntry entry, String userName) {
        try {

            User user = userService.findUserByUsername(userName);
            entry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(entry);
            user.getJournalEntryList().add(saved);
//            user.setUserName(null);
            userService.saveUser(user);
        } catch (Exception e) {

            System.out.println(e);
            throw new RuntimeException("An exception has occured",e);
        }

    }

    public void saveEntry(JournalEntry entry) {


       journalEntryRepository.save(entry);

    }

    public List<JournalEntry> getJournalEntries() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry getJournalEntryById(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    @Transactional
    public boolean deleteJournalEntry(ObjectId id, String userName) {
        boolean removed=false;
        try {
            User user = userService.findUserByUsername(userName);
             removed=user.getJournalEntryList().removeIf(x->x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occured while deleting the entry",e);
        }
        return removed;


    }
//    public  JournalEntry updateJournalEntry(ObjectId id, JournalEntry entry) {
//        JournalEntry entry1 = journalEntryRepository.findById(id).orElse(null);
//        entry1=entry;
//        journalEntryRepository.save(entry1);
//        return entry1;
//    }
}

//controller -> service -> repository