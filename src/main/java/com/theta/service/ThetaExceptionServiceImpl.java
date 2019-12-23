package com.theta.service;

import com.theta.dao.ThetaExceptionDAO;

import com.theta.domain.ThetaException;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for ThetaException entities
 * 
 */

@Service("ThetaExceptionService")
@Transactional
public class ThetaExceptionServiceImpl implements ThetaExceptionService {

	/**
	 * DAO injected by Spring that manages ThetaException entities
	 * 
	 */
	@Autowired
	private ThetaExceptionDAO thetaExceptionDAO;

	/**
	 * Instantiates a new ThetaExceptionServiceImpl.
	 *
	 */
	public ThetaExceptionServiceImpl() {
	}

	/**
	 * Delete an existing ThetaException entity
	 * 
	 */
	@Transactional
	public void deleteThetaException(ThetaException thetaexception) {
		thetaExceptionDAO.remove(thetaexception);
		thetaExceptionDAO.flush();
	}

	/**
	 * Load an existing ThetaException entity
	 * 
	 */
	@Transactional
	public Set<ThetaException> loadThetaExceptions() {
		return thetaExceptionDAO.findAllThetaExceptions();
	}

	/**
	 * Save an existing ThetaException entity
	 * 
	 */
	@Transactional
	public void saveThetaException(ThetaException thetaexception) {
		ThetaException existingThetaException = thetaExceptionDAO.findThetaExceptionByPrimaryKey(thetaexception.getThetaExceptionId());

		if (existingThetaException != null) {
			existingThetaException.setThetaExceptionId(thetaexception.getThetaExceptionId());
			existingThetaException.setStackTrace(thetaexception.getStackTrace());
			existingThetaException.setExceptionText(thetaexception.getExceptionText());
			existingThetaException.setContractDetailText(thetaexception.getContractDetailText());
			existingThetaException.setOrderDetailText(thetaexception.getOrderDetailText());
			existingThetaException.setCreatedBy(thetaexception.getCreatedBy());
			existingThetaException.setCreatedDate(thetaexception.getCreatedDate());
			existingThetaException.setUpdatedBy(thetaexception.getUpdatedBy());
			existingThetaException.setUpdatedDate(thetaexception.getUpdatedDate());
			thetaexception = thetaExceptionDAO.store(existingThetaException);
		} else {
			thetaexception = thetaExceptionDAO.store(thetaexception);
		}
		thetaExceptionDAO.flush();
	}
}
