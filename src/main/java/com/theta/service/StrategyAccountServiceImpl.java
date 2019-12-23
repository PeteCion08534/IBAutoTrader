package com.theta.service;

import com.theta.dao.IbAccountDAO;
import com.theta.dao.PositionDAO;
import com.theta.dao.ProfitLossDAO;
import com.theta.dao.StrategyAccountDAO;
import com.theta.dao.StrategyDAO;

import com.theta.domain.IbAccount;
import com.theta.domain.Position;
import com.theta.domain.ProfitLoss;
import com.theta.domain.Strategy;
import com.theta.domain.StrategyAccount;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for StrategyAccount entities
 * 
 */

@Service("StrategyAccountService")
@Transactional
public class StrategyAccountServiceImpl implements StrategyAccountService {

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
	 * DAO injected by Spring that manages Position entities
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
	 * Instantiates a new StrategyAccountServiceImpl.
	 *
	 */
	public StrategyAccountServiceImpl() {
	}

	/**
	 * Delete an existing Strategy entity
	 * 
	 */
	@Transactional
	public StrategyAccount deleteStrategyAccountStrategy(Integer strategyaccount_strategyAccountId, Integer strategy_strategyId) {
		StrategyAccount strategyaccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount_strategyAccountId, -1, -1);
		Strategy strategy = strategyDAO.findStrategyByPrimaryKey(strategy_strategyId, -1, -1);

		strategyaccount.setStrategy(null);
		strategy.getStrategyAccounts().remove(strategyaccount);
		strategyaccount = strategyAccountDAO.store(strategyaccount);
		strategyAccountDAO.flush();

		strategy = strategyDAO.store(strategy);
		strategyDAO.flush();

		strategyDAO.remove(strategy);
		strategyDAO.flush();

		return strategyaccount;
	}

	/**
	 * Delete an existing StrategyAccount entity
	 * 
	 */
	@Transactional
	public void deleteStrategyAccount(StrategyAccount strategyaccount) {
		strategyAccountDAO.remove(strategyaccount);
		strategyAccountDAO.flush();
	}

	/**
	 * Save an existing StrategyAccount entity
	 * 
	 */
	@Transactional
	public void saveStrategyAccount(StrategyAccount strategyaccount) {
		StrategyAccount existingStrategyAccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount.getStrategyAccountId());

		if (existingStrategyAccount != null) {
			existingStrategyAccount.setStrategyAccountId(strategyaccount.getStrategyAccountId());
			strategyaccount = strategyAccountDAO.store(existingStrategyAccount);
		} else {
			strategyaccount = strategyAccountDAO.store(strategyaccount);
		}
		strategyAccountDAO.flush();
	}

	/**
	 * Save an existing Strategy entity
	 * 
	 */
	@Transactional
	public StrategyAccount saveStrategyAccountStrategy(Integer strategyAccountId, Strategy strategy) {
		StrategyAccount strategyaccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccountId, -1, -1);
		Strategy existingStrategy = strategyDAO.findStrategyByPrimaryKey(strategy.getStrategyId());

		// copy into the existing record to preserve existing relationships
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
			strategy = existingStrategy;
		}

		strategyaccount.setStrategy(strategy);
		strategy.getStrategyAccounts().add(strategyaccount);
		strategyaccount = strategyAccountDAO.store(strategyaccount);
		strategyAccountDAO.flush();

		strategy = strategyDAO.store(strategy);
		strategyDAO.flush();

		return strategyaccount;
	}

	/**
	 * Delete an existing Position entity
	 * 
	 */
	//@Transactional
	/*
	public StrategyAccount deleteStrategyAccountPositions(Integer position_positionId, Integer strategyaccount_strategyAccountId) {
		Position position = positionDAO.findPositionByPrimaryKey(position_positionId, -1, -1);
		StrategyAccount strategyaccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount_strategyAccountId, -1, -1);

		position.setStrategyAccount(null);
		strategyaccount.getPositions().remove(position);
		position = positionDAO.store(position);
		positionDAO.flush();

		strategyaccount = strategyAccountDAO.store(strategyaccount);
		strategyAccountDAO.flush();

		positionDAO.remove(position);
		positionDAO.flush();

		return strategyaccount;
	}
	*/
	
	/**
	 * Save an existing Position entity
	 * 
	 */
	@Transactional
	public StrategyAccount saveStrategyAccountPositions(Integer strategyAccountId, Position position) {
		StrategyAccount strategyaccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccountId, -1, -1);
		Position existingPosition = positionDAO.findPositionByPrimaryKey(position.getPositionId());

		// copy into the existing record to preserve existing relationships
		if (existingPosition != null) {
			existingPosition.setPositionId(position.getPositionId());
			existingPosition.setStrategyName(position.getStrategyName());
			existingPosition.setSymbol(position.getSymbol());
			existingPosition.setOptRight(position.getOptRight());
			existingPosition.setExpiryYear(position.getExpiryYear());
			existingPosition.setExpiryMonth(position.getExpiryMonth());
			existingPosition.setExpiryDay(position.getExpiryDay());
			existingPosition.setExpiryTimeframe(position.getExpiryTimeframe());
			existingPosition.setGoalNumSpreads(position.getGoalNumSpreads());
			existingPosition.setNumOpenSpreads(position.getNumOpenSpreads());
			existingPosition.setNumWins(position.getNumWins());
			existingPosition.setNumLosses(position.getNumLosses());
			existingPosition.setProfitLossUnrealized(position.getProfitLossUnrealized());
			existingPosition.setProfitLossRealized(position.getProfitLossRealized());
			existingPosition.setLastExitSecurityPrice(position.getLastExitSecurityPrice());
			existingPosition.setReentrySecPriceCallAbove(position.getReentrySecPriceCallAbove());
			existingPosition.setReentrySecPricePutBelow(position.getReentrySecPricePutBelow());
			existingPosition.setTotalRisked(position.getTotalRisked());
			existingPosition.setCreatedBy(position.getCreatedBy());
			existingPosition.setCreatedDate(position.getCreatedDate());
			existingPosition.setUpdatedBy(position.getUpdatedBy());
			existingPosition.setUpdatedDate(position.getUpdatedDate());
			position = existingPosition;
		}

		position.setStrategyAccountId(strategyaccount.getStrategyAccountId());
		//strategyaccount.getPositions().add(position);
		position = positionDAO.store(position);
		positionDAO.flush();

		strategyaccount = strategyAccountDAO.store(strategyaccount);
		strategyAccountDAO.flush();

		return strategyaccount;
	}

	/**
	 * Save an existing IbAccount entity
	 * 
	 */
	@Transactional
	public StrategyAccount saveStrategyAccountIbAccount(Integer strategyAccountId, IbAccount ibaccount) {
		StrategyAccount strategyaccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccountId, -1, -1);
		IbAccount existingIbAccount = ibAccountDAO.findIbAccountByPrimaryKey(ibaccount.getIbAccountId());

		// copy into the existing record to preserve existing relationships
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
			ibaccount = existingIbAccount;
		}

		strategyaccount.setIbAccount(ibaccount);
		ibaccount.getStrategyAccounts().add(strategyaccount);
		strategyaccount = strategyAccountDAO.store(strategyaccount);
		strategyAccountDAO.flush();

		ibaccount = ibAccountDAO.store(ibaccount);
		ibAccountDAO.flush();

		return strategyaccount;
	}

	/**
	 * Load an existing StrategyAccount entity
	 * 
	 */
	@Transactional
	public Set<StrategyAccount> loadStrategyAccounts() {
		return strategyAccountDAO.findAllStrategyAccounts();
	}

	/**
	 * Delete an existing IbAccount entity
	 * 
	 */
	@Transactional
	public StrategyAccount deleteStrategyAccountIbAccount(Integer strategyaccount_strategyAccountId, Integer ibaccount_ibAccountId) {
		StrategyAccount strategyaccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount_strategyAccountId, -1, -1);
		IbAccount ibaccount = ibAccountDAO.findIbAccountByPrimaryKey(ibaccount_ibAccountId, -1, -1);

		strategyaccount.setIbAccount(null);
		ibaccount.getStrategyAccounts().remove(strategyaccount);
		strategyaccount = strategyAccountDAO.store(strategyaccount);
		strategyAccountDAO.flush();

		ibaccount = ibAccountDAO.store(ibaccount);
		ibAccountDAO.flush();

		ibAccountDAO.remove(ibaccount);
		ibAccountDAO.flush();

		return strategyaccount;
	}

	/**
	 * Save an existing ProfitLoss entity
	 * 
	 */
	/*
	 * MOVED FROM Strategy!
	 */
/*
	@Transactional
	public StrategyAccount saveStrategyAccountProfitLosses(Integer strategyAccountId, ProfitLoss profitloss) {
		StrategyAccount strategyAccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccountId, -1, -1);
		ProfitLoss existingProfitLoss = profitLossDAO.findProfitLossByPrimaryKey(profitloss.getProfitLossId());

		// copy into the existing record to preserve existing relationships
		if (existingProfitLoss != null) {
			existingProfitLoss.setProfitLossId(profitloss.getProfitLossId());
			existingProfitLoss.setCreatedDate(profitloss.getCreatedDate());
			//existingProfitLoss.setUpdatedBy(profitloss.getUpdatedBy());
			//existingProfitLoss.setUpdatedDate(profitloss.getUpdatedDate());
			profitloss = existingProfitLoss;
		}

		profitloss.setStrategyAccount(strategyAccount);
		strategyAccount.getProfitLosses().add(profitloss);
		profitloss = profitLossDAO.store(profitloss);
		profitLossDAO.flush();

		strategyAccount = strategyAccountDAO.store(strategyAccount);
		strategyAccountDAO.flush();

		return strategyAccount;
	}
*/	
	
	/**
	 * Delete an existing ProfitLoss entity
	 * 
	 */
	/*
	 * MOVED TO StrategyAccountServiceImpl
	 */
/*
	@Transactional
	public StrategyAccount deleteStrategyAccountProfitLosses(Integer profitloss_profitLossId, Integer strategyAccount_strategyAccountId) {
		ProfitLoss profitloss = profitLossDAO.findProfitLossByPrimaryKey(profitloss_profitLossId, -1, -1);
		StrategyAccount strategyAccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyAccount_strategyAccountId, -1, -1);

		profitloss.setStrategyAccount(null);
		strategyAccount.getProfitLosses().remove(profitloss);
		profitloss = profitLossDAO.store(profitloss);
		profitLossDAO.flush();

		strategyAccount = strategyAccountDAO.store(strategyAccount);
		strategyDAO.flush();

		profitLossDAO.remove(profitloss);
		profitLossDAO.flush();

		return strategyAccount;
	}
*/

}
