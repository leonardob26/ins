package com.ins.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ins.jpa.Vehicle;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
	List<Vehicle> findByVname(String vname);
}
