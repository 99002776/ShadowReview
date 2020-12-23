package com.boat.controller;

 

import static org.junit.jupiter.api.Assertions.*;

 

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

 

import com.boat.bean.Boat;
import com.boat.bean.BoatEvent;
import com.boat.service.BoatService;
import com.fasterxml.jackson.databind.ObjectMapper;

 


class BikeControllerTest {
    @Mock
    BoatService boatService;
    
    @InjectMocks
    BoatController boatController;

 

    Boat boat =new Boat();
    BoatEvent boatEvent = new BoatEvent();

 

    

 

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("Before each testcases");
        
    }

 

    @AfterAll
    static void tearDownAfterClass() throws Exception {
          System.out.println("After each testcases");
    }

 

    private Object mockMvc;

 

    @BeforeEach
    void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
            this.mockMvc = MockMvcBuilders.standaloneSetup(boatController).dispatchOptions(true).build();  
      
    }
    

 

    @Test
    void testGetUserId() throws Exception {

 

        Boat boat =new Boat();
        ObjectMapper mapper = new ObjectMapper();        
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/publishboat",boat).content("application/json");
        String json = mapper.writeValueAsString(boat);
        builder = builder.content(json.toString());
        builder = builder.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        ResultActions resultActions = ((MockMvc) this.mockMvc).perform(builder);
        boatService.pushBoatDynamics();
        assertNotNull(resultActions);      
    }

 


    @Test
    void testBoatEvent() throws Exception {
        BoatEvent boatEvent =new BoatEvent();
        ObjectMapper mapper = new ObjectMapper();        
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/event",boatEvent).content("application/json");
        String json = mapper.writeValueAsString(boatEvent);
        builder = builder.content(json.toString());
        builder = builder.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        ResultActions resultActions = ((MockMvc) this.mockMvc).perform(builder);
        boatService.BoatEventData();
        assertNotNull(resultActions);     
        
    }

 

}