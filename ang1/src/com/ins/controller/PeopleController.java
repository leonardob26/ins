package com.ins.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ins.model.PeopleM;
import com.ins.model.PeopleImpl;

@RestController
@RequestMapping("/people")
public class PeopleController {
	@Autowired
	private PeopleImpl people;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<PeopleM> getPeopleList(HttpServletRequest request) {
		/*
		 * HttpSession session = request.getSession(); if
		 * (session.getAttribute("rol")==null || !UserImpl.isAuthorized((Integer)
		 * session.getAttribute("rol"), "Products")) return null;
		 */
		return people.getPeoplesList();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public PeopleM selPeople(HttpServletRequest request, @PathVariable int id) {
		return people.getPeopleVehicle(id); // people.getPeople(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(HttpServletRequest request, @PathVariable int id) {
		return String.valueOf(people.delete(id));
	}

	@RequestMapping(path = "/{idPeople}/{idVehicle}", method = RequestMethod.DELETE)
	public String delPersonVehicles(HttpServletRequest request, @PathVariable int idPeople,
			@PathVariable int idVehicle) {
		return String.valueOf(people.deletePeopleVehicles(idPeople, idVehicle));
	}

	// , @PathVariable String vehiclesId
	@RequestMapping(value = "/{name}", method = RequestMethod.POST)
	public String addPeople(HttpServletRequest request, @PathVariable String name) {
		return String.valueOf(people.insert(name));
	}

	@RequestMapping(value = "/{peopleId}/{vehicleId}", method = RequestMethod.POST)
	public String submitAddPersonVehicles(HttpServletRequest request, @PathVariable int peopleId,
			@PathVariable int vehicleId) {
		return String.valueOf(people.insertPeopleVehicles(peopleId, vehicleId));
	}
	
	@RequestMapping(value = "/{id}/{name}", method = RequestMethod.PUT)
	public String updatePeople(HttpServletRequest request, @PathVariable int id, @PathVariable String name) {
		return String.valueOf(people.update(id, name));

	}	

}
