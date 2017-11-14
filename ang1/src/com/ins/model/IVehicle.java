package com.ins.model;

import java.util.List;

public interface IVehicle {
	public List<VehicleM> getVehiclesList();
	/**
	 * 
	 * @param id
	 * @return Vehicle name
	 */
	public VehicleM getVehicle(int id);
	public int delete(int id);
	public int insert(String name);
	public void update(int id, String name);
}
