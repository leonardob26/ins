package com.ins.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ins.model.Vehicle;
import com.ins.model.VehicleImpl;
import com.ins.model.logger.Errors;
@Controller
public class VehiclesController {
	@Autowired
	private VehicleImpl vehicle;
	
	@RequestMapping(path = "/vehiclesList.do", method = RequestMethod.GET)
	public ModelAndView getVehiclesList(){
		try {			
			return new ModelAndView("vehiclesList", "vehiclesList", vehicle.getVehiclesList());
		} catch (Exception e) {
			return new ModelAndView("error", "error", Errors.getError(e));
		} 
	}
	@RequestMapping(path = "/selVehicles.do", method = RequestMethod.GET)
	public ModelAndView selVehicles( @RequestParam("id") int id){
		try {
			Vehicle veh = vehicle.getVehicle(id);
			return new ModelAndView("vehicles", "vehicles", veh);
		} catch (Exception e) {
			return new ModelAndView("error", "error", Errors.getError(e));
		} 
	}
	@RequestMapping(value = "/vehicles.do", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("vehicle")Vehicle veh, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "error";
        }
        try {
			String action = request.getParameter("submit");
			switch (action) {
			case "Update":
				if (veh.getId() == 0)
					vehicle.insert(veh.getName());
				else
					vehicle.update(veh.getId(), veh.getName());
				break;
			case "Delete":
				vehicle.delete(veh.getId());
				break;
			default:
				break;
			}
        } catch (Exception e) {
			return "error";
		} 
		return "redirect:/vehiclesList.do";
	}
	

}
