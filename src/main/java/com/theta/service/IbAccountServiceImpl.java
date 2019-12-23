package com.theta.service;

import com.theta.dao.IbAccountDAO;
import com.theta.dao.StrategyAccountDAO;

import com.theta.domain.IbAccount;
import com.theta.domain.StrategyAccount;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for IbAccount entities
 * 
 */

@Service("IbAccountService")
@Transactional
public class IbAccountServiceImpl implements IbAccountService {

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
	 * Instantiates a new IbAccountServiceImpl.
	 *
	 */
	public IbAccountServiceImpl() {
	}

	/**
	 * Delete an existing IbAccount entity
	 * 
	 */
	@Transactional
	public void deleteIbAccount(IbAccount ibaccount) {
		ibAccountDAO.remove(ibaccount);
		ibAccountDAO.flush();
	}

	/**
	 * Save an existing IbAccount entity
	 * 
	 */
	@Transactional
	public void saveIbAccount(IbAccount ibaccount) {
		IbAccount existingIbAccount = ibAccountDAO.findIbAccountByPrimaryKey(ibaccount.getIbAccountId());

		if (existingIbAccount != null) {
			existingIbAccount.setIbAccountId(ibaccount.getIbAccountId());
			existingIbAccount.setIbAccountIdExt(ibaccount.getIbAccountIdExt());
			existingIbAccount.setIpAddress(ibaccount.getIpAddress());
			existingIbAccount.setPort(ibaccount.getPort());
			existingIbAccount.setClientId(ibaccount.getClientId());
			existingIbAccount.setCreatedBy(ibaccount.getCreatedBy());
			existingIbAccount.setCreatedDate(ibaccount.getCreatedDate());
			existingIbAccount.setUpdatedBy(ibaccount.getUpdatedBy());
			existingIbAccount.setUpdatedDate(ibaccount.getUpdatedDate());
			ibaccount = ibAccountDAO.store(existingIbAccount);
		} else {
			ibaccount = ibAccountDAO.store(ibaccount);
		}
		ibAccountDAO.flush();
	}

	/**
	 * Delete an existing StrategyAccount entity
	 * 
	 */
	@Transactional
	public IbAccount deleteIbAccountStrategyAccounts(Integer strategyaccount_strategyAccountId, Integer ibaccount_ibAccountId) {
		StrategyAccount strategyaccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount_strategyAccountId, -1, -1);
		IbAccount ibaccount = ibAccountDAO.findIbAccountByPrimaryKey(ibaccount_ibAccountId, -1, -1);

		strategyaccount.setIbAccount(null);
		ibaccount.getStrategyAccounts().remove(strategyaccount);
		strategyaccount = strategyAccountDAO.store(strategyaccount);
		strategyAccountDAO.flush();

		ibaccount = ibAccountDAO.store(ibaccount);
		ibAccountDAO.flush();

		strategyAccountDAO.remove(strategyaccount);
		strategyAccountDAO.flush();

		return ibaccount;
	}

	/**
	 * Load an existing IbAccount entity
	 * 
	 */
	@Transactional
	public Set<IbAccount> loadIbAccounts() {
		return ibAccountDAO.findAllIbAccounts();
	}

	/**
	 * Save an existing StrategyAccount entity
	 * 
	 */
	@Transactional
	public IbAccount saveIbAccountStrategyAccounts(Integer ibAccountId, StrategyAccount strategyaccount) {
		IbAccount ibaccount = ibAccountDAO.findIbAccountByPrimaryKey(ibAccountId, -1, -1);
		StrategyAccount existingStrategyAccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount.getStrategyAccountId());

		// copy into the existing record to preserve existing relationships
		if (existingStrategyAccount != null) {
			existingStrategyAccount.setStrategyAccountId(strategyaccount.getStrategyAccountId());
			strategyaccount = existingStrategyAccount;
		}

		strategyaccount.setIbAccount(ibaccount);
		ibaccount.getStrategyAccounts().add(strategyaccount);
		strategyaccount = strategyAccountDAO.store(strategyaccount);
		strategyAccountDAO.flush();

		ibaccount = ibAccountDAO.store(ibaccount);
		ibAccountDAO.flush();

		return ibaccount;
	}
}
