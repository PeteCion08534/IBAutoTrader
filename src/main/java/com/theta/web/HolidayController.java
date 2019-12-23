package com.theta.web;

import com.theta.dao.HolidayDAO;

import com.theta.domain.Holiday;

import com.theta.service.HolidayService;

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
 * Spring MVC controller that handles CRUD requests for Holiday entities
 * 
 */

@Controller("HolidayController")
public class HolidayController {

	/**
	 * DAO injected by Spring that manages Holiday entities
	 * 
	 */
	@Autowired
	private HolidayDAO holidayDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Holiday entities
	 * 
	 */
	@Autowired
	private HolidayService holidayService;

	/**
	 * Entry point to show all Holiday entities
	 * 
	 */
	public String indexHoliday() {
		return "redirect:/indexHoliday";
	}

	/**
	 * Save an existing Holiday entity
	 * 
	 */
	@RequestMapping("/saveHoliday")
	public String saveHoliday(@ModelAttribute Holiday holiday) {
		holidayService.saveHoliday(holiday);
		return "forward:/indexHoliday";
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
	@RequestMapping("/holidayController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Delete an existing Holiday entity
	 * 
	 */
	@RequestMapping("/deleteHoliday")
	public String deleteHoliday(@RequestParam Integer holidayIdKey) {
		Holiday holiday = holidayDAO.findHolidayByPrimaryKey(holidayIdKey);
		holidayService.deleteHoliday(holiday);
		return "forward:/indexHoliday";
	}

	/**
	 * Select an existing Holiday entity
	 * 
	 */
	@RequestMapping("/selectHoliday")
	public ModelAndView selectHoliday(@RequestParam Integer holidayIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("holiday", holidayDAO.findHolidayByPrimaryKey(holidayIdKey));
		mav.setViewName("holiday/viewHoliday.jsp");

		return mav;
	}

	/**
	 * Edit an existing Holiday entity
	 * 
	 */
	@RequestMapping("/editHoliday")
	public ModelAndView editHoliday(@RequestParam Integer holidayIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("holiday", holidayDAO.findHolidayByPrimaryKey(holidayIdKey));
		mav.setViewName("holiday/editHoliday.jsp");

		return mav;
	}

	/**
	 * Create a new Holiday entity
	 * 
	 */
	@RequestMapping("/newHoliday")
	public ModelAndView newHoliday() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("holiday", new Holiday());
		mav.addObject("newFlag", true);
		mav.setViewName("holiday/editHoliday.jsp");

		return mav;
	}

	/**
	 * Show all Holiday entities
	 * 
	 */
	@RequestMapping("/indexHoliday")
	public ModelAndView listHolidays() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("holidays", holidayService.loadHolidays());

		mav.setViewName("holiday/listHolidays.jsp");

		return mav;
	}

	/**
	 * Select the Holiday entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteHoliday")
	public ModelAndView confirmDeleteHoliday(@RequestParam Integer holidayIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("holiday", holidayDAO.findHolidayByPrimaryKey(holidayIdKey));
		mav.setViewName("holiday/deleteHoliday.jsp");

		return mav;
	}
}