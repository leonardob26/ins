package com.ins.model;

public interface IPeople {
	public String getPeoplesList();
	public int insert(String pname);
	public int delete(int id);
	public int update(int id, String pname);
	public People getPeople(int id);
	
	public People getPeopleVehicle(int id);
	/*public void getOnePeopleVehicle(int id, int vehicleId)*/
	
	public int insertPeopleVehicles(int id, int vehicleId);
	public int deletePeopleVehicles(int id, int vehicleId) ;
	
}
