package com.boat.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoatEvent {
      
	String hin;
    Double latitude;
    Double longitude;
    Integer altitude;
    Integer heading;
    Integer speed;
    Boolean idle;
    Double steeringangle;
    Boolean lightstatus;
    Boolean ignitionstatus;
    Integer batterychargestatus;
    String timestamp;
       


}
