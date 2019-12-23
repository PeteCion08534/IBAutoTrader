package com.theta.service;

import com.theta.domain.ThetaException;

import java.util.Set;

/**
 * Spring service that handles CRUD requests for ThetaException entities
 * 
 */
public interface ThetaExceptionService {

	/**
	 * Delete an existing ThetaException entity
	 * 
	 */
	public void deleteThetaException(ThetaException thetaexception);

	/**
	 * Load an existing ThetaException entity
	 * 
	 */
	public Set<ThetaException> loadThetaExceptions();

	/**
	 * Save an existing ThetaException entity
	 * 
	 */
	public void saveThetaException(ThetaException thetaexception_1);
}