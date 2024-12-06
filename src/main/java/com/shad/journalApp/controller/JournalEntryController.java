//package com.shad.journalApp.controller;
//
//import com.shad.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("journal")
//public class JournalEntryController {
//
//    private Map<Long, JournalEntry> journalEntries= new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry entry){
//          journalEntries.put(entry.getId(), entry);
//          return true;
//    }
//
//    @GetMapping("/id/{myid}")
//    public JournalEntry getEntryById(@PathVariable long myid){
//        return journalEntries.get(myid);
//    }
//
//    @DeleteMapping("/id/{myid}")
//    public JournalEntry deleteEntryById(@PathVariable long myid){
//       return journalEntries.remove(myid);
//    }
//
//    @PutMapping("id/{myid}")
//    public JournalEntry updateEntry(@PathVariable Long myid , @RequestBody JournalEntry entry){
//
//       return journalEntries.put(myid, entry);
//
//    }
//}
