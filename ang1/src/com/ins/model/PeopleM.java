package com.ins.model;

import java.util.Map;

public class PeopleM {
	private int id;
	private String name;

	private Map<Integer, String> vehiclesPeople;

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
	
	public Map<Integer, String> getVehiclesPeople() {
		return vehiclesPeople;
	}
	public void setVehiclesPeople(Map<Integer, String> vehiclesPeople) {
		this.vehiclesPeople = vehiclesPeople;
	}
}
