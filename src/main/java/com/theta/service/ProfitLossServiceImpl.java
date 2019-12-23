package com.theta.service;

import com.theta.dao.ProfitLossDAO;
import com.theta.dao.StrategyAccountDAO;

import com.theta.domain.ProfitLoss;
import com.theta.domain.Strategy;
import com.theta.domain.StrategyAccount;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for ProfitLoss entities
 * 
 */

@Service("ProfitLossService")
@Transactional
public class ProfitLossServiceImpl implements ProfitLossService {

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
	 * Instantiates a new ProfitLossServiceImpl.
	 *
	 */
	public ProfitLossServiceImpl() {
	}

	/**
	 * Delete an existing Strategy entity
	 * 
	 */
/*
	@Transactional
	public ProfitLoss deleteProfitLossStrategyAccount(Integer profitloss_profitLossId, Integer strategyAccount_strategyAccountId) {
		ProfitLoss profitloss = profitLossDAO.findProfitLossByPrimaryKey(profitloss_profitLossId, -1, -1);
		StrategyAccount strategyAccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccount_strategyAccountId, -1, -1);

		profitloss.setStrategyAccount(null);
		strategyAccount.getProfitLosses().remove(profitloss);
		profitloss = profitLossDAO.store(profitloss);
		profitLossDAO.flush();

		strategyAccount = strategyAccountDAO.store(strategyAccount);
		strategyAccountDAO.flush();

		strategyAccountDAO.remove(strategyAccount);
		strategyAccountDAO.flush();

		return profitloss;
	}
*/
	
	/**
	 * Save an existing Strategy entity
	 * 
	 */
/*
	@Transactional
	public ProfitLoss saveProfitLossStrategyAccount(Integer profitLossId, StrategyAccount strategyAccount) {
		ProfitLoss profitloss = profitLossDAO.findProfitLossByPrimaryKey(profitLossId, -1, -1);
		StrategyAccount existingStrategyAccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccount.getStrategyAccountId());

		// copy into the existing record to preserve existing relationships
		if (existingStrategyAccount != null) {
			existingStrategyAccount.setIbAccount(strategyAccount.getIbAccount());
			existingStrategyAccount.setStrategy(strategyAccount.getStrategy());
			existingStrategyAccount.setStrategyAccountId(strategyAccount.getStrategyAccountId());
			strategyAccount = existingStrategyAccount;
		}

		profitloss.setStrategyAccount(strategyAccount);
		strategyAccount.getProfitLosses().add(profitloss);
		profitloss = profitLossDAO.store(profitloss);
		profitLossDAO.flush();

		strategyAccount = strategyAccountDAO.store(strategyAccount);
		strategyAccountDAO.flush();

		return profitloss;
	}
*/
	
	/**
	 * Save an existing ProfitLoss entity
	 * 
	 */
	@Transactional
	public void saveProfitLoss(ProfitLoss profitloss) {
		ProfitLoss existingProfitLoss = profitLossDAO.findProfitLossByPrimaryKey(profitloss.getProfitLossId());

		if (existingProfitLoss != null) {
			existingProfitLoss.setProfitLossId(profitloss.getProfitLossId());
			existingProfitLoss.setCreatedDate(profitloss.getCreatedDate());
			//existingProfitLoss.setUpdatedBy(profitloss.getUpdatedBy());
			//existingProfitLoss.setUpdatedDate(profitloss.getUpdatedDate());
			profitloss = profitLossDAO.store(existingProfitLoss);
		} else {
			profitloss = profitLossDAO.store(profitloss);
		}
		profitLossDAO.flush();
	}

	/**
	 * Load an existing ProfitLoss entity
	 * 
	 */
	@Transactional
	public Set<ProfitLoss> loadProfitLosss() {
		return profitLossDAO.findAllProfitLosss();
	}

	/**
	 * Delete an existing ProfitLoss entity
	 * 
	 */
	@Transactional
	public void deleteProfitLoss(ProfitLoss profitloss) {
		profitLossDAO.remove(profitloss);
		profitLossDAO.flush();
	}
}
