package com.theta.web;

import com.theta.dao.IbAccountDAO;
import com.theta.dao.StrategyAccountDAO;

import com.theta.domain.IbAccount;
import com.theta.domain.StrategyAccount;

import com.theta.service.IbAccountService;

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
 * Spring MVC controller that handles CRUD requests for IbAccount entities
 * 
 */

@Controller("IbAccountController")
public class IbAccountController {

	/**
	 * DAO injected by Spring that manages IbAccount entities
	 * 
	 */
	@Autowired
	private IbAccountDAO ibAccountDAO;

	/**
	 * DAO injected by Spring that manages StrategyAccount entities
	 * 
	 */
	@Autowired
	private StrategyAccountDAO strategyAccountDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for IbAccount entities
	 * 
	 */
	@Autowired
	private IbAccountService ibAccountService;

	/**
	 * Edit an existing IbAccount entity
	 * 
	 */
	@RequestMapping("/editIbAccount")
	public ModelAndView editIbAccount(@RequestParam Integer ibAccountIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("ibaccount", ibAccountDAO.findIbAccountByPrimaryKey(ibAccountIdKey));
		mav.setViewName("ibaccount/editIbAccount.jsp");

		return mav;
	}

	/**
	 * Save an existing IbAccount entity
	 * 
	 */
	@RequestMapping("/saveIbAccount")
	public String saveIbAccount(@ModelAttribute IbAccount ibaccount) {
		ibAccountService.saveIbAccount(ibaccount);
		return "forward:/indexIbAccount";
	}

	/**
	 * View an existing StrategyAccount entity
	 * 
	 */
	@RequestMapping("/selectIbAccountStrategyAccounts")
	public ModelAndView selectIbAccountStrategyAccounts(@RequestParam Integer ibaccount_ibAccountId, @RequestParam Integer strategyaccount_strategyAccountId) {
		StrategyAccount strategyaccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount_strategyAccountId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("ibaccount_ibAccountId", ibaccount_ibAccountId);
		mav.addObject("strategyaccount", strategyaccount);
		mav.setViewName("ibaccount/strategyaccounts/viewStrategyAccounts.jsp");

		return mav;
	}

	/**
	 * Create a new StrategyAccount entity
	 * 
	 */
	@RequestMapping("/newIbAccountStrategyAccounts")
	public ModelAndView newIbAccountStrategyAccounts(@RequestParam Integer ibaccount_ibAccountId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ibaccount_ibAccountId", ibaccount_ibAccountId);
		mav.addObject("strategyaccount", new StrategyAccount());
		mav.addObject("newFlag", true);
		mav.setViewName("ibaccount/strategyaccounts/editStrategyAccounts.jsp");

		return mav;
	}

	/**
	 * Show all StrategyAccount entities by IbAccount
	 * 
	 */
	@RequestMapping("/listIbAccountStrategyAccounts")
	public ModelAndView listIbAccountStrategyAccounts(@RequestParam Integer ibAccountIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("ibaccount", ibAccountDAO.findIbAccountByPrimaryKey(ibAccountIdKey));
		mav.setViewName("ibaccount/strategyaccounts/listStrategyAccounts.jsp");

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
	 * Entry point to show all IbAccount entities
	 * 
	 */
	public String indexIbAccount() {
		return "redirect:/indexIbAccount";
	}

	/**
	 */
	@RequestMapping("/ibaccountController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Create a new IbAccount entity
	 * 
	 */
	@RequestMapping("/newIbAccount")
	public ModelAndView newIbAccount() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("ibaccount", new IbAccount());
		mav.addObject("newFlag", true);
		mav.setViewName("ibaccount/editIbAccount.jsp");

		return mav;
	}

	/**
	 * Edit an existing StrategyAccount entity
	 * 
	 */
	@RequestMapping("/editIbAccountStrategyAccounts")
	public ModelAndView editIbAccountStrategyAccounts(@RequestParam Integer ibaccount_ibAccountId, @RequestParam Integer strategyaccount_strategyAccountId) {
		StrategyAccount strategyaccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount_strategyAccountId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("ibaccount_ibAccountId", ibaccount_ibAccountId);
		mav.addObject("strategyaccount", strategyaccount);
		mav.setViewName("ibaccount/strategyaccounts/editStrategyAccounts.jsp");

		return mav;
	}

	/**
	 * Select the child StrategyAccount entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteIbAccountStrategyAccounts")
	public ModelAndView confirmDeleteIbAccountStrategyAccounts(@RequestParam Integer ibaccount_ibAccountId, @RequestParam Integer strategyaccount_strategyAccountId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategyaccount", strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount_strategyAccountId));
		mav.addObject("ibaccount_ibAccountId", ibaccount_ibAccountId);
		mav.setViewName("ibaccount/strategyaccounts/deleteStrategyAccounts.jsp");

		return mav;
	}

	/**
	 * Save an existing StrategyAccount entity
	 * 
	 */
	@RequestMapping("/saveIbAccountStrategyAccounts")
	public ModelAndView saveIbAccountStrategyAccounts(@RequestParam Integer ibaccount_ibAccountId, @ModelAttribute StrategyAccount strategyaccount) {
		IbAccount ibaccount = ibAccountService.saveIbAccountStrategyAccounts(ibaccount_ibAccountId, strategyaccount);

		ModelAndView mav = new ModelAndView();
		mav.addObject("ibaccount_ibAccountId", ibaccount_ibAccountId);
		mav.addObject("ibaccount", ibaccount);
		mav.setViewName("ibaccount/viewIbAccount.jsp");

		return mav;
	}

	/**
	 * Delete an existing IbAccount entity
	 * 
	 */
	@RequestMapping("/deleteIbAccount")
	public String deleteIbAccount(@RequestParam Integer ibAccountIdKey) {
		IbAccount ibaccount = ibAccountDAO.findIbAccountByPrimaryKey(ibAccountIdKey);
		ibAccountService.deleteIbAccount(ibaccount);
		return "forward:/indexIbAccount";
	}

	/**
	 * Delete an existing StrategyAccount entity
	 * 
	 */
	@RequestMapping("/deleteIbAccountStrategyAccounts")
	public ModelAndView deleteIbAccountStrategyAccounts(@RequestParam Integer ibaccount_ibAccountId, @RequestParam Integer strategyaccount_strategyAccountId) {
		ModelAndView mav = new ModelAndView();

		IbAccount ibaccount = ibAccountService.deleteIbAccountStrategyAccounts(strategyaccount_strategyAccountId, ibaccount_ibAccountId);

		mav.addObject("ibaccount_ibAccountId", ibaccount_ibAccountId);
		mav.addObject("ibaccount", ibaccount);
		mav.setViewName("ibaccount/viewIbAccount.jsp");

		return mav;
	}

	/**
	 * Select the IbAccount entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteIbAccount")
	public ModelAndView confirmDeleteIbAccount(@RequestParam Integer ibAccountIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("ibaccount", ibAccountDAO.findIbAccountByPrimaryKey(ibAccountIdKey));
		mav.setViewName("ibaccount/deleteIbAccount.jsp");

		return mav;
	}

	/**
	 * Select an existing IbAccount entity
	 * 
	 */
	@RequestMapping("/selectIbAccount")
	public ModelAndView selectIbAccount(@RequestParam Integer ibAccountIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("ibaccount", ibAccountDAO.findIbAccountByPrimaryKey(ibAccountIdKey));
		mav.setViewName("ibaccount/viewIbAccount.jsp");

		return mav;
	}

	/**
	 * Show all IbAccount entities
	 * 
	 */
	@RequestMapping("/indexIbAccount")
	public ModelAndView listIbAccounts() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("ibaccounts", ibAccountService.loadIbAccounts());

		mav.setViewName("ibaccount/listIbAccounts.jsp");

		return mav;
	}
}