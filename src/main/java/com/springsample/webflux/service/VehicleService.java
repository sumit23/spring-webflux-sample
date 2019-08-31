package com.springsample.webflux.service;

import com.springsample.webflux.dto.ServiceResponseDto;
import com.springsample.webflux.dto.VehicleDto;

public interface VehicleService {

	public ServiceResponseDto createVehicle (VehicleDto vehicleDto);
	
	public ServiceResponseDto updateVehicle (VehicleDto vehicleDto);
	
	public ServiceResponseDto getVehicle (int vehicleId);
	
	public ServiceResponseDto fetchVehicles (int vehicleId);
	
	public ServiceResponseDto deleteVehicle (int vehicleId);
}
