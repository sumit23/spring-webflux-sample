package com.springsample.webflux.service;

import com.springsample.webflux.dto.VehicleDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VehicleAsyncService {

	Mono<VehicleDto> createVehicleAsync(VehicleDto vehicleDto);

	Mono<VehicleDto> getVehicleAsync(int vehicleId);
	
	Flux<VehicleDto> getVehicleListAsync();
	
	Mono<Void> updateVehicleAsync(VehicleDto vehicleDto);
	
	Mono<Void> deleteVehicleAsync(int vehicleId);
}
