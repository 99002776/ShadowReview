package com.boat.scheduler;    
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.boat.service.BoatService;

 


    @EnableScheduling
    @Component
    @SuppressWarnings("unused")
    public class BoatScheduler {
       
        Logger logger = LoggerFactory.getLogger(BoatScheduler.class);

        @Autowired
        BoatService boatService;
           
         @Scheduled(initialDelayString="${TIMEOUT_SCHEDULER_INTIAL_DELAY}",
                 fixedRateString="${TIMEOUT_SCHEDULER_DELAY}")
         
         /**
          * pushBoatData generates random data for boat.
          * @throws IOException
          */
         public void pushBoatData() throws IOException {
             
           System.out.println(boatService.pushBoatDynamics()); 
             
         }
     
      
        
        
        
        
        
    }