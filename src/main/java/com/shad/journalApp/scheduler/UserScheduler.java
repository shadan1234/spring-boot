package com.shad.journalApp.scheduler;

import com.shad.journalApp.cache.AppCache;
import com.shad.journalApp.entity.JournalEntry;
import com.shad.journalApp.entity.User;
import com.shad.journalApp.enums.Sentiment;
import com.shad.journalApp.repository.UserRepositoryImpl;
import com.shad.journalApp.repository.UserRespository;
import com.shad.journalApp.services.EmailService;
import com.shad.journalApp.services.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepositoryImp;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 9 * * SUN")
//    @Scheduled(cron = "0 * * ? * *")

    public void fetchUsersAndSendSAMail(){
        List<User> users = userRepositoryImp.getUserForSA();
        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntryList();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment,Integer> sentimentCounts=new HashMap<>();
            for(Sentiment sentiment : sentiments){
                if(sentiments!=null){
                    sentimentCounts.put(sentiment,sentimentCounts.getOrDefault(sentiment,0)+1);
                }
            }
            int maxCount=0;
            Sentiment maxSentiment=null;
            for(Map.Entry<Sentiment,Integer> entry : sentimentCounts.entrySet()){
                if(entry.getValue()>maxCount){
                    maxCount=entry.getValue();
                    maxSentiment=entry.getKey();
                }
            }
            if(maxSentiment!=null){
                emailService.sendEmail(user.getEmail(), "Sentiment for the last 7 days", maxSentiment.toString());
            }

        }
    }

    // to update the appCache every 10 min
//    @Scheduled(cron = "0 */10 * ? * *")
//    public void clearAppCache(){
//        appCache.init();
//    }


}
