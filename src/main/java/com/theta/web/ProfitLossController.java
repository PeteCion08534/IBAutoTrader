package com.theta.web;

import com.theta.dao.ProfitLossDAO;
import com.theta.dao.StrategyAccountDAO;
import com.theta.dao.StrategyDAO;

import com.theta.domain.ProfitLoss;
import com.theta.domain.StrategyAccount;

import com.theta.service.ProfitLossService;

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
 * Spring MVC controller that handles CRUD requests for ProfitLoss entities
 * 
 */

@Controller("ProfitLossController")
public class ProfitLossController {

	/**
	 * DAO injected by Spring that manages ProfitLoss entities
	 * 
	 */
	@Autowired
	private ProfitLossDAO profitLossDAO;

	/**
	 * DAO injected by Spring that manages Strategy entities
	 * 
	 */
	@Autowired
	private StrategyAccountDAO strategyAccountDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for ProfitLoss entities
	 * 
	 */
	@Autowired
	private ProfitLossService profitLossService;

	/**
	 * Select the ProfitLoss entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteProfitLoss")
	public ModelAndView confirmDeleteProfitLoss(@RequestParam Integer profitLossIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("profitloss", profitLossDAO.findProfitLossByPrimaryKey(profitLossIdKey));
		mav.setViewName("profitloss/deleteProfitLoss.jsp");

		return mav;
	}

	/**
	 * View an existing Strategy entity
	 * 
	 */
	@RequestMapping("/selectProfitLossStrategy")
	public ModelAndView selectProfitLossStrategy(@RequestParam Integer profitloss_profitLossId, @RequestParam Integer strategyAccount_strategyAccountId) {
		StrategyAccount strategyAccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccount_strategyAccountId, -1, -1);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("profitloss_profitLossId", profitloss_profitLossId);
		mav.addObject("strategyAccount", strategyAccount);
		mav.setViewName("profitloss/strategy/viewStrategy.jsp");

		return mav;
	}

	/**
	 * Delete an existing ProfitLoss entity
	 * 
	 */
	@RequestMapping("/deleteProfitLoss")
	public String deleteProfitLoss(@RequestParam Integer profitLossIdKey) {
		ProfitLoss profitloss = profitLossDAO.findProfitLossByPrimaryKey(profitLossIdKey);
		profitLossService.deleteProfitLoss(profitloss);
		return "forward:/indexProfitLoss";
	}

	/**
	 * Show all Strategy entities by ProfitLoss
	 * 
	 */
	@RequestMapping("/listProfitLossStrategy")
	public ModelAndView listProfitLossStrategy(@RequestParam Integer profitLossIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("profitloss", profitLossDAO.findProfitLossByPrimaryKey(profitLossIdKey));
		mav.setViewName("profitloss/strategy/listStrategy.jsp");

		return mav;
	}

	/**
	 * Save an existing Strategy entity
	 * 
	 */
/*
	@RequestMapping("/saveProfitLossStrategy")
	public ModelAndView saveProfitLossStrategy(@RequestParam Integer profitloss_profitLossId, @ModelAttribute StrategyAccount strategyAccount) {
		ProfitLoss profitloss = profitLossService.saveProfitLossStrategyAccount(profitloss_profitLossId, strategyAccount);

		ModelAndView mav = new ModelAndView();
		mav.addObject("profitloss_profitLossId", profitloss_profitLossId);
		mav.addObject("profitloss", profitloss);
		mav.setViewName("profitloss/viewProfitLoss.jsp");

		return mav;
	}
*/
	/**
	 * Edit an existing ProfitLoss entity
	 * 
	 */
	@RequestMapping("/editProfitLoss")
	public ModelAndView editProfitLoss(@RequestParam Integer profitLossIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("profitloss", profitLossDAO.findProfitLossByPrimaryKey(profitLossIdKey));
		mav.setViewName("profitloss/editProfitLoss.jsp");

		return mav;
	}

	/**
	 * Edit an existing Strategy entity
	 * 
	 */
	@RequestMapping("/editProfitLossStrategy")
	public ModelAndView editProfitLossStrategy(@RequestParam Integer profitloss_profitLossId, @RequestParam Integer strategyAccount_strategyAccountId) {
		StrategyAccount strategyAccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccount_strategyAccountId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("profitloss_profitLossId", profitloss_profitLossId);
		mav.addObject("strategyAccount", strategyAccount);
		mav.setViewName("profitloss/strategy/editStrategy.jsp");

		return mav;
	}

	/**
	 * Show all ProfitLoss entities
	 * 
	 */
	@RequestMapping("/indexProfitLoss")
	public ModelAndView listProfitLosss() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("profitlosss", profitLossService.loadProfitLosss());

		mav.setViewName("profitloss/listProfitLosss.jsp");

		return mav;
	}

	/**
	 * Entry point to show all ProfitLoss entities
	 * 
	 */
	public String indexProfitLoss() {
		return "redirect:/indexProfitLoss";
	}

	/**
	 * Delete an existing Strategy entity
	 * 
	 */
/*	
	@RequestMapping("/deleteProfitLossStrategy")
	public ModelAndView deleteProfitLossStrategy(@RequestParam Integer profitloss_profitLossId, @RequestParam Integer strategyAccount_strategyAccountId) {
		ModelAndView mav = new ModelAndView();

		ProfitLoss profitloss = profitLossService.deleteProfitLossStrategyAccount(profitloss_profitLossId, strategyAccount_strategyAccountId);

		mav.addObject("profitloss_profitLossId", profitloss_profitLossId);
		mav.addObject("profitloss", profitloss);
		mav.setViewName("profitloss/viewProfitLoss.jsp");

		return mav;
	}
*/
	/**
	 * Create a new ProfitLoss entity
	 * 
	 */
	@RequestMapping("/newProfitLoss")
	public ModelAndView newProfitLoss() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("profitloss", new ProfitLoss());
		mav.addObject("newFlag", true);
		mav.setViewName("profitloss/editProfitLoss.jsp");

		return mav;
	}

	/**
	 * Select an existing ProfitLoss entity
	 * 
	 */
	@RequestMapping("/selectProfitLoss")
	public ModelAndView selectProfitLoss(@RequestParam Integer profitLossIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("profitloss", profitLossDAO.findProfitLossByPrimaryKey(profitLossIdKey));
		mav.setViewName("profitloss/viewProfitLoss.jsp");

		return mav;
	}

	/**
	 * Select the child Strategy entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteProfitLossStrategy")
	public ModelAndView confirmDeleteProfitLossStrategy(@RequestParam Integer profitloss_profitLossId, @RequestParam Integer strategyAccount_strategyAccountId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategyAccount", strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccount_strategyAccountId));
		mav.addObject("profitloss_profitLossId", profitloss_profitLossId);
		mav.setViewName("profitloss/strategy/deleteStrategy.jsp");

		return mav;
	}

	/**
	 * Save an existing ProfitLoss entity
	 * 
	 */
	@RequestMapping("/saveProfitLoss")
	public String saveProfitLoss(@ModelAttribute ProfitLoss profitloss) {
		profitLossService.saveProfitLoss(profitloss);
		return "forward:/indexProfitLoss";
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
	 * Create a new Strategy entity
	 * 
	 */
	@RequestMapping("/newProfitLossStrategy")
	public ModelAndView newProfitLossStrategy(@RequestParam Integer profitloss_profitLossId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("profitloss_profitLossId", profitloss_profitLossId);
		mav.addObject("strategyAccount", new StrategyAccount());
		mav.addObject("newFlag", true);
		mav.setViewName("profitloss/strategy/editStrategy.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/profitlossController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}
}