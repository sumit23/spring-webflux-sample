package com.springsample.webflux.api.v1.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsample.webflux.api.v1.request.VehicleRequest;
import com.springsample.webflux.dto.ServiceResponseDto;
import com.springsample.webflux.dto.VehicleDto;
import com.springsample.webflux.service.VehicleService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/v1/vehicle")
@Api(tags="Vehicle")
public class VehicleController {

	private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

	@Autowired
    private VehicleService vehicleService;
    
    @PostMapping
    public ResponseEntity<Object> createVehicle(@RequestBody VehicleRequest req) {
        try {
            VehicleDto vehicleDto = new VehicleDto()
                    .setColor(req.getColor())
                    .setMake(req.getMake())
                    .setModel(req.getModel())
                    .setVin(req.getVin())
                    .setYear(req.getYear());
            ServiceResponseDto<VehicleDto> response = vehicleService.createVehicle(vehicleDto);

            switch (response.getStatus()) {
                case SUCCESS:
                    return new ResponseEntity<>(vehicleDto, HttpStatus.CREATED);
                case BAD_REQUEST:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                default:
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            logger.error("exception in create vehicle api: ", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> getVehicleById (@PathVariable("id") int id) {
    	ServiceResponseDto<VehicleDto> response = vehicleService.getVehicle(id);
    	switch (response.getStatus()) {
	        case SUCCESS:
	            return new ResponseEntity<VehicleDto>(response.getResponseObject(), HttpStatus.OK);
	        case NOT_FOUND:
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        default:
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @GetMapping
    public ResponseEntity<List<VehicleDto>> getVehicleList () {
    	ServiceResponseDto<List<VehicleDto>> response = vehicleService.getVehiclesList();
    	switch (response.getStatus()) {
	        case SUCCESS:
	            return new ResponseEntity<List<VehicleDto>>(response.getResponseObject(), HttpStatus.OK);
	        case NOT_FOUND:
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        default:
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @PutMapping
    public ResponseEntity<Object> updateVehicle(@RequestBody VehicleRequest req) {
        try {
            VehicleDto vehicleDto = new VehicleDto()
                    .setColor(req.getColor())
                    .setMake(req.getMake())
                    .setModel(req.getModel())
                    .setVin(req.getVin())
                    .setYear(req.getYear())
                    .setId(req.getId());
            ServiceResponseDto<VehicleDto> response = vehicleService.updateVehicle(vehicleDto);

            switch (response.getStatus()) {
                case SUCCESS:
                    return new ResponseEntity<>(HttpStatus.ACCEPTED);
                case BAD_REQUEST:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                default:
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            logger.error("exception in update vehicle api: ", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleDto> deleteVehicleById (@PathVariable("id") int id) {
    	ServiceResponseDto<VehicleDto> response = vehicleService.deleteVehicle(id);
    	switch (response.getStatus()) {
	        case SUCCESS:
	            return new ResponseEntity<VehicleDto>(HttpStatus.ACCEPTED);
	        case NOT_FOUND:
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        default:
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }

}
