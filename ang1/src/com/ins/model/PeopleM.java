package com.ins.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class PeopleM {
	private int id;
	private String name;
	private Map<Integer, String> cmbVehicles = new LinkedHashMap<Integer, String>();
	private String vehiclesList;
	private int vehicleId;
	
	
	public void addElementMap(int id, String name){
		cmbVehicles.put(id, name);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<Integer, String> getCmbVehicles() {
		return cmbVehicles;
	}
	public void setCmbVehicles(Map<Integer, String> cmbVehicles) {
		this.cmbVehicles = cmbVehicles;
	}
	
	public String getVehiclesList() {
		return vehiclesList;
	}
	public void setVehiclesList(String vehiclesList) {
		this.vehiclesList = vehiclesList;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
}
