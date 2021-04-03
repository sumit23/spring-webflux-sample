package com.springsample.webflux.service;

import java.util.List;

import com.springsample.webflux.dto.ServiceResponseDto;
import com.springsample.webflux.dto.VehicleDto;

public interface VehicleService {

	ServiceResponseDto<VehicleDto> createVehicle(VehicleDto vehicleDto);
	
	ServiceResponseDto<VehicleDto> updateVehicle(VehicleDto vehicleDto);
	
	ServiceResponseDto<VehicleDto> getVehicle(int vehicleId);
	
	ServiceResponseDto<List<VehicleDto>> getVehiclesList();
	
	ServiceResponseDto<VehicleDto> deleteVehicle(int vehicleId);
}
