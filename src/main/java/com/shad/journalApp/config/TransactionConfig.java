//package com.shad.journalApp.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.MongoDatabaseFactory;
//import org.springframework.data.mongodb.MongoTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@EnableTransactionManagement
//public class TransactionConfig {
//    @Bean
//    public PlatformTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
//        return new MongoTransactionManager(dbFactory);
//    }
//}


// we can do it like this