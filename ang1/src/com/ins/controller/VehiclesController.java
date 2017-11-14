package com.ins.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ins.model.VehicleM;
import com.ins.model.VehicleImpl;
import com.ins.model.logger.Errors;

@RestController
@RequestMapping("/vehicle")
public class VehiclesController {
	@Autowired
	private VehicleImpl vehicle;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<VehicleM> getVehiclesList(HttpServletRequest request){
		return vehicle.getVehiclesList();
	}
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public VehicleM selVehicles(HttpServletRequest request, @PathVariable int id){
		return vehicle.getVehicle(id);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(HttpServletRequest request, @PathVariable int id) {
		/*HttpSession session = request.getSession();
		if (session.getAttribute("rol")==null || !UserImpl.isAuthorizedWrite((Integer) session.getAttribute("rol"), "Products")) 
			return "ACCESS DENIED";*/
		return String.valueOf(vehicle.delete(id));
	}
	@RequestMapping(value = "/{name}", method = RequestMethod.POST)
	public String insert(HttpServletRequest request, @PathVariable String name) {
		/*HttpSession session = request.getSession();
		if (session.getAttribute("rol")==null || !UserImpl.isAuthorizedWrite((Integer) session.getAttribute("rol"), "Products")) 
			return "ACCESS DENIED";*/
		return String.valueOf(vehicle.insert(name));
	}
	@RequestMapping(value = "/{id}/{name}", method = RequestMethod.PUT)
	public String update(HttpServletRequest request, @PathVariable int id, @PathVariable String name) {
		/*HttpSession session = request.getSession();
		if (session.getAttribute("rol")==null || !UserImpl.isAuthorizedWrite((Integer) session.getAttribute("rol"), "Products")) 
			return "ACCESS DENIED";*/
		vehicle.update(id,name);
		return "SUCCESS";
	}
}
