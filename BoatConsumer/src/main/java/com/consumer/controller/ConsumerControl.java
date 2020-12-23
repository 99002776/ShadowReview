package com.consumer.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

 

import com.consumer.services.TopicConsumer;

@RestController
public class ConsumerControl {
    
    @Autowired
    TopicConsumer topicConsumer;
         
//    @GetMapping("/messages")
//    public List<String> getmessages() {
//        
//        return topicConsumer.getMessages();
//    }

}
