package com.springsample.webflux.api.v1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsample.webflux.api.v1.request.VehicleRequest;
import com.springsample.webflux.dto.ServiceResponseDto;
import com.springsample.webflux.dto.VehicleDto;
import com.springsample.webflux.service.VehicleService;

import io.swagger.annotations.Api;

@RestController
@ConditionalOnProperty("vehicle.controller.enabled")
@RequestMapping("/v1/vehicle")
@Api(tags="Vehicle")
public class VehicleController {

	private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    
    private VehicleService vehicleService;
    
    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	/**
     * Api to create Vehicle
     *
     * @param createVehicleRequest
     * @return ResponseEntity
     * @author Sumit Singhal
     */
    @PostMapping("/")
    public ResponseEntity<Object> sendEmail(@RequestBody VehicleRequest createVehicleRequest) {
        try {
            VehicleDto vehicleDto = new VehicleDto()
                    .setColor(createVehicleRequest.getColor())
                    ;
            ServiceResponseDto response = vehicleService.createVehicle(vehicleDto);

            switch (response.getStatus()) {
                case SUCCESS:
                    return new ResponseEntity<>(HttpStatus.OK);
                case BAD_REQUEST:
                    return new ResponseEntity<>(response.getResponseObject(), HttpStatus.BAD_REQUEST);
                default:
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            logger.error("exception in create vehicle api: ", e);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
