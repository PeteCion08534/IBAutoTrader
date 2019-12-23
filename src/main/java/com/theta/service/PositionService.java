package com.theta.service;

import com.theta.domain.Position;
import com.theta.domain.Spread;
import com.theta.domain.StrategyAccount;

import java.util.Set;

/**
 * Spring service that handles CRUD requests for Position entities
 * 
 */
public interface PositionService {

	/**
	 * Delete an existing Spread entity
	 * 
	 */
	//public Position deletePositionSpreads(Integer spread_spreadId, Integer position_positionId);

	/**
	 * Save an existing Spread entity
	 * 
	 */
	public Position savePositionSpreads(Integer positionId, Spread spread);

	/**
	 * Save an existing Position entity
	 * 
	 */
	public void savePosition(Position position);

	/**
	 * Save an existing StrategyAccount entity
	 * 
	 */
	public Position savePositionStrategyAccount(Integer positionId_1, StrategyAccount strategyaccount);

	/**
	 * Load an existing Position entity
	 * 
	 */
	public Set<Position> loadPositions();

	/**
	 * Delete an existing StrategyAccount entity
	 * 
	 */
	public Position deletePositionStrategyAccount(Integer position_positionId_1, Integer strategyaccount_strategyAccountId);

	/**
	 * Delete an existing Position entity
	 * 
	 */
	public void deletePosition(Position position_1);
}