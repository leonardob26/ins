package com.ins.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the people database table.
 * 
 */
@Entity
@Table(name="people")
@NamedQuery(name="People.findAll", query="SELECT p FROM People p")
public class People implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String pname;

	//bi-directional many-to-many association to Vehicle
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="people_vehicle"
		, joinColumns={
			@JoinColumn(name="people_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="vehicle_id")
			}
		)
	private List<Vehicle> vehicles;

	public People() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public List<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

}