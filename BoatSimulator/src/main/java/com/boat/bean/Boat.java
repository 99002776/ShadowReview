package com.boat.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Boat {
	private String hid;
	private Integer noOfEngines;
	BoatLocation boatlocation;
	List<BoatEngine> boatengine=new ArrayList<>();
	
	

}
