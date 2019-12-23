package com.theta.web;

import com.theta.dao.ProfitLossDAO;
import com.theta.dao.StrategyAccountDAO;
import com.theta.dao.StrategyDAO;

import com.theta.domain.ProfitLoss;
import com.theta.domain.Strategy;
import com.theta.domain.StrategyAccount;

import com.theta.service.StrategyService;

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
 * Spring MVC controller that handles CRUD requests for Strategy entities
 * 
 */

@Controller("StrategyController")
public class StrategyController {

	/**
	 * DAO injected by Spring that manages ProfitLoss entities
	 * 
	 */
	@Autowired
	private ProfitLossDAO profitLossDAO;

	/**
	 * DAO injected by Spring that manages StrategyAccount entities
	 * 
	 */
	@Autowired
	private StrategyAccountDAO strategyAccountDAO;

	/**
	 * DAO injected by Spring that manages Strategy entities
	 * 
	 */
	@Autowired
	private StrategyDAO strategyDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Strategy entities
	 * 
	 */
	@Autowired
	private StrategyService strategyService;

	/**
	 * Select the child ProfitLoss entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteStrategyProfitLosses")
	public ModelAndView confirmDeleteStrategyProfitLosses(@RequestParam Integer strategy_strategyId, @RequestParam Integer profitloss_profitLossId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("profitloss", profitLossDAO.findProfitLossByPrimaryKey(profitloss_profitLossId));
		mav.addObject("strategy_strategyId", strategy_strategyId);
		mav.setViewName("strategy/profitlosses/deleteProfitLosses.jsp");

		return mav;
	}

	/**
	 * Save an existing StrategyAccount entity
	 * 
	 */
	@RequestMapping("/saveStrategyStrategyAccounts")
	public ModelAndView saveStrategyStrategyAccounts(@RequestParam Integer strategy_strategyId, @ModelAttribute StrategyAccount strategyaccount) {
		Strategy strategy = strategyService.saveStrategyStrategyAccounts(strategy_strategyId, strategyaccount);

		ModelAndView mav = new ModelAndView();
		mav.addObject("strategy_strategyId", strategy_strategyId);
		mav.addObject("strategy", strategy);
		mav.setViewName("strategy/viewStrategy.jsp");

		return mav;
	}

	/**
	 * Select the child StrategyAccount entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteStrategyStrategyAccounts")
	public ModelAndView confirmDeleteStrategyStrategyAccounts(@RequestParam Integer strategy_strategyId, @RequestParam Integer strategyaccount_strategyAccountId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategyaccount", strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount_strategyAccountId));
		mav.addObject("strategy_strategyId", strategy_strategyId);
		mav.setViewName("strategy/strategyaccounts/deleteStrategyAccounts.jsp");

		return mav;
	}

	/**
	 * Show all ProfitLoss entities by Strategy
	 * 
	 */
	@RequestMapping("/listStrategyProfitLosses")
	public ModelAndView listStrategyProfitLosses(@RequestParam Integer strategyIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategy", strategyDAO.findStrategyByPrimaryKey(strategyIdKey));
		mav.setViewName("strategy/profitlosses/listProfitLosses.jsp");

		return mav;
	}

	/**
	 * Delete an existing StrategyAccount entity
	 * 
	 */
	@RequestMapping("/deleteStrategyStrategyAccounts")
	public ModelAndView deleteStrategyStrategyAccounts(@RequestParam Integer strategy_strategyId, @RequestParam Integer strategyaccount_strategyAccountId) {
		ModelAndView mav = new ModelAndView();

		Strategy strategy = strategyService.deleteStrategyStrategyAccounts(strategyaccount_strategyAccountId, strategy_strategyId);

		mav.addObject("strategy_strategyId", strategy_strategyId);
		mav.addObject("strategy", strategy);
		mav.setViewName("strategy/viewStrategy.jsp");

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
	 * Save an existing Strategy entity
	 * 
	 */
	@RequestMapping("/saveStrategy")
	public String saveStrategy(@ModelAttribute Strategy strategy) {
		strategyService.saveStrategy(strategy);
		return "forward:/indexStrategy";
	}

	/**
	 * Select an existing Strategy entity
	 * 
	 */
	@RequestMapping("/selectStrategy")
	public ModelAndView selectStrategy(@RequestParam Integer strategyIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategy", strategyDAO.findStrategyByPrimaryKey(strategyIdKey));
		mav.setViewName("strategy/viewStrategy.jsp");

		return mav;
	}

	/**
	 * Create a new ProfitLoss entity
	 * 
	 */
	@RequestMapping("/newStrategyProfitLosses")
	public ModelAndView newStrategyProfitLosses(@RequestParam Integer strategy_strategyId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("strategy_strategyId", strategy_strategyId);
		mav.addObject("profitloss", new ProfitLoss());
		mav.addObject("newFlag", true);
		mav.setViewName("strategy/profitlosses/editProfitLosses.jsp");

		return mav;
	}

	/**
	 * Create a new StrategyAccount entity
	 * 
	 */
	@RequestMapping("/newStrategyStrategyAccounts")
	public ModelAndView newStrategyStrategyAccounts(@RequestParam Integer strategy_strategyId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("strategy_strategyId", strategy_strategyId);
		mav.addObject("strategyaccount", new StrategyAccount());
		mav.addObject("newFlag", true);
		mav.setViewName("strategy/strategyaccounts/editStrategyAccounts.jsp");

		return mav;
	}

	/**
	 * Show all Strategy entities
	 * 
	 */
	@RequestMapping("/indexStrategy")
	public ModelAndView listStrategys() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategys", strategyService.loadStrategys());

		mav.setViewName("strategy/listStrategys.jsp");

		return mav;
	}

	/**
	 * Create a new Strategy entity
	 * 
	 */
	@RequestMapping("/newStrategy")
	public ModelAndView newStrategy() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategy", new Strategy());
		mav.addObject("newFlag", true);
		mav.setViewName("strategy/editStrategy.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/strategyController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Edit an existing StrategyAccount entity
	 * 
	 */
	@RequestMapping("/editStrategyStrategyAccounts")
	public ModelAndView editStrategyStrategyAccounts(@RequestParam Integer strategy_strategyId, @RequestParam Integer strategyaccount_strategyAccountId) {
		StrategyAccount strategyaccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount_strategyAccountId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("strategy_strategyId", strategy_strategyId);
		mav.addObject("strategyaccount", strategyaccount);
		mav.setViewName("strategy/strategyaccounts/editStrategyAccounts.jsp");

		return mav;
	}

	/**
	 * Show all StrategyAccount entities by Strategy
	 * 
	 */
	@RequestMapping("/listStrategyStrategyAccounts")
	public ModelAndView listStrategyStrategyAccounts(@RequestParam Integer strategyIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategy", strategyDAO.findStrategyByPrimaryKey(strategyIdKey));
		mav.setViewName("strategy/strategyaccounts/listStrategyAccounts.jsp");

		return mav;
	}

	/**
	 * Save an existing ProfitLoss entity
	 * Delete an existing ProfitLoss entity
	 */
	/*
	 * MOVED TO StrategyAccountController
	 */
	/*
	@RequestMapping("/saveStrategyProfitLosses")
	public ModelAndView saveStrategyProfitLosses(@RequestParam Integer strategy_strategyId, @ModelAttribute ProfitLoss profitloss) {
		Strategy strategy = strategyService.saveStrategyProfitLosses(strategy_strategyId, profitloss);

		ModelAndView mav = new ModelAndView();
		mav.addObject("strategy_strategyId", strategy_strategyId);
		mav.addObject("strategy", strategy);
		mav.setViewName("strategy/viewStrategy.jsp");

		return mav;
	}

	@RequestMapping("/deleteStrategyProfitLosses")
	public ModelAndView deleteStrategyProfitLosses(@RequestParam Integer strategy_strategyId, @RequestParam Integer profitloss_profitLossId) {
		ModelAndView mav = new ModelAndView();

		Strategy strategy = strategyService.deleteStrategyProfitLosses(profitloss_profitLossId, strategy_strategyId);

		mav.addObject("strategy_strategyId", strategy_strategyId);
		mav.addObject("strategy", strategy);
		mav.setViewName("strategy/viewStrategy.jsp");

		return mav;
	}
	*/
	

	/**
	 * View an existing ProfitLoss entity
	 * 
	 */
	@RequestMapping("/selectStrategyProfitLosses")
	public ModelAndView selectStrategyProfitLosses(@RequestParam Integer strategy_strategyId, @RequestParam Integer profitloss_profitLossId) {
		ProfitLoss profitloss = profitLossDAO.findProfitLossByPrimaryKey(profitloss_profitLossId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("strategy_strategyId", strategy_strategyId);
		mav.addObject("profitloss", profitloss);
		mav.setViewName("strategy/profitlosses/viewProfitLosses.jsp");

		return mav;
	}

	/**
	 * Entry point to show all Strategy entities
	 * 
	 */
	public String indexStrategy() {
		return "redirect:/indexStrategy";
	}

	/**
	 * Edit an existing ProfitLoss entity
	 * 
	 */
	@RequestMapping("/editStrategyProfitLosses")
	public ModelAndView editStrategyProfitLosses(@RequestParam Integer strategy_strategyId, @RequestParam Integer profitloss_profitLossId) {
		ProfitLoss profitloss = profitLossDAO.findProfitLossByPrimaryKey(profitloss_profitLossId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("strategy_strategyId", strategy_strategyId);
		mav.addObject("profitloss", profitloss);
		mav.setViewName("strategy/profitlosses/editProfitLosses.jsp");

		return mav;
	}

	/**
	 * Select the Strategy entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteStrategy")
	public ModelAndView confirmDeleteStrategy(@RequestParam Integer strategyIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategy", strategyDAO.findStrategyByPrimaryKey(strategyIdKey));
		mav.setViewName("strategy/deleteStrategy.jsp");

		return mav;
	}


	/**
	 * Delete an existing Strategy entity
	 * 
	 */
	@RequestMapping("/deleteStrategy")
	public String deleteStrategy(@RequestParam Integer strategyIdKey) {
		Strategy strategy = strategyDAO.findStrategyByPrimaryKey(strategyIdKey);
		strategyService.deleteStrategy(strategy);
		return "forward:/indexStrategy";
	}

	/**
	 * View an existing StrategyAccount entity
	 * 
	 */
	@RequestMapping("/selectStrategyStrategyAccounts")
	public ModelAndView selectStrategyStrategyAccounts(@RequestParam Integer strategy_strategyId, @RequestParam Integer strategyaccount_strategyAccountId) {
		StrategyAccount strategyaccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount_strategyAccountId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("strategy_strategyId", strategy_strategyId);
		mav.addObject("strategyaccount", strategyaccount);
		mav.setViewName("strategy/strategyaccounts/viewStrategyAccounts.jsp");

		return mav;
	}

	/**
	 * Edit an existing Strategy entity
	 * 
	 */
	@RequestMapping("/editStrategy")
	public ModelAndView editStrategy(@RequestParam Integer strategyIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategy", strategyDAO.findStrategyByPrimaryKey(strategyIdKey));
		mav.setViewName("strategy/editStrategy.jsp");

		return mav;
	}
}