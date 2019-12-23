package com.theta.service;

import com.theta.dao.ProfitLossDAO;
import com.theta.dao.StrategyAccountDAO;
import com.theta.dao.StrategyDAO;

import com.theta.domain.ProfitLoss;
import com.theta.domain.Strategy;
import com.theta.domain.StrategyAccount;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Strategy entities
 * 
 */

@Service("StrategyService")
@Transactional
public class StrategyServiceImpl implements StrategyService {

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
	 * Instantiates a new StrategyServiceImpl.
	 *
	 */
	public StrategyServiceImpl() {
	}

	/**
	 * Save an existing ProfitLoss entity
	 * 
	 */
	/*
	 * MOVED TO StrategyAccount!
	 */
	/*
	@Transactional
	public Strategy saveStrategyProfitLosses(Integer strategyId, ProfitLoss profitloss) {
		Strategy strategy = strategyDAO.findStrategyByPrimaryKey(strategyId, -1, -1);
		ProfitLoss existingProfitLoss = profitLossDAO.findProfitLossByPrimaryKey(profitloss.getProfitLossId());

		// copy into the existing record to preserve existing relationships
		if (existingProfitLoss != null) {
			existingProfitLoss.setProfitLossId(profitloss.getProfitLossId());
			existingProfitLoss.setProfitLossStartDate(profitloss.getProfitLossStartDate());
			existingProfitLoss.setProfitLossDate(profitloss.getProfitLossDate());
			existingProfitLoss.setProfitOrLoss(profitloss.getProfitOrLoss());
			existingProfitLoss.setProfitOrLossAmt(profitloss.getProfitOrLossAmt());
			existingProfitLoss.setCreatedBy(profitloss.getCreatedBy());
			existingProfitLoss.setCreatedDate(profitloss.getCreatedDate());
			existingProfitLoss.setUpdatedBy(profitloss.getUpdatedBy());
			existingProfitLoss.setUpdatedDate(profitloss.getUpdatedDate());
			profitloss = existingProfitLoss;
		}

		profitloss.setStrategyAccount(strategyAccount);
		strategy.getProfitLosses().add(profitloss);
		profitloss = profitLossDAO.store(profitloss);
		profitLossDAO.flush();

		strategy = strategyDAO.store(strategy);
		strategyDAO.flush();

		return strategy;
	}
	*/
	
	
	/**
	 * Save an existing StrategyAccount entity
	 * 
	 */
	@Transactional
	public Strategy saveStrategyStrategyAccounts(Integer strategyId, StrategyAccount strategyaccount) {
		Strategy strategy = strategyDAO.findStrategyByPrimaryKey(strategyId, -1, -1);
		StrategyAccount existingStrategyAccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount.getStrategyAccountId());

		// copy into the existing record to preserve existing relationships
		if (existingStrategyAccount != null) {
			existingStrategyAccount.setStrategyAccountId(strategyaccount.getStrategyAccountId());
			strategyaccount = existingStrategyAccount;
		}

		strategyaccount.setStrategy(strategy);
		strategy.getStrategyAccounts().add(strategyaccount);
		strategyaccount = strategyAccountDAO.store(strategyaccount);
		strategyAccountDAO.flush();

		strategy = strategyDAO.store(strategy);
		strategyDAO.flush();

		return strategy;
	}

	/**
	 * Delete an existing Strategy entity
	 * 
	 */
	@Transactional
	public void deleteStrategy(Strategy strategy) {
		strategyDAO.remove(strategy);
		strategyDAO.flush();
	}

	/**
	 * Delete an existing StrategyAccount entity
	 * 
	 */
	@Transactional
	public Strategy deleteStrategyStrategyAccounts(Integer strategyaccount_strategyAccountId, Integer strategy_strategyId) {
		StrategyAccount strategyaccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount_strategyAccountId, -1, -1);
		Strategy strategy = strategyDAO.findStrategyByPrimaryKey(strategy_strategyId, -1, -1);

		strategyaccount.setStrategy(null);
		strategy.getStrategyAccounts().remove(strategyaccount);
		strategyaccount = strategyAccountDAO.store(strategyaccount);
		strategyAccountDAO.flush();

		strategy = strategyDAO.store(strategy);
		strategyDAO.flush();

		strategyAccountDAO.remove(strategyaccount);
		strategyAccountDAO.flush();

		return strategy;
	}

	/**
	 * Load an existing Strategy entity
	 * 
	 */
	@Transactional
	public Set<Strategy> loadStrategys() {
		return strategyDAO.findAllStrategys();
	}

	/**
	 * Delete an existing ProfitLoss entity
	 * 
	 */
	/*
	 * MOVED TO StrategyAccountServiceImpl
	 */
	/*
	@Transactional
	public Strategy deleteStrategyProfitLosses(Integer profitloss_profitLossId, Integer strategy_strategyId) {
		ProfitLoss profitloss = profitLossDAO.findProfitLossByPrimaryKey(profitloss_profitLossId, -1, -1);
		Strategy strategy = strategyDAO.findStrategyByPrimaryKey(strategy_strategyId, -1, -1);

		profitloss.setStrategy(null);
		strategy.getProfitLosses().remove(profitloss);
		profitloss = profitLossDAO.store(profitloss);
		profitLossDAO.flush();

		strategy = strategyDAO.store(strategy);
		strategyDAO.flush();

		profitLossDAO.remove(profitloss);
		profitLossDAO.flush();

		return strategy;
	}
	*/

	/**
	 * Save an existing Strategy entity
	 * 
	 */
	@Transactional
	public void saveStrategy(Strategy strategy) {
		Strategy existingStrategy = strategyDAO.findStrategyByPrimaryKey(strategy.getStrategyId());

		if (existingStrategy != null) {
			existingStrategy.setStrategyId(strategy.getStrategyId());
			existingStrategy.setStrategyName(strategy.getStrategyName());
			existingStrategy.setSymbol(strategy.getSymbol());
			existingStrategy.setActiveFlag(strategy.getActiveFlag());
			existingStrategy.setInitNumSpreadsCall(strategy.getInitNumSpreadsCall());
			existingStrategy.setInitNumSpreadsPut(strategy.getInitNumSpreadsPut());
			existingStrategy.setPercentEnterLow(strategy.getPercentEnterLow());
			existingStrategy.setPercentEnterHigh(strategy.getPercentEnterHigh());
			existingStrategy.setDaysEnterLow(strategy.getDaysEnterLow());
			existingStrategy.setDaysEnterHigh(strategy.getDaysEnterHigh());
			existingStrategy.setAmountTotalRisk(strategy.getAmountTotalRisk());
			existingStrategy.setAmountRiskPerPoint(strategy.getAmountRiskPerPoint());
			existingStrategy.setDistanceBetOptions(strategy.getDistanceBetOptions());
			existingStrategy.setOptPricesToGet(strategy.getOptPricesToGet());
			existingStrategy.setPctSetTrailingStop(strategy.getPctSetTrailingStop());
			existingStrategy.setPctTrailingStop(strategy.getPctTrailingStop());
			existingStrategy.setPctExitWin(strategy.getPctExitWin());
			existingStrategy.setSetWinTrailingStop(strategy.getSetWinTrailingStop());
			existingStrategy.setSetExitAtInsPrice(strategy.getSetExitAtInsPrice());
			existingStrategy.setSetReenterOnWin(strategy.getSetReenterOnWin());
			existingStrategy.setSetReenterOnLoss(strategy.getSetReenterOnLoss());
			existingStrategy.setPointDiffForReentry(strategy.getPointDiffForReentry());
			existingStrategy.setDaysExitBeforeExpiry(strategy.getDaysExitBeforeExpiry());
			existingStrategy.setSetBreakevenWhenInsHit(strategy.getSetBreakevenWhenInsHit());
			existingStrategy.setMsWaitForIbResponse(strategy.getMsWaitForIbResponse());
			existingStrategy.setMinDelayFromStart(strategy.getMinDelayFromStart());
			existingStrategy.setSetCheckVix(strategy.getSetCheckVix());
			existingStrategy.setNoEntryIfVixOver(strategy.getNoEntryIfVixOver());
			existingStrategy.setTimezone(strategy.getTimezone());
			existingStrategy.setLiveOrTestFlag(strategy.getLiveOrTestFlag());
			existingStrategy.setComissionPerTrade(strategy.getComissionPerTrade());
			existingStrategy.setCreatedBy(strategy.getCreatedBy());
			existingStrategy.setCreatedDate(strategy.getCreatedDate());
			existingStrategy.setUpdatedBy(strategy.getUpdatedBy());
			existingStrategy.setUpdatedDate(strategy.getUpdatedDate());
			strategy = strategyDAO.store(existingStrategy);
		} else {
			strategy = strategyDAO.store(strategy);
		}
		strategyDAO.flush();
	}
}
