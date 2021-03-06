package com.ins.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.ins.jpa.Vehicle;
import com.ins.repositories.VehicleRepository;

@Service
public class VehicleImpl implements IVehicle {
	@Autowired
	VehicleRepository repo;

	@Override
	public List<StructVehicle> getVehiclesList() {
		List<StructVehicle> lsv = new ArrayList();

		for (Vehicle vehicle : repo.findAll()) {
			StructVehicle ve = new StructVehicle();
			ve.setId(vehicle.getId());
			ve.setName(vehicle.getVname());
			lsv.add(ve);
		}

		return lsv;
	}

	@Override
	public StructVehicle getVehicle(int id) {
		StructVehicle vehicle = new StructVehicle();
		if (id != 0) {
			Vehicle ve = repo.findOne(id);
			vehicle.setId(ve.getId());
			vehicle.setName(ve.getVname());
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
	public int update(int id, String name) {
		Vehicle veh;
		if (repo.exists(1)) {
			veh = repo.findOne(id);
			try {

				veh.setVname(name);
				repo.save(veh);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return veh.getId();
		}
		return 0;
	}
}
