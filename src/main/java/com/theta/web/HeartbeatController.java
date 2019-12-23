package com.theta.web;

import com.theta.dao.HeartbeatDAO;

import com.theta.domain.Heartbeat;

import com.theta.service.HeartbeatService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for Heartbeat entities
 * 
 */

@Controller("HeartbeatController")
public class HeartbeatController {

	/**
	 * DAO injected by Spring that manages Heartbeat entities
	 * 
	 */
	@Autowired
	private HeartbeatDAO heartbeatDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Heartbeat entities
	 * 
	 */
	@Autowired
	private HeartbeatService heartbeatService;

	/**
	 * Select the Heartbeat entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteHeartbeat")
	public ModelAndView confirmDeleteHeartbeat(@RequestParam Integer heartbeatIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("heartbeat", heartbeatDAO.findHeartbeatByPrimaryKey(heartbeatIdKey));
		mav.setViewName("heartbeat/deleteHeartbeat.jsp");
		
		return mav;
	}

	/**
	 * Entry point to show all Heartbeat entities
	 * 
	 */
	public String indexHeartbeat() {
		return "redirect:/indexHeartbeat";
	}

	/**
	 * Save an existing Heartbeat entity
	 * 
	 */
	@RequestMapping("/saveHeartbeat")
	public String saveHeartbeat(@ModelAttribute Heartbeat heartbeat) {
		heartbeatService.saveHeartbeat(heartbeat);
		return "forward:/indexHeartbeat";
	}

	/**
	 * Edit an existing Heartbeat entity
	 * 
	 */
	@RequestMapping("/editHeartbeat")
	public ModelAndView editHeartbeat(@RequestParam Integer heartbeatIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("heartbeat", heartbeatDAO.findHeartbeatByPrimaryKey(heartbeatIdKey));
		mav.setViewName("heartbeat/editHeartbeat.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/heartbeatController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Select an existing Heartbeat entity
	 * 
	 */
	@RequestMapping("/selectHeartbeat")
	public ModelAndView selectHeartbeat(@RequestParam Integer heartbeatIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("heartbeat", heartbeatDAO.findHeartbeatByPrimaryKey(heartbeatIdKey));
		mav.setViewName("heartbeat/viewHeartbeat.jsp");

		return mav;
	}

	/**
	 * Delete an existing Heartbeat entity
	 * 
	 */
	@RequestMapping("/deleteHeartbeat")
	public String deleteHeartbeat(@RequestParam Integer heartbeatIdKey) {
		Heartbeat heartbeat = heartbeatDAO.findHeartbeatByPrimaryKey(heartbeatIdKey);
		heartbeatService.deleteHeartbeat(heartbeat);
		return "forward:/indexHeartbeat";
	}

	/**
	 * Register custom, context-specific property editors
	 * 
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register static property editors.
		binder.registerCustomEditor(java.util.Calendar.class, new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class, new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Double.class, true));
	}

	/**
	 * Create a new Heartbeat entity
	 * 
	 */
	@RequestMapping("/newHeartbeat")
	public ModelAndView newHeartbeat() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("heartbeat", new Heartbeat());
		mav.addObject("newFlag", true);
		mav.setViewName("heartbeat/editHeartbeat.jsp");

		return mav;
	}

	/**
	 * Show all Heartbeat entities
	 * 
	 */
	@RequestMapping("/indexHeartbeat")
	public ModelAndView listHeartbeats() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("heartbeats", heartbeatService.loadHeartbeats());

		mav.setViewName("heartbeat/listHeartbeats.jsp");

		return mav;
	}
}