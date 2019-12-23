package com.theta.web;

import com.theta.dao.ThetaExceptionDAO;

import com.theta.domain.ThetaException;

import com.theta.service.ThetaExceptionService;

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
 * Spring MVC controller that handles CRUD requests for ThetaException entities
 * 
 */

@Controller("ThetaExceptionController")
public class ThetaExceptionController {

	/**
	 * DAO injected by Spring that manages ThetaException entities
	 * 
	 */
	@Autowired
	private ThetaExceptionDAO thetaExceptionDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for ThetaException entities
	 * 
	 */
	@Autowired
	private ThetaExceptionService thetaExceptionService;

	/**
	 * Show all ThetaException entities
	 * 
	 */
	@RequestMapping("/indexThetaException")
	public ModelAndView listThetaExceptions() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("thetaexceptions", thetaExceptionService.loadThetaExceptions());

		mav.setViewName("thetaexception/listThetaExceptions.jsp");

		return mav;
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
	 */
	@RequestMapping("/thetaexceptionController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Delete an existing ThetaException entity
	 * 
	 */
	@RequestMapping("/deleteThetaException")
	public String deleteThetaException(@RequestParam Integer thetaExceptionIdKey) {
		ThetaException thetaexception = thetaExceptionDAO.findThetaExceptionByPrimaryKey(thetaExceptionIdKey);
		thetaExceptionService.deleteThetaException(thetaexception);
		return "forward:/indexThetaException";
	}

	/**
	 * Edit an existing ThetaException entity
	 * 
	 */
	@RequestMapping("/editThetaException")
	public ModelAndView editThetaException(@RequestParam Integer thetaExceptionIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("thetaexception", thetaExceptionDAO.findThetaExceptionByPrimaryKey(thetaExceptionIdKey));
		mav.setViewName("thetaexception/editThetaException.jsp");

		return mav;
	}

	/**
	 * Save an existing ThetaException entity
	 * 
	 */
	@RequestMapping("/saveThetaException")
	public String saveThetaException(@ModelAttribute ThetaException thetaexception) {
		thetaExceptionService.saveThetaException(thetaexception);
		return "forward:/indexThetaException";
	}

	/**
	 * Select the ThetaException entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteThetaException")
	public ModelAndView confirmDeleteThetaException(@RequestParam Integer thetaExceptionIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("thetaexception", thetaExceptionDAO.findThetaExceptionByPrimaryKey(thetaExceptionIdKey));
		mav.setViewName("thetaexception/deleteThetaException.jsp");

		return mav;
	}

	/**
	 * Create a new ThetaException entity
	 * 
	 */
	@RequestMapping("/newThetaException")
	public ModelAndView newThetaException() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("thetaexception", new ThetaException());
		mav.addObject("newFlag", true);
		mav.setViewName("thetaexception/editThetaException.jsp");

		return mav;
	}

	/**
	 * Entry point to show all ThetaException entities
	 * 
	 */
	public String indexThetaException() {
		return "redirect:/indexThetaException";
	}

	/**
	 * Select an existing ThetaException entity
	 * 
	 */
	@RequestMapping("/selectThetaException")
	public ModelAndView selectThetaException(@RequestParam Integer thetaExceptionIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("thetaexception", thetaExceptionDAO.findThetaExceptionByPrimaryKey(thetaExceptionIdKey));
		mav.setViewName("thetaexception/viewThetaException.jsp");

		return mav;
	}
}