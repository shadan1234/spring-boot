package com.shad.journalApp.cache;

import com.shad.journalApp.entity.ConfigJournalAppEntity;
import com.shad.journalApp.repository.ConfigJournalAppRespository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRespository configJournalAppRespository;

    public Map<String,String> appCache;

    @PostConstruct
    public void init() {
        appCache= new HashMap<>();
        List<ConfigJournalAppEntity> all=configJournalAppRespository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity:all) {
            appCache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }
    }

}
