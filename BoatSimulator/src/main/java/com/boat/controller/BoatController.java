package com.boat.controller;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boat.bean.BoatLog;
import com.boat.service.BoatService;

@RestController
public class BoatController {
	
	@Autowired
	BoatService boatservice;
	@Autowired
	BoatLog boatlog;
	 /**
     * GetMapping for the Boat event data.
     * @return
     * @throws IOException
     */
    @GetMapping("/event")
    public String post() throws IOException {
        	
    	return (boatservice.BoatEventData());
//        return "Published  event data successfully";
            
    } 
    
  @GetMapping("/stopboat")
  public String stopBoat() throws IOException {
  	
  	System.out.println(boatservice.stopBoatDynamics());
  	return "stopped  Sucessfully";
  }
    
    
    @GetMapping("/publishboat")
    public String publishBoat() throws IOException {
    	
    	System.out.println(boatservice.pushBoatDynamics());
    	return "Published Sucessfully";
    }
    
    
    /**
     * PostMapping for the vehicle data from Rest API.
     * @param vehicle
     * @return
     */
    @CrossOrigin
    @PostMapping("/boat/post")
    public String postController(@RequestBody String vehicle)
    {
    	Object file = JSONValue.parse(vehicle);
        JSONObject jsonObjectdecode = (JSONObject)file;
        String hin=(String)jsonObjectdecode.get("hid");
        
    	System.out.println(hin);
    	
    	if(hin.isEmpty()) {
    		return "please enter details";
    	}
    	else {
    		System.out.println("im in else");
    		String name=(String)jsonObjectdecode.get("name");
            String model=(String)jsonObjectdecode.get("model");
            String noOfEngines=(String)jsonObjectdecode.get("noOfEngines");
            Integer noe = Integer.valueOf(noOfEngines);
            boatlog.setHid(hin);
            boatlog.setModel(model);
            boatlog.setName(name);
            boatlog.setNoOfEngines(noe);
         
//        System.out.println(boatlog);
            boatservice.vehicleRegister(boatlog);
       
            return"sucessfully posted";
       
          }
    	
    }

	

}
