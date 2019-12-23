package com.theta.service;

import com.theta.domain.Holiday;

import java.util.Set;

/**
 * Spring service that handles CRUD requests for Holiday entities
 * 
 */
public interface HolidayService {

	/**
	 * Save an existing Holiday entity
	 * 
	 */
	public void saveHoliday(Holiday holiday);

	/**
	 * Load an existing Holiday entity
	 * 
	 */
	public Set<Holiday> loadHolidays();

	/**
	 * Delete an existing Holiday entity
	 * 
	 */
	public void deleteHoliday(Holiday holiday_1);
}