package com.theta.service;

import com.theta.dao.PositionDAO;
import com.theta.dao.SpreadDAO;

import com.theta.domain.Position;
import com.theta.domain.Spread;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Spread entities
 * 
 */

@Service("SpreadService")
@Transactional
public class SpreadServiceImpl implements SpreadService {

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
	 * Instantiates a new SpreadServiceImpl.
	 *
	 */
	public SpreadServiceImpl() {
	}

	/**
	 * Delete an existing Spread entity
	 * 
	 */
	@Transactional
	public void deleteSpread(Spread spread) {
		spreadDAO.remove(spread);
		spreadDAO.flush();
	}

	/**
	 * Load an existing Spread entity
	 * 
	 */
	@Transactional
	public Set<Spread> loadSpreads() {
		return spreadDAO.findAllSpreads();
	}

	/**
	 * Delete an existing Position entity
	 * 
	 */
/*
	@Transactional
	public Spread deleteSpreadPosition(Integer spread_spreadId, Integer position_positionId) {
		Spread spread = spreadDAO.findSpreadByPrimaryKey(spread_spreadId, -1, -1);
		Position position = positionDAO.findPositionByPrimaryKey(position_positionId, -1, -1);

		spread.setPosition(null);
		//position.getSpreads().remove(spread);
		spread = spreadDAO.store(spread);
		spreadDAO.flush();

		position = positionDAO.store(position);
		positionDAO.flush();

		positionDAO.remove(position);
		positionDAO.flush();

		return spread;
	}
*/
	/**
	 * Save an existing Position entity
	 * 
	 */
	@Transactional
	public Spread saveSpreadPosition(Integer spreadId, Position position) {
		Spread spread = spreadDAO.findSpreadByPrimaryKey(spreadId, -1, -1);
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

		//spread.setPosition(position);
		//position.getSpreads().add(spread);
		spread = spreadDAO.store(spread);
		spreadDAO.flush();

		position = positionDAO.store(position);
		positionDAO.flush();

		return spread;
	}

	/**
	 * Save an existing Spread entity
	 * 
	 */
	@Transactional
	public void saveSpread(Spread spread) {
		Spread existingSpread = spreadDAO.findSpreadByPrimaryKey(spread.getSpreadId());

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
			// TODO: Remove these
			//existingSpread.setExitAboveSpreadValue(spread.getExitAboveSpreadValue());
			//existingSpread.setExitBelowSpreadValue(spread.getExitBelowSpreadValue());
			//existingSpread.setTrailingDaysAndHours(spread.getTrailingDaysAndHours());
			existingSpread.setProfitLossUnrealized(spread.getProfitLossUnrealized());
			existingSpread.setProfitLossRealized(spread.getProfitLossRealized());
			existingSpread.setCreatedBy(spread.getCreatedBy());
			existingSpread.setCreatedDate(spread.getCreatedDate());
			existingSpread.setUpdatedBy(spread.getUpdatedBy());
			existingSpread.setUpdatedDate(spread.getUpdatedDate());
			existingSpread.setReopenDate(spread.getReopenDate());
			existingSpread.setRequestSeqNo(spread.getRequestSeqNo());
			existingSpread.setEnterTriggerDate(spread.getEnterTriggerDate());
			existingSpread.setEnterConfirmDate(spread.getEnterConfirmDate());
			existingSpread.setExitTriggerDate(spread.getExitTriggerDate());
			existingSpread.setExitConfirmDate(spread.getExitConfirmDate());
			spread = spreadDAO.store(existingSpread);
		} else {
			spread = spreadDAO.store(spread);
		}
		spreadDAO.flush();
	}
}
