package com.theta.service;

import com.theta.domain.IbAccount;
import com.theta.domain.Position;
import com.theta.domain.ProfitLoss;
import com.theta.domain.Strategy;
import com.theta.domain.StrategyAccount;

import java.util.Set;

/**
 * Spring service that handles CRUD requests for StrategyAccount entities
 * 
 */
public interface StrategyAccountService {

	/**
	 * Delete an existing Strategy entity
	 * 
	 */
	public StrategyAccount deleteStrategyAccountStrategy(Integer strategyaccount_strategyAccountId, Integer strategy_strategyId);

	/**
	 * Delete an existing StrategyAccount entity
	 * 
	 */
	public void deleteStrategyAccount(StrategyAccount strategyaccount);

	/**
	 * Save an existing StrategyAccount entity
	 * 
	 */
	public void saveStrategyAccount(StrategyAccount strategyaccount_1);

	/**
	 * Save an existing Strategy entity
	 * 
	 */
	public StrategyAccount saveStrategyAccountStrategy(Integer strategyAccountId, Strategy strategy);

	/**
	 * Delete an existing Position entity
	 * 
	 */
	//public StrategyAccount deleteStrategyAccountPositions(Integer position_positionId, Integer strategyaccount_strategyAccountId_1);

	/**
	 * Save an existing Position entity
	 * 
	 */
	public StrategyAccount saveStrategyAccountPositions(Integer strategyAccountId_1, Position position);

	/**
	 * Save an existing IbAccount entity
	 * 
	 */
	public StrategyAccount saveStrategyAccountIbAccount(Integer strategyAccountId_2, IbAccount ibaccount);

	/**
	 * Load an existing StrategyAccount entity
	 * 
	 */
	public Set<StrategyAccount> loadStrategyAccounts();

	/**
	 * Delete an existing IbAccount entity
	 * 
	 */
	public StrategyAccount deleteStrategyAccountIbAccount(Integer strategyaccount_strategyAccountId_2, Integer ibaccount_ibAccountId);


	/*
	 * MOVED FROM StrategyService:
	 */
	/**
	 * Save an existing ProfitLoss entity
	 * Delete an existing ProfitLoss entity
	 * 
	 */
/*
	public StrategyAccount saveStrategyAccountProfitLosses(Integer strategyAccountId, ProfitLoss profitloss);
	public StrategyAccount deleteStrategyAccountProfitLosses(Integer profitloss_profitLossId, Integer strategyAccount_strategyAccountId_1);
*/

}