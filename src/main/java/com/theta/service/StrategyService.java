package com.theta.service;

import com.theta.domain.ProfitLoss;
import com.theta.domain.Strategy;
import com.theta.domain.StrategyAccount;

import java.util.Set;

/**
 * Spring service that handles CRUD requests for Strategy entities
 * 
 */
public interface StrategyService {

	/*
	 * MOVED TO StrategyAccountService:
	 */
	/**
	 * Save an existing ProfitLoss entity
	 * Delete an existing ProfitLoss entity
	 * 
	 */
	//public Strategy saveStrategyProfitLosses(Integer strategyId, ProfitLoss profitloss);
	//public Strategy deleteStrategyProfitLosses(Integer profitloss_profitLossId, Integer strategy_strategyId_1);


	/**
	 * Save an existing StrategyAccount entity
	 * 
	 */
	public Strategy saveStrategyStrategyAccounts(Integer strategyId_1, StrategyAccount strategyaccount);

	/**
	 * Delete an existing Strategy entity
	 * 
	 */
	public void deleteStrategy(Strategy strategy);

	/**
	 * Delete an existing StrategyAccount entity
	 * 
	 */
	public Strategy deleteStrategyStrategyAccounts(Integer strategyaccount_strategyAccountId, Integer strategy_strategyId);

	/**
	 * Load an existing Strategy entity
	 * 
	 */
	public Set<Strategy> loadStrategys();


	/**
	 * Save an existing Strategy entity
	 * 
	 */
	public void saveStrategy(Strategy strategy_1);
}