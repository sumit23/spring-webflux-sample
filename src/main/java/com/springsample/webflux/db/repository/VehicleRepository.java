package com.springsample.webflux.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsample.webflux.db.domain.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}
