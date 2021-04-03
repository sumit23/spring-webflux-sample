package com.springsample.webflux.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.springsample.webflux.db.domain.Vehicle;
import com.springsample.webflux.db.repository.VehicleRepository;
import com.springsample.webflux.dto.ServiceResponseDto;
import com.springsample.webflux.dto.VehicleDto;
import com.springsample.webflux.util.Constants;

@Component
public class VehicleServiceImpl implements VehicleService{
	
	@Autowired
	private VehicleRepository vehicleRepo;
	
	@Override
	public ServiceResponseDto<VehicleDto> createVehicle(VehicleDto vehicleDto) {
		ServiceResponseDto<VehicleDto> responseDto = new ServiceResponseDto<VehicleDto>();
		Vehicle vehicleDomain = new Vehicle()
				.setModel(vehicleDto.getModel())
				.setMake(vehicleDto.getMake())
				.setColor(vehicleDto.getColor())
				.setVin(vehicleDto.getVin())
				.setYear(vehicleDto.getYear());
		vehicleDomain = vehicleRepo.save(vehicleDomain);
		vehicleDto.setId(vehicleDomain.getId());
		responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		return responseDto;
	}

	@Override
	public ServiceResponseDto<VehicleDto> updateVehicle(VehicleDto vehicleDto) {
		ServiceResponseDto<VehicleDto> responseDto = new ServiceResponseDto<VehicleDto>();
		responseDto.setStatus(Constants.StatusCodes.NOT_FOUND);
		
		Optional<Vehicle> vehicleOptional = vehicleRepo.findById(vehicleDto.getId());
		vehicleOptional.ifPresent( vehicle -> {
			
			Vehicle vehicleDomain = new Vehicle()
					.setColor(vehicleDto.getColor())
					.setId(vehicleDto.getId())
					.setMake(vehicleDto.getMake())
					.setModel(vehicleDto.getModel())
					.setVin(vehicleDto.getVin())
					.setYear(vehicleDto.getYear());
			vehicleRepo.save(vehicleDomain);
			
			responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		});

		return responseDto;
	}

	@Override
	public ServiceResponseDto<VehicleDto> getVehicle(int vehicleId) {
		Optional<Vehicle> vehicleOptional = vehicleRepo.findById(vehicleId);
		ServiceResponseDto<VehicleDto> responseDto = new ServiceResponseDto<VehicleDto>();
		VehicleDto dto = null;
		responseDto.setStatus(Constants.StatusCodes.NOT_FOUND);
		if (vehicleOptional.isPresent()) {
			Vehicle v = vehicleOptional.get();
			dto = new VehicleDto().setColor(v.getColor())
					.setId(v.getId())
					.setMake(v.getMake())
					.setModel(v.getModel())
					.setVin(v.getVin())
					.setYear(v.getYear());
			responseDto.setResponseObject(dto);
			responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		}
		return responseDto;
	}

	@Override
	public ServiceResponseDto<List<VehicleDto>> getVehiclesList() {
		ServiceResponseDto<List<VehicleDto>> responseDto = new ServiceResponseDto<List<VehicleDto>>();
		
		List<Vehicle> vehicleList = vehicleRepo.findAll(PageRequest.of(0, 1000)).getContent();
		
		List<VehicleDto> vehicleDtoList = vehicleList.parallelStream().map( (vehicle) -> {
			return new VehicleDto().setColor(vehicle.getColor())
					.setId(vehicle.getId())
					.setMake(vehicle.getMake())
					.setModel(vehicle.getModel())
					.setVin(vehicle.getVin())
					.setYear(vehicle.getYear());
		}).collect(Collectors.toList());
		
		responseDto.setResponseObject(vehicleDtoList);
		responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		return responseDto;
	}

	@Override
	public ServiceResponseDto<VehicleDto> deleteVehicle(int vehicleId) {
		ServiceResponseDto<VehicleDto> responseDto = new ServiceResponseDto<VehicleDto>();
		responseDto.setStatus(Constants.StatusCodes.NOT_FOUND);
		
		Optional<Vehicle> vehicleOptional = vehicleRepo.findById(vehicleId);
		vehicleOptional.ifPresent( vehicle -> {
			
			vehicleRepo.delete(vehicle);
			responseDto.setStatus(Constants.StatusCodes.SUCCESS);
		});

		return responseDto;
	}
	
}
