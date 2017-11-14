package com.ins.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ins.jpa.People;
import com.ins.jpa.Vehicle;
import com.ins.repositories.PeopleRepository;
import com.ins.repositories.VehicleRepository;

@Service
public class PeopleImpl implements IPeople {

	@Autowired
	PeopleRepository repo;
	@Autowired
	VehicleRepository repoVehicle;

	@Override
	public List<PeopleM> getPeoplesList() {
		List<PeopleM> lsp = new ArrayList<PeopleM>() ;
		
		for (People people: repo.findAll()) {
			PeopleM pe = new PeopleM();
			pe.setId(people.getId());
			pe.setName(people.getPname());
			lsp.add(pe);
		}

		return lsp;
	}

	@Override
	public PeopleM getPeopleVehicle(int idPeople) {
		PeopleM people = new PeopleM();
		People peopleJpa = repo.findOne(idPeople);
		people.setId(idPeople);
		people.setName(peopleJpa.getPname());
		List<Vehicle> vehiclesList = peopleJpa.getVehicles();

		Map<Integer, String> vehiclesPeople =  new HashMap<Integer, String>();
		for (Vehicle vehicle : vehiclesList) {
			vehiclesPeople.put(vehicle.getId(), vehicle.getVname());
		}
		people.setVehiclesPeople(vehiclesPeople);
		return people;
	/*	for (Vehicle vehicle : vehiclesList)
			vehiclesIdAdded.add(vehicle.getId());*/

		/*for (Vehicle vehicle : repoVehicle.findAll()) {
			if (!vehiclesIdAdded.contains(vehicle.getId()))
				people.addElementMap(vehicle.getId(), vehicle.getVname());
		}*/
		
	}

	@Override
	public com.ins.jpa.People getPeople(int id) {
		com.ins.jpa.People people = new com.ins.jpa.People();
		if (id == 0) {
			people.setPname("");
			return people;
		}

		return repo.findOne(id);

	}

	@Override
	public int insert(String pname) {
		People people = new People();
		people.setPname(pname);
		repo.save(people);
		return people.getId();
	}

	@Override
	public int delete(int id) {
		repo.delete(id);
		return 1;
	}

	@Override
	public int update(int id, String pname) {
		People people = new People();
		people.setId(id);
		people.setPname(pname);
		repo.save(people);
		return people.getId();
	}

	@Override
	public int insertPeopleVehicles(int id, int vehicleId) {
		People people = repo.findOne(id);

		Vehicle vehicle = new Vehicle();
		vehicle.setId(vehicleId);

		List<Vehicle> vehicles = people.getVehicles();
		vehicles.add(vehicle);

		people.setVehicles(vehicles);
		repo.save(people);

		return people.getId();
	}

	/*
	 * @Override public void getOnePeopleVehicle(int id, int vehicleId) { People
	 * people = repo.findOne(arg0)
	 * 
	 * String sql = "SELECT * FROM people_vehicles where id=?"; PreparedStatement
	 * sel = db.conn.prepareStatement(sql); sel.setInt(1, idPeopleVehicles);
	 * ResultSet rs = sel.executeQuery(); if (rs.next()){ this.id =
	 * rs.getInt("people_id"); this.vehicleId = rs.getInt("vehicles_id"); }
	 * rs.close();
	 * 
	 * }
	 */
	@Override
	public int deletePeopleVehicles(int id, int vehicleId) {
		People people = repo.findOne(id);
		List<Vehicle> vehicles = people.getVehicles();
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getId() == vehicleId) {
				vehicles.remove(vehicle);
				break;
			}
		}
		return repo.save(people).getId();

	}
}
