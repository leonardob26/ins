package com.ins.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ins.jpa.Vehicle;
import com.ins.repositories.VehicleRepository;

@Service
public class VehicleImpl implements IVehicle {
	@Autowired
	VehicleRepository repo;
	
	@Override
	public String getVehiclesList() {	
		StringBuilder sb = new StringBuilder();
		for (Vehicle vehicle : repo.findAll()){
			sb.append("<div class='row'><div class='col-md-5'><a href=selVehicles.do?id=" +  
					vehicle.getId() + ">" + vehicle.getVname() + "</a></div></div>");
		}
		return sb.length()>0?sb.toString():"";
	}

	@Override
	public com.ins.model.Vehicle getVehicle(int id) {
		com.ins.model.Vehicle vehicle = new com.ins.model.Vehicle();
		if (id!=0){
			Vehicle vehicleJpa = repo.findOne(id);
			vehicle.setId(id);
			vehicle.setName(vehicleJpa.getVname());
		}
		return vehicle;
	}

	@Override
	public int delete(int id) {
		repo.delete(id);
		return 0;
	}

	@Override
	public int insert(String name) {
		Vehicle vehicle = new Vehicle();
		vehicle.setVname(name);
		repo.save(vehicle);
		return vehicle.getId();
	}

	@Override
	public void update(int id, String name) {
		try {
			Vehicle veh;
			if (repo.exists(1)){
				veh = repo.findOne(id);
				veh.setVname(name);
				repo.save(veh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
