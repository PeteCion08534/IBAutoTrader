package com.theta.web;

import com.theta.dao.IbAccountDAO;
import com.theta.dao.PositionDAO;
import com.theta.dao.StrategyAccountDAO;
import com.theta.dao.StrategyDAO;

import com.theta.domain.IbAccount;
import com.theta.domain.Position;
import com.theta.domain.ProfitLoss;
import com.theta.domain.Strategy;
import com.theta.domain.StrategyAccount;

import com.theta.service.StrategyAccountService;

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
 * Spring MVC controller that handles CRUD requests for StrategyAccount entities
 * 
 */

@Controller("StrategyAccountController")
public class StrategyAccountController {

	/**
	 * DAO injected by Spring that manages IbAccount entities
	 * 
	 */
	@Autowired
	private IbAccountDAO ibAccountDAO;

	/**
	 * DAO injected by Spring that manages Position entities
	 * 
	 */
	@Autowired
	private PositionDAO positionDAO;

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
	 * Service injected by Spring that provides CRUD operations for StrategyAccount entities
	 * 
	 */
	@Autowired
	private StrategyAccountService strategyAccountService;

	/**
	 * Save an existing ProfitLoss entity
	 * Delete an existing ProfitLoss entity
	 */
	/*
	 * MOVED FROM StrategyController
	 */
/*
	@RequestMapping("/saveStrategyAccountProfitLosses")
	public ModelAndView saveStrategyAccountProfitLosses(@RequestParam Integer strategyAccount_strategyAccountId, @ModelAttribute ProfitLoss profitloss) {
		StrategyAccount strategyAccount = strategyAccountService.saveStrategyAccountProfitLosses(strategyAccount_strategyAccountId, profitloss);

		ModelAndView mav = new ModelAndView();
		mav.addObject("strategyAccount_strategyAccountId", strategyAccount_strategyAccountId);
		mav.addObject("strategyAccount", strategyAccount);
		mav.setViewName("strategy/viewStrategy.jsp");

		return mav;
	}

	@RequestMapping("/deleteStrategyAccountProfitLosses")
	public ModelAndView deleteStrategyProfitLosses(@RequestParam Integer strategyAccount_strategyAccountId, @RequestParam Integer profitloss_profitLossId) {
		ModelAndView mav = new ModelAndView();

		StrategyAccount strategyAccount = strategyAccountService.deleteStrategyAccountProfitLosses(profitloss_profitLossId, strategyAccount_strategyAccountId);

		mav.addObject("strategyAccount_strategyAccountId", strategyAccount_strategyAccountId);
		mav.addObject("strategyAccount", strategyAccount);
		mav.setViewName("strategy/viewStrategy.jsp");

		return mav;
	}
*/
	
	/**
	 * Delete an existing Strategy entity
	 * 
	 */
	@RequestMapping("/deleteStrategyAccountStrategy")
	public ModelAndView deleteStrategyAccountStrategy(@RequestParam Integer strategyaccount_strategyAccountId, @RequestParam Integer strategy_strategyId) {
		ModelAndView mav = new ModelAndView();

		StrategyAccount strategyaccount = strategyAccountService.deleteStrategyAccountStrategy(strategyaccount_strategyAccountId, strategy_strategyId);

		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.addObject("strategyaccount", strategyaccount);
		mav.setViewName("strategyaccount/viewStrategyAccount.jsp");

		return mav;
	}

	/**
	 * View an existing IbAccount entity
	 * 
	 */
	@RequestMapping("/selectStrategyAccountIbAccount")
	public ModelAndView selectStrategyAccountIbAccount(@RequestParam Integer strategyaccount_strategyAccountId, @RequestParam Integer ibaccount_ibAccountId) {
		IbAccount ibaccount = ibAccountDAO.findIbAccountByPrimaryKey(ibaccount_ibAccountId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.addObject("ibaccount", ibaccount);
		mav.setViewName("strategyaccount/ibaccount/viewIbAccount.jsp");

		return mav;
	}

	/**
	 * Edit an existing Strategy entity
	 * 
	 */
	@RequestMapping("/editStrategyAccountStrategy")
	public ModelAndView editStrategyAccountStrategy(@RequestParam Integer strategyaccount_strategyAccountId, @RequestParam Integer strategy_strategyId) {
		Strategy strategy = strategyDAO.findStrategyByPrimaryKey(strategy_strategyId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.addObject("strategy", strategy);
		mav.setViewName("strategyaccount/strategy/editStrategy.jsp");

		return mav;
	}

	/**
	 * Create a new StrategyAccount entity
	 * 
	 */
	@RequestMapping("/newStrategyAccount")
	public ModelAndView newStrategyAccount() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategyaccount", new StrategyAccount());
		mav.addObject("newFlag", true);
		mav.setViewName("strategyaccount/editStrategyAccount.jsp");

		return mav;
	}

	/**
	 * Create a new Strategy entity
	 * 
	 */
	@RequestMapping("/newStrategyAccountStrategy")
	public ModelAndView newStrategyAccountStrategy(@RequestParam Integer strategyaccount_strategyAccountId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.addObject("strategy", new Strategy());
		mav.addObject("newFlag", true);
		mav.setViewName("strategyaccount/strategy/editStrategy.jsp");

		return mav;
	}

	/**
	 * Show all Strategy entities by StrategyAccount
	 * 
	 */
	@RequestMapping("/listStrategyAccountStrategy")
	public ModelAndView listStrategyAccountStrategy(@RequestParam Integer strategyAccountIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategyaccount", strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccountIdKey));
		mav.setViewName("strategyaccount/strategy/listStrategy.jsp");

		return mav;
	}

	/**
	 * Show all StrategyAccount entities
	 * 
	 */
	@RequestMapping("/indexStrategyAccount")
	public ModelAndView listStrategyAccounts() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategyaccounts", strategyAccountService.loadStrategyAccounts());

		mav.setViewName("strategyaccount/listStrategyAccounts.jsp");

		return mav;
	}

	/**
	 * Entry point to show all StrategyAccount entities
	 * 
	 */
	public String indexStrategyAccount() {
		return "redirect:/indexStrategyAccount";
	}

	/**
	 * View an existing Position entity
	 * 
	 */
	@RequestMapping("/selectStrategyAccountPositions")
	public ModelAndView selectStrategyAccountPositions(@RequestParam Integer strategyaccount_strategyAccountId, @RequestParam Integer position_positionId) {
		Position position = positionDAO.findPositionByPrimaryKey(position_positionId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.addObject("position", position);
		mav.setViewName("strategyaccount/positions/viewPositions.jsp");

		return mav;
	}

	/**
	 * Save an existing StrategyAccount entity
	 * 
	 */
	@RequestMapping("/saveStrategyAccount")
	public String saveStrategyAccount(@ModelAttribute StrategyAccount strategyaccount) {
		strategyAccountService.saveStrategyAccount(strategyaccount);
		return "forward:/indexStrategyAccount";
	}

	/**
	 * Edit an existing IbAccount entity
	 * 
	 */
	@RequestMapping("/editStrategyAccountIbAccount")
	public ModelAndView editStrategyAccountIbAccount(@RequestParam Integer strategyaccount_strategyAccountId, @RequestParam Integer ibaccount_ibAccountId) {
		IbAccount ibaccount = ibAccountDAO.findIbAccountByPrimaryKey(ibaccount_ibAccountId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.addObject("ibaccount", ibaccount);
		mav.setViewName("strategyaccount/ibaccount/editIbAccount.jsp");

		return mav;
	}

	/**
	 * Edit an existing StrategyAccount entity
	 * 
	 */
	@RequestMapping("/editStrategyAccount")
	public ModelAndView editStrategyAccount(@RequestParam Integer strategyAccountIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategyaccount", strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccountIdKey));
		mav.setViewName("strategyaccount/editStrategyAccount.jsp");

		return mav;
	}

	/**
	 * View an existing Strategy entity
	 * 
	 */
	@RequestMapping("/selectStrategyAccountStrategy")
	public ModelAndView selectStrategyAccountStrategy(@RequestParam Integer strategyaccount_strategyAccountId, @RequestParam Integer strategy_strategyId) {
		Strategy strategy = strategyDAO.findStrategyByPrimaryKey(strategy_strategyId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.addObject("strategy", strategy);
		mav.setViewName("strategyaccount/strategy/viewStrategy.jsp");

		return mav;
	}

	/**
	 * Select an existing StrategyAccount entity
	 * 
	 */
	@RequestMapping("/selectStrategyAccount")
	public ModelAndView selectStrategyAccount(@RequestParam Integer strategyAccountIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategyaccount", strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccountIdKey));
		mav.setViewName("strategyaccount/viewStrategyAccount.jsp");

		return mav;
	}

	/**
	 * Select the child IbAccount entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteStrategyAccountIbAccount")
	public ModelAndView confirmDeleteStrategyAccountIbAccount(@RequestParam Integer strategyaccount_strategyAccountId, @RequestParam Integer ibaccount_ibAccountId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("ibaccount", ibAccountDAO.findIbAccountByPrimaryKey(ibaccount_ibAccountId));
		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.setViewName("strategyaccount/ibaccount/deleteIbAccount.jsp");

		return mav;
	}

	/**
	 * Select the child Strategy entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteStrategyAccountStrategy")
	public ModelAndView confirmDeleteStrategyAccountStrategy(@RequestParam Integer strategyaccount_strategyAccountId, @RequestParam Integer strategy_strategyId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategy", strategyDAO.findStrategyByPrimaryKey(strategy_strategyId));
		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.setViewName("strategyaccount/strategy/deleteStrategy.jsp");

		return mav;
	}

	/**
	 * Delete an existing IbAccount entity
	 * 
	 */
	@RequestMapping("/deleteStrategyAccountIbAccount")
	public ModelAndView deleteStrategyAccountIbAccount(@RequestParam Integer strategyaccount_strategyAccountId, @RequestParam Integer ibaccount_ibAccountId) {
		ModelAndView mav = new ModelAndView();

		StrategyAccount strategyaccount = strategyAccountService.deleteStrategyAccountIbAccount(strategyaccount_strategyAccountId, ibaccount_ibAccountId);

		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.addObject("strategyaccount", strategyaccount);
		mav.setViewName("strategyaccount/viewStrategyAccount.jsp");

		return mav;
	}

	/**
	 * Select the child Position entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteStrategyAccountPositions")
	public ModelAndView confirmDeleteStrategyAccountPositions(@RequestParam Integer strategyaccount_strategyAccountId, @RequestParam Integer position_positionId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("position", positionDAO.findPositionByPrimaryKey(position_positionId));
		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.setViewName("strategyaccount/positions/deletePositions.jsp");

		return mav;
	}

	/**
	 * Create a new Position entity
	 * 
	 */
	@RequestMapping("/newStrategyAccountPositions")
	public ModelAndView newStrategyAccountPositions(@RequestParam Integer strategyaccount_strategyAccountId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.addObject("position", new Position());
		mav.addObject("newFlag", true);
		mav.setViewName("strategyaccount/positions/editPositions.jsp");

		return mav;
	}

	/**
	 * Delete an existing StrategyAccount entity
	 * 
	 */
	@RequestMapping("/deleteStrategyAccount")
	public String deleteStrategyAccount(@RequestParam Integer strategyAccountIdKey) {
		StrategyAccount strategyaccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccountIdKey);
		strategyAccountService.deleteStrategyAccount(strategyaccount);
		return "forward:/indexStrategyAccount";
	}

	/**
	 * Select the StrategyAccount entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteStrategyAccount")
	public ModelAndView confirmDeleteStrategyAccount(@RequestParam Integer strategyAccountIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategyaccount", strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccountIdKey));
		mav.setViewName("strategyaccount/deleteStrategyAccount.jsp");

		return mav;
	}

	/**
	 * Show all Position entities by StrategyAccount
	 * 
	 */
	@RequestMapping("/listStrategyAccountPositions")
	public ModelAndView listStrategyAccountPositions(@RequestParam Integer strategyAccountIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategyaccount", strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccountIdKey));
		mav.setViewName("strategyaccount/positions/listPositions.jsp");

		return mav;
	}

	/**
	 * Delete an existing Position entity
	 * 
	 */
	//@RequestMapping("/deleteStrategyAccountPositions")
	//public ModelAndView deleteStrategyAccountPositions(@RequestParam Integer strategyaccount_strategyAccountId, @RequestParam Integer position_positionId) {
	//	ModelAndView mav = new ModelAndView();

	//	StrategyAccount strategyaccount = strategyAccountService.deleteStrategyAccountPositions(position_positionId, strategyaccount_strategyAccountId);

	//	mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
	//	mav.addObject("strategyaccount", strategyaccount);
	//	mav.setViewName("strategyaccount/viewStrategyAccount.jsp");

	//	return mav;
	//}

	/**
	 * Edit an existing Position entity
	 * 
	 */
	@RequestMapping("/editStrategyAccountPositions")
	public ModelAndView editStrategyAccountPositions(@RequestParam Integer strategyaccount_strategyAccountId, @RequestParam Integer position_positionId) {
		Position position = positionDAO.findPositionByPrimaryKey(position_positionId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.addObject("position", position);
		mav.setViewName("strategyaccount/positions/editPositions.jsp");

		return mav;
	}

	/**
	 * Save an existing IbAccount entity
	 * 
	 */
	@RequestMapping("/saveStrategyAccountIbAccount")
	public ModelAndView saveStrategyAccountIbAccount(@RequestParam Integer strategyaccount_strategyAccountId, @ModelAttribute IbAccount ibaccount) {
		StrategyAccount strategyaccount = strategyAccountService.saveStrategyAccountIbAccount(strategyaccount_strategyAccountId, ibaccount);

		ModelAndView mav = new ModelAndView();
		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.addObject("strategyaccount", strategyaccount);
		mav.setViewName("strategyaccount/viewStrategyAccount.jsp");

		return mav;
	}

	/**
	 * Show all IbAccount entities by StrategyAccount
	 * 
	 */
	@RequestMapping("/listStrategyAccountIbAccount")
	public ModelAndView listStrategyAccountIbAccount(@RequestParam Integer strategyAccountIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("strategyaccount", strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccountIdKey));
		mav.setViewName("strategyaccount/ibaccount/listIbAccount.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/strategyaccountController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Create a new IbAccount entity
	 * 
	 */
	@RequestMapping("/newStrategyAccountIbAccount")
	public ModelAndView newStrategyAccountIbAccount(@RequestParam Integer strategyaccount_strategyAccountId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.addObject("ibaccount", new IbAccount());
		mav.addObject("newFlag", true);
		mav.setViewName("strategyaccount/ibaccount/editIbAccount.jsp");

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
	@RequestMapping("/saveStrategyAccountStrategy")
	public ModelAndView saveStrategyAccountStrategy(@RequestParam Integer strategyaccount_strategyAccountId, @ModelAttribute Strategy strategy) {
		StrategyAccount strategyaccount = strategyAccountService.saveStrategyAccountStrategy(strategyaccount_strategyAccountId, strategy);

		ModelAndView mav = new ModelAndView();
		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.addObject("strategyaccount", strategyaccount);
		mav.setViewName("strategyaccount/viewStrategyAccount.jsp");

		return mav;
	}

	/**
	 * Save an existing Position entity
	 * 
	 */
	@RequestMapping("/saveStrategyAccountPositions")
	public ModelAndView saveStrategyAccountPositions(@RequestParam Integer strategyaccount_strategyAccountId, @ModelAttribute Position position) {
		StrategyAccount strategyaccount = strategyAccountService.saveStrategyAccountPositions(strategyaccount_strategyAccountId, position);

		ModelAndView mav = new ModelAndView();
		mav.addObject("strategyaccount_strategyAccountId", strategyaccount_strategyAccountId);
		mav.addObject("strategyaccount", strategyaccount);
		mav.setViewName("strategyaccount/viewStrategyAccount.jsp");

		return mav;
	}
}