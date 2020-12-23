package com.boat.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoatEngine {
	private Integer engine_id;
	private Integer engine_revolutions;	
	private Integer boost_pressure;
	private Double oil_pressure;
	private Double oil_temperature;
	private Double engine_temperature;
	private Double engine_runtime;
	private Double fuel_rate;
	private Double coolant_pressure;
	private Double alternator_voltage;



}
