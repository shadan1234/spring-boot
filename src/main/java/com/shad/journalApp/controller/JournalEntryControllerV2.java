package com.shad.journalApp.controller;

import com.shad.journalApp.entity.JournalEntry;
import com.shad.journalApp.entity.User;
import com.shad.journalApp.services.JournalEntryService;
import com.shad.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesByUserName() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user= userService.findUserByUsername(userName);
        List<JournalEntry> journalEntries= user.getJournalEntryList();
        if(journalEntries != null && !journalEntries.isEmpty()) {

            return new ResponseEntity<>(journalEntries, HttpStatus.OK);

        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);

    }

    @PostMapping()
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveEntry(entry,userName);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(entry,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myid) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user= userService.findUserByUsername(userName);
        List<JournalEntry> collect=user.getJournalEntryList().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()) {


            JournalEntry journalEntry = journalEntryService.getJournalEntryById(myid);
            if (journalEntry != null) {
                return new ResponseEntity<>(journalEntry, HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myid) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean removed=journalEntryService.deleteJournalEntry(myid,userName);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("id/{myid}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry ) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user= userService.findUserByUsername(userName);
        List<JournalEntry> collect=user.getJournalEntryList().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()) {

            JournalEntry old = journalEntryService.getJournalEntryById(myid);

            if (old != null) {
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                old.setDate(LocalDateTime.now());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old,HttpStatus.ACCEPTED);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
