package com.ins.model;



public interface IVehicle {
	public String getVehiclesList();
	/**
	 * 
	 * @param id
	 * @return Vehicle name
	 */
	public Vehicle getVehicle(int id);
	public int delete(int id);
	public int insert(String name);
	public void update(int id, String name);
}
