package com.theta.service;

import com.theta.domain.IbAccount;
import com.theta.domain.StrategyAccount;

import java.util.Set;

/**
 * Spring service that handles CRUD requests for IbAccount entities
 * 
 */
public interface IbAccountService {

	/**
	 * Delete an existing IbAccount entity
	 * 
	 */
	public void deleteIbAccount(IbAccount ibaccount);

	/**
	 * Save an existing IbAccount entity
	 * 
	 */
	public void saveIbAccount(IbAccount ibaccount_1);

	/**
	 * Delete an existing StrategyAccount entity
	 * 
	 */
	public IbAccount deleteIbAccountStrategyAccounts(Integer strategyaccount_strategyAccountId, Integer ibaccount_ibAccountId);

	/**
	 * Load an existing IbAccount entity
	 * 
	 */
	public Set<IbAccount> loadIbAccounts();

	/**
	 * Save an existing StrategyAccount entity
	 * 
	 */
	public IbAccount saveIbAccountStrategyAccounts(Integer ibAccountId, StrategyAccount strategyaccount);
}