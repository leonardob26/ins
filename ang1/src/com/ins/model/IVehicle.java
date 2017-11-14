package com.ins.model;

import java.util.List;

public interface IVehicle {
	public List<StructVehicle> getVehiclesList();
	/**
	 * 
	 * @param id
	 * @return Vehicle name
	 */
	public StructVehicle getVehicle(int id);
	public int delete(int id);
	public int insert(String name);
	public void update(int id, String name);
}
