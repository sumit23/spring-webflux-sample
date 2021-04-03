package com.springsample.webflux.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.springsample.webflux.db.domain.Vehicle;
import com.springsample.webflux.db.repository.VehicleRepository;
import com.springsample.webflux.dto.VehicleDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class VehicleAsyncServiceImpl implements VehicleAsyncService{
	
	@Autowired
	private VehicleRepository vehicleRepo;

	@Override
	public Mono<VehicleDto> createVehicleAsync(VehicleDto vehicleDto) {
		CompletableFuture<VehicleDto> future = CompletableFuture.supplyAsync(() -> {
			Vehicle vehicleDomain = new Vehicle()
								.setModel(vehicleDto.getModel())
								.setMake(vehicleDto.getMake())
								.setColor(vehicleDto.getColor())
								.setVin(vehicleDto.getVin())
								.setYear(vehicleDto.getYear());
			vehicleDomain = vehicleRepo.save(vehicleDomain);
			vehicleDto.setId(vehicleDomain.getId());
			return vehicleDto;
		});
		Mono<VehicleDto> monoFromFuture = Mono.fromFuture(future);
		
		return monoFromFuture;
	}
	
	@Override
	public Mono<VehicleDto> getVehicleAsync(int vehicleId) {
		CompletableFuture<VehicleDto> future = CompletableFuture.supplyAsync(() -> {
			
			Optional<Vehicle> vehicleOptional = vehicleRepo.findById(vehicleId);
			VehicleDto dto = null;
			if (vehicleOptional.isPresent()) {
				Vehicle v = vehicleOptional.get();
				dto = new VehicleDto().setColor(v.getColor())
						.setId(v.getId())
						.setMake(v.getMake())
						.setModel(v.getModel())
						.setVin(v.getVin())
						.setYear(v.getYear());
			}
			return dto;
		});
		Mono<VehicleDto> monoFromFuture = Mono.fromFuture(future);
		
		return monoFromFuture;
	}

	@Override
	public Flux<VehicleDto> getVehicleListAsync() {
		return Flux.create((emitter) -> {
			CompletableFuture<List<VehicleDto>> future = CompletableFuture.supplyAsync( () -> {
				List<Vehicle> vehicleList = vehicleRepo.findAll(PageRequest.of(0, 1000)).getContent();
				List<VehicleDto> vehicleDtoList = vehicleList.parallelStream().map( (vehicle) -> {
					return new VehicleDto().setColor(vehicle.getColor())
							.setId(vehicle.getId())
							.setMake(vehicle.getMake())
							.setModel(vehicle.getModel())
							.setVin(vehicle.getVin())
							.setYear(vehicle.getYear());
				}).collect(Collectors.toList());
				return vehicleDtoList;
			});
			future.whenComplete( (vehicleDtoList, exception) -> {
				if (exception == null) {
					vehicleDtoList.forEach(emitter::next);
					emitter.complete();
				} else {
					emitter.complete();
				}
			});
		});
	}

	@Override
	public Mono<Void> updateVehicleAsync(VehicleDto vehicleDto) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			Vehicle vehicleDomain = new Vehicle()
					.setModel(vehicleDto.getModel())
					.setMake(vehicleDto.getMake())
					.setColor(vehicleDto.getColor())
					.setVin(vehicleDto.getVin())
					.setYear(vehicleDto.getYear())
					.setId(vehicleDto.getId());
			vehicleDomain = vehicleRepo.save(vehicleDomain);
		});
		Mono<Void> monoFromFuture = Mono.fromFuture(future);
		
		return monoFromFuture;
	}

	@Override
	public Mono<Void> deleteVehicleAsync(int vehicleId) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			
			Optional<Vehicle> vehicleOptional = vehicleRepo.findById(vehicleId);
			if (vehicleOptional.isPresent()) {
				vehicleRepo.delete(vehicleOptional.get());;
			}
		});
		Mono<Void> monoFromFuture = Mono.fromFuture(future);
		
		return monoFromFuture;
	}
}
