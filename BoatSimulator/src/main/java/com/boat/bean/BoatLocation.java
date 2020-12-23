package com.boat.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoatLocation {
	
	private Double latitude;
	private Double longitude;
	private Double heading;
	private Integer altitude=0;
	private String timestamp;

}
