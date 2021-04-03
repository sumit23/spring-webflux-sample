package com.springsample.webflux.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class VehicleDto {
	
	private String model;
	private String make;
	private String vin;
	private String color;
	private String year;
	private int id;
}
