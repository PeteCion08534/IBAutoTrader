package com.theta.web;

import com.theta.dao.PositionDAO;
import com.theta.dao.SpreadDAO;
import com.theta.dao.StrategyAccountDAO;

import com.theta.domain.Position;
import com.theta.domain.Spread;
import com.theta.domain.StrategyAccount;

import com.theta.service.PositionService;

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
 * Spring MVC controller that handles CRUD requests for Position entities
 * 
 */

@Controller("PositionController")
public class PositionController {

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
	 * DAO injected by Spring that manages StrategyAccount entities
	 * 
	 */
	@Autowired
	private StrategyAccountDAO strategyAccountDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Position entities
	 * 
	 */
	@Autowired
	private PositionService positionService;

	/**
	 * Select the child StrategyAccount entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeletePositionStrategyAccount")
	public ModelAndView confirmDeletePositionStrategyAccount(@RequestParam Integer position_positionId, @RequestParam Integer strategyaccount_strategyAccountId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategyaccount", strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount_strategyAccountId));
		mav.addObject("position_positionId", position_positionId);
		mav.setViewName("position/strategyaccount/deleteStrategyAccount.jsp");

		return mav;
	}

	/**
	 * Select the child Spread entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeletePositionSpreads")
	public ModelAndView confirmDeletePositionSpreads(@RequestParam Integer position_positionId, @RequestParam Integer spread_spreadId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("spread", spreadDAO.findSpreadByPrimaryKey(spread_spreadId));
		mav.addObject("position_positionId", position_positionId);
		mav.setViewName("position/spreads/deleteSpreads.jsp");

		return mav;
	}

	/**
	 * Select an existing Position entity
	 * 
	 */
	@RequestMapping("/selectPosition")
	public ModelAndView selectPosition(@RequestParam Integer positionIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("position", positionDAO.findPositionByPrimaryKey(positionIdKey));
		mav.setViewName("position/viewPosition.jsp");

		return mav;
	}

	/**
	 * Delete an existing StrategyAccount entity
	 * 
	 */
	@RequestMapping("/deletePositionStrategyAccount")
	public ModelAndView deletePositionStrategyAccount(@RequestParam Integer position_positionId, @RequestParam Integer strategyaccount_strategyAccountId) {
		ModelAndView mav = new ModelAndView();

		Position position = positionService.deletePositionStrategyAccount(position_positionId, strategyaccount_strategyAccountId);

		mav.addObject("position_positionId", position_positionId);
		mav.addObject("position", position);
		mav.setViewName("position/viewPosition.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/positionController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Edit an existing Spread entity
	 * 
	 */
	@RequestMapping("/editPositionSpreads")
	public ModelAndView editPositionSpreads(@RequestParam Integer position_positionId, @RequestParam Integer spread_spreadId) {
		Spread spread = spreadDAO.findSpreadByPrimaryKey(spread_spreadId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("position_positionId", position_positionId);
		mav.addObject("spread", spread);
		mav.setViewName("position/spreads/editSpreads.jsp");

		return mav;
	}

	/**
	 * Select the Position entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeletePosition")
	public ModelAndView confirmDeletePosition(@RequestParam Integer positionIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("position", positionDAO.findPositionByPrimaryKey(positionIdKey));
		mav.setViewName("position/deletePosition.jsp");

		return mav;
	}

	/**
	 * Delete an existing Position entity
	 * 
	 */
	@RequestMapping("/deletePosition")
	public String deletePosition(@RequestParam Integer positionIdKey) {
		Position position = positionDAO.findPositionByPrimaryKey(positionIdKey);
		positionService.deletePosition(position);
		return "forward:/indexPosition";
	}

	/**
	 * Show all Spread entities by Position
	 * 
	 */
	@RequestMapping("/listPositionSpreads")
	public ModelAndView listPositionSpreads(@RequestParam Integer positionIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("position", positionDAO.findPositionByPrimaryKey(positionIdKey));
		mav.setViewName("position/spreads/listSpreads.jsp");

		return mav;
	}

	/**
	 * Create a new Position entity
	 * 
	 */
	@RequestMapping("/newPosition")
	public ModelAndView newPosition() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("position", new Position());
		mav.addObject("newFlag", true);
		mav.setViewName("position/editPosition.jsp");

		return mav;
	}

	/**
	 * Entry point to show all Position entities
	 * 
	 */
	public String indexPosition() {
		return "redirect:/indexPosition";
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
	 * Show all StrategyAccount entities by Position
	 * 
	 */
	@RequestMapping("/listPositionStrategyAccount")
	public ModelAndView listPositionStrategyAccount(@RequestParam Integer positionIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("position", positionDAO.findPositionByPrimaryKey(positionIdKey));
		mav.setViewName("position/strategyaccount/listStrategyAccount.jsp");

		return mav;
	}

	/**
	 * Show all Position entities
	 * 
	 */
	@RequestMapping("/indexPosition")
	public ModelAndView listPositions() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("positions", positionService.loadPositions());

		mav.setViewName("position/listPositions.jsp");

		return mav;
	}

	/**
	 * Create a new StrategyAccount entity
	 * 
	 */
	@RequestMapping("/newPositionStrategyAccount")
	public ModelAndView newPositionStrategyAccount(@RequestParam Integer position_positionId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("position_positionId", position_positionId);
		mav.addObject("strategyaccount", new StrategyAccount());
		mav.addObject("newFlag", true);
		mav.setViewName("position/strategyaccount/editStrategyAccount.jsp");

		return mav;
	}

	/**
	 * Save an existing Position entity
	 * 
	 */
	@RequestMapping("/savePosition")
	public String savePosition(@ModelAttribute Position position) {
		positionService.savePosition(position);
		return "forward:/indexPosition";
	}

	/**
	 * View an existing StrategyAccount entity
	 * 
	 */
	@RequestMapping("/selectPositionStrategyAccount")
	public ModelAndView selectPositionStrategyAccount(@RequestParam Integer position_positionId, @RequestParam Integer strategyaccount_strategyAccountId) {
		StrategyAccount strategyaccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount_strategyAccountId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("position_positionId", position_positionId);
		mav.addObject("strategyaccount", strategyaccount);
		mav.setViewName("position/strategyaccount/viewStrategyAccount.jsp");

		return mav;
	}

	/**
	 * Edit an existing Position entity
	 * 
	 */
	@RequestMapping("/editPosition")
	public ModelAndView editPosition(@RequestParam Integer positionIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("position", positionDAO.findPositionByPrimaryKey(positionIdKey));
		mav.setViewName("position/editPosition.jsp");

		return mav;
	}

	/**
	 * Edit an existing StrategyAccount entity
	 * 
	 */
	@RequestMapping("/editPositionStrategyAccount")
	public ModelAndView editPositionStrategyAccount(@RequestParam Integer position_positionId, @RequestParam Integer strategyaccount_strategyAccountId) {
		StrategyAccount strategyaccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount_strategyAccountId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("position_positionId", position_positionId);
		mav.addObject("strategyaccount", strategyaccount);
		mav.setViewName("position/strategyaccount/editStrategyAccount.jsp");

		return mav;
	}

	/**
	 * View an existing Spread entity
	 * 
	 */
	@RequestMapping("/selectPositionSpreads")
	public ModelAndView selectPositionSpreads(@RequestParam Integer position_positionId, @RequestParam Integer spread_spreadId) {
		Spread spread = spreadDAO.findSpreadByPrimaryKey(spread_spreadId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("position_positionId", position_positionId);
		mav.addObject("spread", spread);
		mav.setViewName("position/spreads/viewSpreads.jsp");

		return mav;
	}

	/**
	 * Save an existing StrategyAccount entity
	 * 
	 */
	@RequestMapping("/savePositionStrategyAccount")
	public ModelAndView savePositionStrategyAccount(@RequestParam Integer position_positionId, @ModelAttribute StrategyAccount strategyaccount) {
		Position position = positionService.savePositionStrategyAccount(position_positionId, strategyaccount);

		ModelAndView mav = new ModelAndView();
		mav.addObject("position_positionId", position_positionId);
		mav.addObject("position", position);
		mav.setViewName("position/viewPosition.jsp");

		return mav;
	}

	/**
	 * Save an existing Spread entity
	 * 
	 */
	@RequestMapping("/savePositionSpreads")
	public ModelAndView savePositionSpreads(@RequestParam Integer position_positionId, @ModelAttribute Spread spread) {
		Position position = positionService.savePositionSpreads(position_positionId, spread);

		ModelAndView mav = new ModelAndView();
		mav.addObject("position_positionId", position_positionId);
		mav.addObject("position", position);
		mav.setViewName("position/viewPosition.jsp");

		return mav;
	}

	/**
	 * Delete an existing Spread entity
	 * 
	 */
	/*
	@RequestMapping("/deletePositionSpreads")
	public ModelAndView deletePositionSpreads(@RequestParam Integer position_positionId, @RequestParam Integer spread_spreadId) {
		ModelAndView mav = new ModelAndView();

		Position position = positionService.deletePositionSpreads(spread_spreadId, position_positionId);

		mav.addObject("position_positionId", position_positionId);
		mav.addObject("position", position);
		mav.setViewName("position/viewPosition.jsp");

		return mav;
	}
	*/
	
	/**
	 * Create a new Spread entity
	 * 
	 */
	@RequestMapping("/newPositionSpreads")
	public ModelAndView newPositionSpreads(@RequestParam Integer position_positionId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("position_positionId", position_positionId);
		mav.addObject("spread", new Spread());
		mav.addObject("newFlag", true);
		mav.setViewName("position/spreads/editSpreads.jsp");

		return mav;
	}
}