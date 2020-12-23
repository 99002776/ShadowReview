//package com.boat.scheduler;
//
// 
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
// 
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Map.Entry;
//
// 
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
// 
//
//import com.boat.bean.Boat;
//import com.boat.service.BoatService;
//
// 
//
// 
//
//class BikeSchedulerTest {
//    @InjectMocks
//    BoatScheduler boatScheduler;
//    
//    @Mock
//    BoatService boatService;
//    @Mock
//    Boat boat;
//    HashMap<String, Boat> boatMap = new HashMap<String, Boat>();
//    private MockMvc mockMvc;
//
// 
//
//    @BeforeAll
//    static void setUpBeforeClass() throws Exception {
//          System.out.println("Before each testcases");
//        
//    }
//
// 
//
//    @AfterAll
//    static void tearDownAfterClass() throws Exception {
//          System.out.println("After each testcases");
//    }
//
// 
//
//    @BeforeEach
//    void setUp() throws Exception {
//         MockitoAnnotations.initMocks(this);
//           mockMvc = MockMvcBuilders.standaloneSetup(boatScheduler).dispatchOptions(true).build();       
//           boat.setHid("123456");
//           boatService.setVehicleMap(boatMap);
//            boatMap.put(boat.getHid(),boat); 
//    }
//         
//
// 
//
//    @AfterEach
//    void tearDown() throws Exception {
//          System.out.println("After each testcases");
//    }
//
// 
//
//    @Test
//    void test() {
//        
//        when(boatService.getBoatMap()).thenReturn( boatMap);
//        //when(bikeService.post()).thenReturn( bikeMap)
//        //Map<MutableKey, String> items = new HashMap<>();
////        Iterable<String> iterable = Collections.emptySet();
////        assertEquals(0, Iterables.size(iterable));
//        @SuppressWarnings("rawtypes")
//        Iterator iterator = boatMap.entrySet().iterator();
//        while (iterator.hasNext()){
//        @SuppressWarnings("unchecked")
//        Entry<String,Boat> entry  = (Entry<String,Boat>) iterator.next();
//        }
//    }
//
// 
//
//        
//    }