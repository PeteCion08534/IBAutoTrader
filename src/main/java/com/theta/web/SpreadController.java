package com.theta.web;

import com.theta.dao.PositionDAO;
import com.theta.dao.SpreadDAO;

import com.theta.domain.Position;
import com.theta.domain.Spread;

import com.theta.service.SpreadService;

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
 * Spring MVC controller that handles CRUD requests for Spread entities
 * 
 */

@Controller("SpreadController")
public class SpreadController {

	/**
	 * DAO injected by Spring that manages Position entities
	 * 
	 */
	@Autowired
	private PositionDAO positionDAO;

	/**
	 * DAO injected by Spring that manages Spread entities
	 * 
	 */
	@Autowired
	private SpreadDAO spreadDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Spread entities
	 * 
	 */
	@Autowired
	private SpreadService spreadService;

	/**
	 * Select the child Position entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSpreadPosition")
	public ModelAndView confirmDeleteSpreadPosition(@RequestParam Integer spread_spreadId, @RequestParam Integer position_positionId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("position", positionDAO.findPositionByPrimaryKey(position_positionId));
		mav.addObject("spread_spreadId", spread_spreadId);
		mav.setViewName("spread/position/deletePosition.jsp");

		return mav;
	}

	/**
	 * Delete an existing Position entity
	 * 
	 */
/*
	@RequestMapping("/deleteSpreadPosition")
	public ModelAndView deleteSpreadPosition(@RequestParam Integer spread_spreadId, @RequestParam Integer position_positionId) {
		ModelAndView mav = new ModelAndView();

		Spread spread = spreadService.deleteSpreadPosition(spread_spreadId, position_positionId);

		mav.addObject("spread_spreadId", spread_spreadId);
		mav.addObject("spread", spread);
		mav.setViewName("spread/viewSpread.jsp");

		return mav;
	}
*/
	/**
	 * Save an existing Position entity
	 * 
	 */
	@RequestMapping("/saveSpreadPosition")
	public ModelAndView saveSpreadPosition(@RequestParam Integer spread_spreadId, @ModelAttribute Position position) {
		Spread spread = spreadService.saveSpreadPosition(spread_spreadId, position);

		ModelAndView mav = new ModelAndView();
		mav.addObject("spread_spreadId", spread_spreadId);
		mav.addObject("spread", spread);
		mav.setViewName("spread/viewSpread.jsp");

		return mav;
	}

	/**
	 * Select the Spread entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSpread")
	public ModelAndView confirmDeleteSpread(@RequestParam Integer spreadIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("spread", spreadDAO.findSpreadByPrimaryKey(spreadIdKey));
		mav.setViewName("spread/deleteSpread.jsp");

		return mav;
	}

	/**
	 * Create a new Spread entity
	 * 
	 */
	@RequestMapping("/newSpread")
	public ModelAndView newSpread() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("spread", new Spread());
		mav.addObject("newFlag", true);
		mav.setViewName("spread/editSpread.jsp");

		return mav;
	}

	/**
	 * Show all Position entities by Spread
	 * 
	 */
	@RequestMapping("/listSpreadPosition")
	public ModelAndView listSpreadPosition(@RequestParam Integer spreadIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("spread", spreadDAO.findSpreadByPrimaryKey(spreadIdKey));
		mav.setViewName("spread/position/listPosition.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/spreadController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Edit an existing Spread entity
	 * 
	 */
	@RequestMapping("/editSpread")
	public ModelAndView editSpread(@RequestParam Integer spreadIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("spread", spreadDAO.findSpreadByPrimaryKey(spreadIdKey));
		mav.setViewName("spread/editSpread.jsp");

		return mav;
	}

	/**
	 * Entry point to show all Spread entities
	 * 
	 */
	public String indexSpread() {
		return "redirect:/indexSpread";
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
	 * Save an existing Spread entity
	 * 
	 */
	@RequestMapping("/saveSpread")
	public String saveSpread(@ModelAttribute Spread spread) {
		spreadService.saveSpread(spread);
		return "forward:/indexSpread";
	}

	/**
	 * View an existing Position entity
	 * 
	 */
	@RequestMapping("/selectSpreadPosition")
	public ModelAndView selectSpreadPosition(@RequestParam Integer spread_spreadId, @RequestParam Integer position_positionId) {
		Position position = positionDAO.findPositionByPrimaryKey(position_positionId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("spread_spreadId", spread_spreadId);
		mav.addObject("position", position);
		mav.setViewName("spread/position/viewPosition.jsp");

		return mav;
	}

	/**
	 * Delete an existing Spread entity
	 * 
	 */
	@RequestMapping("/deleteSpread")
	public String deleteSpread(@RequestParam Integer spreadIdKey) {
		Spread spread = spreadDAO.findSpreadByPrimaryKey(spreadIdKey);
		spreadService.deleteSpread(spread);
		return "forward:/indexSpread";
	}

	/**
	 * Edit an existing Position entity
	 * 
	 */
	@RequestMapping("/editSpreadPosition")
	public ModelAndView editSpreadPosition(@RequestParam Integer spread_spreadId, @RequestParam Integer position_positionId) {
		Position position = positionDAO.findPositionByPrimaryKey(position_positionId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("spread_spreadId", spread_spreadId);
		mav.addObject("position", position);
		mav.setViewName("spread/position/editPosition.jsp");

		return mav;
	}

	/**
	 * Create a new Position entity
	 * 
	 */
	@RequestMapping("/newSpreadPosition")
	public ModelAndView newSpreadPosition(@RequestParam Integer spread_spreadId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("spread_spreadId", spread_spreadId);
		mav.addObject("position", new Position());
		mav.addObject("newFlag", true);
		mav.setViewName("spread/position/editPosition.jsp");

		return mav;
	}

	/**
	 * Select an existing Spread entity
	 * 
	 */
	@RequestMapping("/selectSpread")
	public ModelAndView selectSpread(@RequestParam Integer spreadIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("spread", spreadDAO.findSpreadByPrimaryKey(spreadIdKey));
		mav.setViewName("spread/viewSpread.jsp");

		return mav;
	}

	/**
	 * Show all Spread entities
	 * 
	 */
	@RequestMapping("/indexSpread")
	public ModelAndView listSpreads() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("spreads", spreadService.loadSpreads());

		mav.setViewName("spread/listSpreads.jsp");

		return mav;
	}
}