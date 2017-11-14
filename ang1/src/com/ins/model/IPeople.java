package com.ins.model;

import java.util.List;

public interface IPeople {
	public List<com.ins.jpa.People> getPeoplesList();
	public int insert(String pname);
	public int delete(int id);
	public int update(int id, String pname);
	public com.ins.jpa.People getPeople(int id);
	
	public PeopleM getPeopleVehicle(int id);
	/*public void getOnePeopleVehicle(int id, int vehicleId)*/
	
	public int insertPeopleVehicles(int id, int vehicleId);
	public int deletePeopleVehicles(int id, int vehicleId) ;
	
}
