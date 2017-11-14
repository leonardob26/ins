package com.ins.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

import com.ins.model.PeopleM;
import com.ins.model.PeopleImpl;
import com.ins.model.logger.Errors;

@RestController
@RequestMapping("/people")
public class PeopleController {
	@Autowired
	private PeopleImpl people;

	@RequestMapping(path = "/peopleList", method = RequestMethod.GET)
	public List<com.ins.jpa.People> getPeopleList(HttpServletRequest request) {
		/*HttpSession session = request.getSession();
		if (session.getAttribute("rol")==null || !UserImpl.isAuthorized((Integer) session.getAttribute("rol"), "Products")) 
			return null;*/
		return people.getPeoplesList();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public com.ins.jpa.People selPeople(HttpServletRequest request, @PathVariable int id) {
		return people.getPeople(id);
	}

	@RequestMapping(path = "/delPeopleVehicles", method = RequestMethod.GET)
	public ModelAndView delPersonVehicles(@RequestParam("idPeople") int idPeople, @RequestParam("idVehicle") int idVehicle) {
		try {
			people.deletePeopleVehicles(idPeople, idVehicle);
			return new ModelAndView("redirect:/peopleList.do");
		} catch (Exception e) {
			return new ModelAndView("error", "error", Errors.getError(e));
		} 
	}

	@RequestMapping(value = "/people", method = RequestMethod.POST)
	public ModelAndView submit(@Valid @ModelAttribute("employee") PeopleM people, BindingResult result,
			HttpServletRequest request, Map<String, Object> model) {
		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		try {
			String action = request.getParameter("submit");
			switch (action) {
			case "Update":
				if (people.getId() == 0)
					this.people.insert(people.getName());
				else
					this.people.update(people.getId(), people.getName());
				break;
			case "Delete":
				this.people.delete(people.getId());
				break;
			case "AddCarSel":
				//this.people.setVehicleId(0);
				people = this.people.getPeopleVehicle(people.getId());
				model.put("name", people.getName());
				model.put("cmbVehicles", people.getCmbVehicles());
				return new ModelAndView("peoplevehicles", "people", people);
			default:
				break;
			}
			return new ModelAndView("redirect:/peopleList.do");
		} catch (Exception e) {
			return new ModelAndView("error", "error", Errors.getError(e));
		} 
	}

	@RequestMapping(value = "/addPeopleVehicles", method = RequestMethod.POST)
	public ModelAndView submitAddPersonVehicles(@Valid @ModelAttribute("employee") PeopleM people,
			BindingResult result, HttpServletRequest request, Map<String, Object> model) {
		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		try {
			String action = request.getParameter("submit");
			switch (action) {
			case "InsertPeopleVehicle":
				this.people.insertPeopleVehicles(people.getId(), people.getVehicleId());
				break;
			default:
				break;
			}
			return new ModelAndView("redirect:/selPeople.do?id=" + people.getId());
		} catch (Exception e) {
			return new ModelAndView("error", "error", Errors.getError(e));
		} 
	}

}
