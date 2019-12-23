package com.theta.service;

import com.theta.domain.ProfitLoss;
import com.theta.domain.Strategy;
import com.theta.domain.StrategyAccount;

import java.util.Set;

/**
 * Spring service that handles CRUD requests for ProfitLoss entities
 * 
 */
public interface ProfitLossService {

	/*
	 * Change to reflect link to StrategyAccount
	 */
	/**
	 * Delete an existing Strategy entity
	 * 
	 */
	//public ProfitLoss deleteProfitLossStrategyAccount(Integer profitloss_profitLossId, Integer strategyAccount_strategyAccountId);

	/**
	 * Save an existing Strategy entity
	 * 
	 */
	//public ProfitLoss saveProfitLossStrategyAccount(Integer profitLossId, StrategyAccount strategyAccount);

	/**
	 * Save an existing ProfitLoss entity
	 * 
	 */
	public void saveProfitLoss(ProfitLoss profitloss);

	/**
	 * Load an existing ProfitLoss entity
	 * 
	 */
	public Set<ProfitLoss> loadProfitLosss();

	/**
	 * Delete an existing ProfitLoss entity
	 * 
	 */
	public void deleteProfitLoss(ProfitLoss profitloss_1);
}