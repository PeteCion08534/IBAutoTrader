package com.theta.service;

import com.theta.domain.Position;
import com.theta.domain.Spread;

import java.util.Set;

/**
 * Spring service that handles CRUD requests for Spread entities
 * 
 */
public interface SpreadService {

	/**
	 * Delete an existing Spread entity
	 * 
	 */
	public void deleteSpread(Spread spread);

	/**
	 * Load an existing Spread entity
	 * 
	 */
	public Set<Spread> loadSpreads();

	/**
	 * Delete an existing Position entity
	 * 
	 */
//	public Spread deleteSpreadPosition(Integer spread_spreadId, Integer position_positionId);

	/**
	 * Save an existing Position entity
	 * 
	 */
	public Spread saveSpreadPosition(Integer spreadId, Position position);

	/**
	 * Save an existing Spread entity
	 * 
	 */
	public void saveSpread(Spread spread_1);
}