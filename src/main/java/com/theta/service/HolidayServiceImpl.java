package com.theta.service;

import com.theta.dao.HolidayDAO;

import com.theta.domain.Holiday;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Holiday entities
 * 
 */

@Service("HolidayService")
@Transactional
public class HolidayServiceImpl implements HolidayService {

	/**
	 * DAO injected by Spring that manages Holiday entities
	 * 
	 */
	@Autowired
	private HolidayDAO holidayDAO;

	/**
	 * Instantiates a new HolidayServiceImpl.
	 *
	 */
	public HolidayServiceImpl() {
	}

	/**
	 * Save an existing Holiday entity
	 * 
	 */
	@Transactional
	public void saveHoliday(Holiday holiday) {
		Holiday existingHoliday = holidayDAO.findHolidayByPrimaryKey(holiday.getHolidayId());

		if (existingHoliday != null) {
			existingHoliday.setHolidayId(holiday.getHolidayId());
			existingHoliday.setHolidayName(holiday.getHolidayName());
			existingHoliday.setHolidayYear(holiday.getHolidayYear());
			existingHoliday.setHolidayDate(holiday.getHolidayDate());
			existingHoliday.setCreatedBy(holiday.getCreatedBy());
			existingHoliday.setCreatedDate(holiday.getCreatedDate());
			existingHoliday.setUpdatedBy(holiday.getUpdatedBy());
			existingHoliday.setUpdatedDate(holiday.getUpdatedDate());
			holiday = holidayDAO.store(existingHoliday);
		} else {
			holiday = holidayDAO.store(holiday);
		}
		holidayDAO.flush();
	}

	/**
	 * Load an existing Holiday entity
	 * 
	 */
	@Transactional
	public Set<Holiday> loadHolidays() {
		return holidayDAO.findAllHolidays();
	}

	/**
	 * Delete an existing Holiday entity
	 * 
	 */
	@Transactional
	public void deleteHoliday(Holiday holiday) {
		holidayDAO.remove(holiday);
		holidayDAO.flush();
	}
}
