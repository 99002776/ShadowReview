package com.consumer.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component; 

@Component
public class TopicConsumer {
    
   // private final List<String> messages = new ArrayList<>();
    
    private final String url = "jdbc:postgresql://localhost/Boat";
    private final String user = "postgres";
    private final String password = "password";
    
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
    
    /**
     * scheduled Boat  engine data
     * @param message
     */
    @KafkaListener(topics = "boatdynamics", groupId = "boat")
    public void listen(String message) {
    	
    	
    	
    	System.out.println(message);
        
        Object file = JSONValue.parse(message); 
        JSONObject jsonObjectdecode = (JSONObject)file; 
  
         String hin=(String)jsonObjectdecode.get("hid");
    	 Long noOfEngines=(Long)jsonObjectdecode.get("noOfEngines");
    	 JSONObject boatlocation = (JSONObject)jsonObjectdecode.get("boatlocation");
    	 Double latitude=(Double)boatlocation.get("latitude");
    	 Double longitude=(Double)boatlocation.get("longitude");
    	 Double heading=(Double)boatlocation.get("heading");
    	 Long altitude=(Long)boatlocation.get("altitude");
    	 String timestamp = (String)boatlocation.get("timestamp");
    	 
    	 TopicConsumer topicConsumer = new TopicConsumer();
         Connection conn = topicConsumer.connect();
         
         try {
             Statement stmnt = null;
             stmnt = connect().createStatement();
             
             String query = "INSERT INTO boatlocation (hin, latitude,longitude,altitude,heading,timestamp ) VALUES(?,?,?,?,?,?)";
             PreparedStatement pst = conn.prepareStatement(query);
           
               pst.setString(1,hin);
               pst.setDouble(2,latitude);
               pst.setDouble(3,longitude);
               pst.setLong(4,altitude);
               pst.setDouble(5,heading);
               pst.setString(6,timestamp);
             
             System.out.println("before insert location");
             pst.executeUpdate();
             System.out.println("After insert location");

         } catch (SQLException e) {
             System.out.println(e.getMessage());
         } 
         
    	 
    	 JSONArray boatengine = (JSONArray)jsonObjectdecode.get("boatengine");
         Iterator itr = boatengine.iterator();
         
         for(int i=0;i<boatengine.size();i++) {
        	 
             JSONObject beobj=(JSONObject)boatengine.get(i);
             Long engid=(Long) beobj.get("engine_id");
             Long engine_revolutions =(Long)beobj.get("engine_revolutions");
             Long boost_pressure =(Long)beobj.get("boost_pressure");
             Double oil_pressure = (Double)beobj.get("oil_pressure");
             Double oil_temperature= (Double)beobj.get("oil_temperature");
             Double engine_temperature = (Double)beobj.get("engine_temperature");
             Double engine_runtime = (Double)beobj.get("engine_runtime");
             Double fuel_rate = (Double)beobj.get("fuel_rate");
             Double coolant_pressure = (Double)beobj.get("coolant_pressure");
             Double alternator_voltage = (Double)beobj.get("alternator_voltage");
       
         
        try {
            Statement stmnt = null;
            stmnt = connect().createStatement();
            
            String query = "INSERT INTO boatdynamics (hin,engid, engine_revolutions,boost_pressure,oil_pressure, oil_temperature,engine_temperature,engine_runtime,fuel_rate,coolant_pressure,alternator_voltage,timestamp) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            System.out.println(hin);
            pst.setString(1,hin);
            pst.setLong(2,engid);
            pst.setLong(3,engine_revolutions);
            pst.setLong(4,boost_pressure);
            pst.setDouble(5,oil_pressure);
            pst.setDouble(6,oil_temperature);
            pst.setDouble(7,engine_temperature);
            pst.setDouble(8,engine_runtime);
            pst.setDouble(9,fuel_rate);
            pst.setDouble(10,coolant_pressure);
            pst.setDouble(11,alternator_voltage);
            pst.setString(12,timestamp);
            
            
            System.out.println("before insert engine");
            pst.executeUpdate();
            System.out.println("After insert engine");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }  
         }

    }

    
    
    
//    @KafkaListener(topics = "BoatData", groupId = "kafka-sandbox")
//    public void listen(String message) {
//        
//        Object file = JSONValue.parse(message); 
//        JSONObject jsonObjectdecode = (JSONObject)file; 
//  
//         String hin=(String)jsonObjectdecode.get("hin");
//    	 Long engine_rpm=(Long)jsonObjectdecode.get("engine_rpm");
//    	 Double engine_temperature=(Double)jsonObjectdecode.get("engine_temperature");
//    	 Long current_fuel=(Long)jsonObjectdecode.get("current_fuel");
//    	 Double latitude=(Double)jsonObjectdecode.get("latitude");
//    	 Double longitude=(Double)jsonObjectdecode.get("longitude");
//    	 String timestamp=(String)jsonObjectdecode.get("timestamp");
//    	 Long heading=(Long)jsonObjectdecode.get("heading");
//         Long speed=(Long)jsonObjectdecode.get("speed");
//         JSONObject obj=(JSONObject)jsonObjectdecode.get("");
//        TopicConsumer topicConsumer = new TopicConsumer();
//        Connection conn = topicConsumer.connect();
//        
//        try {
//            Statement stmnt = null;
//            stmnt = connect().createStatement();
//            
//            String query = "INSERT INTO boatdata (hin, engine_rpm, engine_temperature,current_fuel,latitude, longitude,timestamp,heading,speed ) VALUES(?,?,?,?,?,?,?,?,?)";
//            PreparedStatement pst = conn.prepareStatement(query);
//          
//            pst.setString(1,hin);
//            pst.setLong(2,engine_rpm);
//            pst.setDouble(3,engine_temperature);
//            pst.setLong(4,current_fuel);
//            pst.setDouble(5,latitude);
//            pst.setDouble(6,longitude);
//            pst.setString(7,timestamp);
//            pst.setLong(8,heading);
//            pst.setLong(9,speed);
//            
//            System.out.println("after insert");
////            stmnt.execute(query);
//            pst.executeUpdate();
//            System.out.println("Afterupdate");
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }  
//
//    }

    
/**
 * database connection for boat event 
 * 
 */
    
    @KafkaListener(topics = "boatevent", groupId = "boat")
    public void Eventlisten(String message) {
        
        Object file = JSONValue.parse(message); 
        
        JSONObject jsonObjectdecode = (JSONObject)file; 
        
        //Long id =(Long)jsonObjectdecode.get("id"); 
        String hin=(String)jsonObjectdecode.get("hin");
        Double latitude=(Double)jsonObjectdecode.get("latitude");
        Double longitude=(Double )jsonObjectdecode.get("longitude");
        Long altitude=(Long)jsonObjectdecode.get("altitude");
        Long heading=(Long)jsonObjectdecode.get("heading");
        Long speed=(Long)jsonObjectdecode.get("speed");
        Boolean idle=(Boolean)jsonObjectdecode.get("idle");
        Double  steeringangle=(Double )jsonObjectdecode.get("steeringangle");
        Boolean lightstatus=(Boolean)jsonObjectdecode.get("lightstatus");
        Boolean ignitionstatus=(Boolean)jsonObjectdecode.get("ignitionstatus");
        Long batterychargestatus=(Long)jsonObjectdecode.get("batterychargestatus");
        String timestamp=(String)jsonObjectdecode.get("timestamp");
        
          
        TopicConsumer topicConsumer = new TopicConsumer();
        Connection conn = topicConsumer.connect();
        
        try {
            Statement stmnt = null;
            stmnt = connect().createStatement();
            
            String query = "INSERT INTO boatevent ( hin,latitude,longitude,altitude, heading, speed, steeringangle, lightstatus,  ignitionstatus,  batterychargestatus, timestamp,  idle) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
//            
            pst.setString(1,hin);
            pst.setDouble(2,latitude);
            pst.setDouble(3,longitude);
            pst.setLong(4,altitude);
            pst.setLong(5,heading);
            pst.setLong(6,speed);
            pst.setDouble(7,steeringangle);
            pst.setBoolean(8,lightstatus);
            pst.setBoolean(9,ignitionstatus);
            pst.setLong(10,batterychargestatus);
            pst.setString(11,timestamp);
            pst.setBoolean(12,idle );
          
            
            System.out.println("after insert");
            pst.executeUpdate();
            System.out.println("Afterupdate");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    
    
    
//@KafkaListener(topics = "BoatEvent", groupId = "kafka-sandbox")
//public void Eventlisten(String message) {
//    
//    Object file = JSONValue.parse(message); 
//    
//    JSONObject jsonObjectdecode = (JSONObject)file; 
//    
//    //Long id =(Long)jsonObjectdecode.get("id"); 
//    String hin=(String)jsonObjectdecode.get("hin");
//    Double latitude=(Double)jsonObjectdecode.get("latitude");
//    Double longitude=(Double )jsonObjectdecode.get("longitude");
//    Long altitude=(Long)jsonObjectdecode.get("altitude");
//    Long heading=(Long)jsonObjectdecode.get("heading");
//    Long speed=(Long)jsonObjectdecode.get("speed");
//    Boolean idle=(Boolean)jsonObjectdecode.get("idle");
//    Double  steeringangle=(Double )jsonObjectdecode.get("steeringangle");
//    Boolean lightstatus=(Boolean)jsonObjectdecode.get("lightstatus");
//    Boolean ignitionstatus=(Boolean)jsonObjectdecode.get("ignitionstatus");
//    Long batterychargestatus=(Long)jsonObjectdecode.get("batterychargestatus");
//    String timestamp=(String)jsonObjectdecode.get("timestamp");
//    
//      
//    TopicConsumer topicConsumer = new TopicConsumer();
//    Connection conn = topicConsumer.connect();
//    
//    try {
//        Statement stmnt = null;
//        stmnt = connect().createStatement();
//        
//        String query = "INSERT INTO boatevent ( hin,latitude,longitude,altitude, heading, speed, steeringangle, lightstatus,  ignitionstatus,  batterychargestatus, timestamp,  idle) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
//        PreparedStatement pst = conn.prepareStatement(query);
////        
//        pst.setString(1,hin);
//        pst.setDouble(2,latitude);
//        pst.setDouble(3,longitude);
//        pst.setLong(4,altitude);
//        pst.setLong(5,heading);
//        pst.setLong(6,speed);
//        pst.setDouble(7,steeringangle);
//        pst.setBoolean(8,lightstatus);
//        pst.setBoolean(9,ignitionstatus);
//        pst.setLong(10,batterychargestatus);
//        pst.setString(11,timestamp);
//        pst.setBoolean(12,idle );
//      
//        
//        System.out.println("after insert");
//        pst.executeUpdate();
//        System.out.println("Afterupdate");
//
//    } catch (SQLException e) {
//        System.out.println(e.getMessage());
//    }
//    
//}


///**
// * database connection for Vehicle data. 
// * 
// */ 
//@KafkaListener(topics = "vehicle", groupId = "kafka-sandbox")
//public void vehicleListen(String message) {
//
//	Object file = JSONValue.parse(message); 
//	JSONObject jsonObjectdecode = (JSONObject)file; 
//	
//	String hin=(String)jsonObjectdecode.get("hin");
//	String engine_type=(String)jsonObjectdecode.get("engine_type");
//	Long fuel_capacity=(Long)jsonObjectdecode.get("fuel_capacity");
//	Long max_speed=( Long)jsonObjectdecode.get("max_speed");
//
//
//		TopicConsumer topicConsumer = new TopicConsumer();
//		Connection conn = topicConsumer.connect();
//
//		try {
//			Statement stmnt = null;
//			stmnt = connect().createStatement();
//
//			String query = "INSERT INTO vehicle ( hin,engine_type,fuel_capacity,max_speed ) VALUES(?,?,?,?)";
//			PreparedStatement pst = conn.prepareStatement(query);
//			
//			pst.setString(1,hin);
//			pst.setString(2,engine_type);
//			pst.setLong(3,fuel_capacity);
//			pst.setLong(4,max_speed);
// 
//			System.out.println("after insert");
//			pst.executeUpdate();
//			System.out.println("Afterupdate");
//
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
//}

/**
 * database connection for Vehicle data. 
 * 
 */ 
@KafkaListener(topics = "boatregister", groupId = "boat")
public void vehicleListen(String message) {

	Object file = JSONValue.parse(message); 
	JSONObject jsonObjectdecode = (JSONObject)file; 
	
	String hin=(String)jsonObjectdecode.get("hid");
	String name=(String)jsonObjectdecode.get("name");
	String model=(String)jsonObjectdecode.get("model");
	Long noOfEngines=( Long)jsonObjectdecode.get("noOfEngines");


		TopicConsumer topicConsumer = new TopicConsumer();
		Connection conn = topicConsumer.connect();

		try {
			Statement stmnt = null;
			stmnt = connect().createStatement();

			String query = "INSERT INTO boatregister ( hid,name,model,noOfEngines ) VALUES(?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(query);
			
			pst.setString(1,hin);
			pst.setString(2,name);
			pst.setString(3,model);
			pst.setLong(4,noOfEngines);
 
			System.out.println("after insert");
			pst.executeUpdate();
			System.out.println("Afterupdate");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
}

 
}