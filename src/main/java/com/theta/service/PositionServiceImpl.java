package com.theta.service;

import com.theta.dao.PositionDAO;
import com.theta.dao.SpreadDAO;
import com.theta.dao.StrategyAccountDAO;

import com.theta.domain.Position;
import com.theta.domain.Spread;
import com.theta.domain.StrategyAccount;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Position entities
 * 
 */

@Service("PositionService")
@Transactional
public class PositionServiceImpl implements PositionService {

	/**
	 * DAO injected by Spring that manages Position entities
	 * 
	 */
	@Autowired
	private PositionDAO positionDAO;

	/**
	 * DAO injected by Spring that manages Spread entities
	 * 
	 */
	@Autowired
	private SpreadDAO spreadDAO;

	/**
	 * DAO injected by Spring that manages StrategyAccount entities
	 * 
	 */
	@Autowired
	private StrategyAccountDAO strategyAccountDAO;

	/**
	 * Instantiates a new PositionServiceImpl.
	 *
	 */
	public PositionServiceImpl() {
	}

	/**
	 * Delete an existing Spread entity
	 * 
	 */
/*
	@Transactional
	public Position deletePositionSpreads(Integer spread_spreadId, Integer position_positionId) {
		Spread spread = spreadDAO.findSpreadByPrimaryKey(spread_spreadId, -1, -1);
		Position position = positionDAO.findPositionByPrimaryKey(position_positionId, -1, -1);

		spread.setPosition(null);
		position.getSpreads().remove(spread);
		spread = spreadDAO.store(spread);
		spreadDAO.flush();

		position = positionDAO.store(position);
		positionDAO.flush();

		spreadDAO.remove(spread);
		spreadDAO.flush();

		return position;
	}
*/
	
	/**
	 * Save an existing Spread entity
	 * 
	 */
	@Transactional
	public Position savePositionSpreads(Integer positionId, Spread spread) {
		Position position = positionDAO.findPositionByPrimaryKey(positionId, -1, -1);
		Spread existingSpread = spreadDAO.findSpreadByPrimaryKey(spread.getSpreadId());

		// copy into the existing record to preserve existing relationships
		if (existingSpread != null) {
			existingSpread.setSpreadId(spread.getSpreadId());
			existingSpread.setOpenOrClosed(spread.getOpenOrClosed());
			//existingSpread.setNumSpreads(spread.getNumSpreads());
			existingSpread.setMoneymkrPositionId(spread.getMoneymkrPositionId());
			existingSpread.setInsurancePositionId(spread.getInsurancePositionId());
			existingSpread.setStrikeMoneymkr(spread.getStrikeMoneymkr());
			existingSpread.setStrikeInsurance(spread.getStrikeInsurance());
			existingSpread.setEnterMoneymkrPrice(spread.getEnterMoneymkrPrice());
			existingSpread.setEnterMoneymkrDate(spread.getEnterMoneymkrDate());
			existingSpread.setEnterInsurancePrice(spread.getEnterInsurancePrice());
			existingSpread.setEnterInsuranceDate(spread.getEnterInsuranceDate());
			existingSpread.setEnterSecurityPrice(spread.getEnterSecurityPrice());
			existingSpread.setEnterSecurityDate(spread.getEnterSecurityDate());
			existingSpread.setEnterCommission(spread.getEnterCommission());
			existingSpread.setCurrentMoneymkrPrice(spread.getCurrentMoneymkrPrice());
			existingSpread.setCurrentInsurancePrice(spread.getCurrentInsurancePrice());
			existingSpread.setCurrentVixPrice(spread.getCurrentVixPrice());
			existingSpread.setCurrentSecurityPrice(spread.getCurrentSecurityPrice());
			existingSpread.setCurrentDate(spread.getCurrentDate());
			existingSpread.setExitMoneymkrPrice(spread.getExitMoneymkrPrice());
			existingSpread.setExitMoneymkrDate(spread.getExitMoneymkrDate());
			existingSpread.setExitInsurancePrice(spread.getExitInsurancePrice());
			existingSpread.setExitInsuranceDate(spread.getExitInsuranceDate());
			existingSpread.setExitSecurityPrice(spread.getExitSecurityPrice());
			existingSpread.setExitSecurityDate(spread.getExitSecurityDate());
			existingSpread.setExitCommission(spread.getExitCommission());
			existingSpread.setTrailingStopIsSet(spread.getTrailingStopIsSet());
			// Remove these:
			//existingSpread.setExitAboveSpreadValue(spread.getExitAboveSpreadValue());
			//existingSpread.setExitBelowSpreadValue(spread.getExitBelowSpreadValue());
			//existingSpread.setTrailingDaysAndHours(spread.getTrailingDaysAndHours());
			existingSpread.setProfitLossUnrealized(spread.getProfitLossUnrealized());
			existingSpread.setProfitLossRealized(spread.getProfitLossRealized());
			existingSpread.setCreatedBy(spread.getCreatedBy());
			existingSpread.setCreatedDate(spread.getCreatedDate());
			existingSpread.setUpdatedBy(spread.getUpdatedBy());
			existingSpread.setUpdatedDate(spread.getUpdatedDate());
			spread = existingSpread;
		}

		//spread.setPositionId(positionId);
		//position.getSpreads().add(spread);
		spread = spreadDAO.store(spread);
		spreadDAO.flush();

		position = positionDAO.store(position);
		positionDAO.flush();

		return position;
	}

	/**
	 * Save an existing Position entity
	 * 
	 */
	@Transactional
	public void savePosition(Position position) {
		Position existingPosition = positionDAO.findPositionByPrimaryKey(position.getPositionId());

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
			position = positionDAO.store(existingPosition);
		} else {
			position = positionDAO.store(position);
		}
		positionDAO.flush();
	}

	/**
	 * Save an existing StrategyAccount entity
	 * 
	 */
	@Transactional
	public Position savePositionStrategyAccount(Integer positionId, StrategyAccount strategyaccount) {
		Position position = positionDAO.findPositionByPrimaryKey(positionId, -1, -1);
		StrategyAccount existingStrategyAccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount.getStrategyAccountId());

		// copy into the existing record to preserve existing relationships
		if (existingStrategyAccount != null) {
			existingStrategyAccount.setStrategyAccountId(strategyaccount.getStrategyAccountId());
			strategyaccount = existingStrategyAccount;
		}

		position.setStrategyAccountId(strategyaccount.getStrategyAccountId());
		//strategyaccount.getPositions().add(position);
		position = positionDAO.store(position);
		positionDAO.flush();

		strategyaccount = strategyAccountDAO.store(strategyaccount);
		strategyAccountDAO.flush();

		return position;
	}

	/**
	 * Load an existing Position entity
	 * 
	 */
	@Transactional
	public Set<Position> loadPositions() {
		return positionDAO.findAllPositions();
	}

	/**
	 * Delete an existing StrategyAccount entity
	 * 
	 */
	@Transactional
	public Position deletePositionStrategyAccount(Integer position_positionId, Integer strategyaccount_strategyAccountId) {
		Position position = positionDAO.findPositionByPrimaryKey(position_positionId, -1, -1);
		StrategyAccount strategyaccount = strategyAccountDAO.findStrategyAccountByPrimaryKey(strategyaccount_strategyAccountId, -1, -1);

		position.setStrategyAccountId(null);
		//strategyaccount.getPositions().remove(position);
		position = positionDAO.store(position);
		positionDAO.flush();

		strategyaccount = strategyAccountDAO.store(strategyaccount);
		strategyAccountDAO.flush();

		strategyAccountDAO.remove(strategyaccount);
		strategyAccountDAO.flush();

		return position;
	}

	/**
	 * Delete an existing Position entity
	 * 
	 */
	@Transactional
	public void deletePosition(Position position) {
		positionDAO.remove(position);
		positionDAO.flush();
	}
}
