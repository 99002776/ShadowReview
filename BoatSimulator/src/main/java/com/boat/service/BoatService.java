package com.boat.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.boat.bean.Boat;
import com.boat.bean.BoatEngine;
import com.boat.bean.BoatEvent;
import com.boat.bean.BoatLocation;
import com.boat.bean.BoatLog;


@Service
public class BoatService {
	
	 String hin;
	 Integer noofengines;
	    
	    Map<String, BoatLog> vehicleMap = new HashMap<String, BoatLog>();
	
	 @Autowired
		BoatUtil boatutil;
	 
	 @Autowired
     private KafkaTemplate<String, Boat> kafkaTemplate;
     private static final String DTOPICS = "boatdynamics";
	 
	 @Autowired
     private KafkaTemplate<String, BoatEvent> kafkaTemplateEvent;
     private static final String TOPICS = "boatevent";
     
     @Autowired
     private KafkaTemplate<String, BoatLog> kafkaTemplateVehicle;
     private static final String VTOPICS = "boatregister";

	/**
 * BoatEventData generates boat event data for consumer
 * @throws IOException
 */
	public String BoatEventData() throws IOException {
		// TODO Auto-generated method stub
		BoatLog vehicle=vehicleMap.get(hin);
		if(vehicle!=null) {
		BoatEvent boatevent = new BoatEvent();
		boatutil.readDataFromExcel(boatevent);
		boatevent.setAltitude(boatutil.getRandomNumber(755,765));
		boatevent.setBatterychargestatus(20);
		boatevent.setHeading(boatutil.getRandomNumber(-180,180));
		//boatevent.setHin(boatutil.getAlphaNumericString(12));
		boatevent.setHin(vehicle.getHid());
		boatevent.setIdle(false);
		boatevent.setIgnitionstatus(true);
		boatevent.setLightstatus(false);
		boatevent.setSpeed(40);
		boatevent.setSteeringangle(boatutil.getRandomNumberDouble(0, 180));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        boatevent.setTimestamp(strDate);
        kafkaTemplateEvent.send(TOPICS,boatevent);
        return "event data published";
		}
		else {
			
			return "no vehicle to publish eventdata";
		}
		
	}

	 

	@SuppressWarnings("unused")
	public String pushBoatDynamics() throws IOException {
		// TODO Auto-generated method stub
		Boat boat = new Boat();
		BoatLog vehicle=vehicleMap.get(hin);
		if(vehicle!=null) {
			int n = vehicle.getNoOfEngines();
			boat.setHid(vehicle.getHid());
			boat.setNoOfEngines(vehicle.getNoOfEngines());
			BoatLocation loc = new BoatLocation();
			loc.setAltitude(0);
			loc.setHeading(boatutil.getRandomNumberDouble(-180, 180));
			boatutil.readDataFromExcel(loc);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	        Date now = new Date();
	        String strDate = sdf.format(now);
			loc.setTimestamp(strDate);
			boat.setBoatlocation(loc);
			List<BoatEngine> boatlist = new ArrayList<>();
			for(int i=0;i<n;i++) {
				
				BoatEngine boatengine = new BoatEngine();
				boatengine.setAlternator_voltage(boatutil.getRandomNumberDouble(0, 15));
				boatengine.setBoost_pressure(0);
				boatengine.setCoolant_pressure(boatutil.getRandomNumberDouble(500, 600));
				boatengine.setEngine_id(i+1);
				boatengine.setEngine_revolutions(boatutil.getRandomNumber(1500, 1900));
				boatengine.setEngine_runtime(120.4);
				boatengine.setEngine_temperature(boatutil.getRandomNumberDouble(120, 150));
				boatengine.setFuel_rate(boatutil.getRandomNumberDouble(2, 4));
				boatengine.setOil_pressure(boatutil.getRandomNumberDouble(0, 60));
				boatengine.setOil_temperature(boatutil.getRandomNumberDouble(190, 202));
				boatlist.add(boatengine);
				
			}
			boat.setBoatengine(boatlist);
			 kafkaTemplate.send(DTOPICS,boat);
			return "Successfully Posted";
		}
		else {

			return "no vehicle data to push";
		}
		
	}



	public void vehicleRegister(BoatLog vehicle) {
		// TODO Auto-generated method stub
		hin=vehicle.getHid();
		noofengines=vehicle.getNoOfEngines();
		vehicleMap.put(vehicle.getHid(), vehicle);
		 kafkaTemplateVehicle.send(VTOPICS,vehicle);
		
	}



	public String stopBoatDynamics() {
		System.out.println(hin);
		vehicleMap.remove(hin);
		// TODO Auto-generated method stub
		return "boat simulation stopped ";
	}


}
