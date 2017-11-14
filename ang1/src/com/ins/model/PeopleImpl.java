package com.ins.model;

import java.util.ArrayList;
import java.util.List;
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
	public List<People> getPeoplesList() {
		List<People> people =  (List<People>) repo.findAll();
		return people;
	}
	
	@Override
	public com.ins.model.PeopleM getPeopleVehicle(int idPeople) {
		com.ins.model.PeopleM people = new com.ins.model.PeopleM();
		People peopleJpa = repo.findOne(idPeople);
		people.setId(idPeople);
		people.setName(peopleJpa.getPname());
		List<Vehicle> vehiclesAddList = peopleJpa.getVehicles();
		
		List<Integer> vehiclesIdAdded = new ArrayList<Integer>();
		
		for (Vehicle vehicle : vehiclesAddList) 
			vehiclesIdAdded.add(vehicle.getId());
			
		for (Vehicle vehicle : repoVehicle.findAll()) {
			if (!vehiclesIdAdded.contains(vehicle.getId()))
				people.addElementMap(vehicle.getId(), vehicle.getVname());
		}
		return people;
	}
	@Override
	public com.ins.jpa.People getPeople(int id) {
		com.ins.jpa.People people = new com.ins.jpa.People();
		if (id==0){
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
/*	@Override
	public void getOnePeopleVehicle(int id, int vehicleId) {
		People people = repo.findOne(arg0)
		
		String sql = "SELECT * FROM people_vehicles where id=?";
		PreparedStatement sel = db.conn.prepareStatement(sql);
		sel.setInt(1, idPeopleVehicles);
		ResultSet rs = sel.executeQuery();
		if (rs.next()){
			this.id = rs.getInt("people_id");
			this.vehicleId = rs.getInt("vehicles_id");
		}
		rs.close();
		
	}*/
	@Override
	public int deletePeopleVehicles(int id, int vehicleId) {
		People people = repo.findOne(id);
		List<Vehicle> vehicles = people.getVehicles();
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getId()==vehicleId){
				vehicles.remove(vehicle);
				break;
			}
		}
		return repo.save(people).getId();		
		
	}
}
