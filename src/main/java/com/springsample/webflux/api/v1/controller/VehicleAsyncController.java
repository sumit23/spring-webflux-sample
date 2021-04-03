package com.springsample.webflux.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springsample.webflux.api.v1.request.VehicleRequest;
import com.springsample.webflux.dto.VehicleDto;
import com.springsample.webflux.service.VehicleAsyncService;

import io.swagger.annotations.Api;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/vehicle-async")
@Api(tags="Vehicle-Async")
public class VehicleAsyncController {

	@Autowired
    private VehicleAsyncService vehicleAsyncService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<VehicleDto> createVehicleAsync(@RequestBody VehicleRequest req) {
    	VehicleDto vehicleDto = new VehicleDto()
                .setColor(req.getColor())
                .setMake(req.getMake())
                .setModel(req.getModel())
                .setVin(req.getVin())
                .setYear(req.getYear());
        return vehicleAsyncService.createVehicleAsync(vehicleDto);
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<VehicleDto> getVehicleByIdAsync(@PathVariable("id") int id) {
    	return vehicleAsyncService.getVehicleAsync(id);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<VehicleDto> getVehicleListAsync() {
    	return vehicleAsyncService.getVehicleListAsync();
    }
    
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> updateVehicle(@RequestBody VehicleRequest req) {
    	VehicleDto vehicleDto = new VehicleDto()
                .setColor(req.getColor())
                .setMake(req.getMake())
                .setModel(req.getModel())
                .setVin(req.getVin())
                .setYear(req.getYear())
                .setId(req.getId());
    	return vehicleAsyncService.updateVehicleAsync(vehicleDto);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> deleteVehicleById (@PathVariable("id") int id) {
    	return vehicleAsyncService.deleteVehicleAsync(id);
    }
    
}
