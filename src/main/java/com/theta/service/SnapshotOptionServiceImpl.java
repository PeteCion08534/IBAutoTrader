package com.theta.service;

import com.theta.dao.SnapshotDAO;
import com.theta.dao.SnapshotOptionDAO;

import com.theta.domain.Snapshot;
import com.theta.domain.SnapshotOption;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for SnapshotOption entities
 * 
 */

@Service("SnapshotOptionService")
@Transactional
public class SnapshotOptionServiceImpl implements SnapshotOptionService {

	/**
	 * DAO injected by Spring that manages Snapshot entities
	 * 
	 */
	@Autowired
	private SnapshotDAO snapshotDAO;

	/**
	 * DAO injected by Spring that manages SnapshotOption entities
	 * 
	 */
	@Autowired
	private SnapshotOptionDAO snapshotOptionDAO;

	/**
	 * Instantiates a new SnapshotOptionServiceImpl.
	 *
	 */
	public SnapshotOptionServiceImpl() {
	}

	/**
	 * Load an existing SnapshotOption entity
	 * 
	 */
	@Transactional
	public Set<SnapshotOption> loadSnapshotOptions() {
		return snapshotOptionDAO.findAllSnapshotOptions();
	}

	/**
	 * Delete an existing Snapshot entity
	 * 
	 */
	@Transactional
	public SnapshotOption deleteSnapshotOptionSnapshot(Integer snapshotoption_snapshotOptionId, Integer snapshot_snapshotId) {
		SnapshotOption snapshotoption = snapshotOptionDAO.findSnapshotOptionByPrimaryKey(snapshotoption_snapshotOptionId, -1, -1);
		Snapshot snapshot = snapshotDAO.findSnapshotByPrimaryKey(snapshot_snapshotId, -1, -1);

		snapshotoption.setSnapshot(null);
		snapshot.getSnapshotOptions().remove(snapshotoption);
		snapshotoption = snapshotOptionDAO.store(snapshotoption);
		snapshotOptionDAO.flush();

		snapshot = snapshotDAO.store(snapshot);
		snapshotDAO.flush();

		snapshotDAO.remove(snapshot);
		snapshotDAO.flush();

		return snapshotoption;
	}

	/**
	 * Save an existing SnapshotOption entity
	 * 
	 */
	@Transactional
	public void saveSnapshotOption(SnapshotOption snapshotoption) {
		SnapshotOption existingSnapshotOption = snapshotOptionDAO.findSnapshotOptionByPrimaryKey(snapshotoption.getSnapshotOptionId());

		if (existingSnapshotOption != null) {
			existingSnapshotOption.setSnapshotOptionId(snapshotoption.getSnapshotOptionId());
			existingSnapshotOption.setOptRight(snapshotoption.getOptRight());
			existingSnapshotOption.setLocalSymbol(snapshotoption.getLocalSymbol());
			existingSnapshotOption.setStrike(snapshotoption.getStrike());
			existingSnapshotOption.setBidPrice(snapshotoption.getBidPrice());
			existingSnapshotOption.setAskPrice(snapshotoption.getAskPrice());
			existingSnapshotOption.setLastPrice(snapshotoption.getLastPrice());
			existingSnapshotOption.setTradingTimeOpenToday(snapshotoption.getTradingTimeOpenToday());
			existingSnapshotOption.setTradingTimeCloseToday(snapshotoption.getTradingTimeCloseToday());
			existingSnapshotOption.setCreatedBy(snapshotoption.getCreatedBy());
			existingSnapshotOption.setCreatedDate(snapshotoption.getCreatedDate());
			existingSnapshotOption.setUpdatedBy(snapshotoption.getUpdatedBy());
			existingSnapshotOption.setUpdatedDate(snapshotoption.getUpdatedDate());
			snapshotoption = snapshotOptionDAO.store(existingSnapshotOption);
		} else {
			snapshotoption = snapshotOptionDAO.store(snapshotoption);
		}
		snapshotOptionDAO.flush();
	}

	/**
	 * Delete an existing SnapshotOption entity
	 * 
	 */
	@Transactional
	public void deleteSnapshotOption(SnapshotOption snapshotoption) {
		snapshotOptionDAO.remove(snapshotoption);
		snapshotOptionDAO.flush();
	}

	/**
	 * Save an existing Snapshot entity
	 * 
	 */
	@Transactional
	public SnapshotOption saveSnapshotOptionSnapshot(Integer snapshotOptionId, Snapshot snapshot) {
		SnapshotOption snapshotoption = snapshotOptionDAO.findSnapshotOptionByPrimaryKey(snapshotOptionId, -1, -1);
		Snapshot existingSnapshot = snapshotDAO.findSnapshotByPrimaryKey(snapshot.getSnapshotId());

		// copy into the existing record to preserve existing relationships
		if (existingSnapshot != null) {
			existingSnapshot.setSnapshotId(snapshot.getSnapshotId());
			existingSnapshot.setSnapshotDate(snapshot.getSnapshotDate());
			existingSnapshot.setSymbol(snapshot.getSymbol());
			existingSnapshot.setSymbolPrice(snapshot.getSymbolPrice());
			existingSnapshot.setExpiryYear(snapshot.getExpiryYear());
			existingSnapshot.setExpiryMonth(snapshot.getExpiryMonth());
			existingSnapshot.setExpirtyDay(snapshot.getExpirtyDay());
			existingSnapshot.setCreatedBy(snapshot.getCreatedBy());
			existingSnapshot.setCreatedDate(snapshot.getCreatedDate());
			existingSnapshot.setUpdatedBy(snapshot.getUpdatedBy());
			existingSnapshot.setUpdatedDate(snapshot.getUpdatedDate());
			snapshot = existingSnapshot;
		}

		snapshotoption.setSnapshot(snapshot);
		snapshot.getSnapshotOptions().add(snapshotoption);
		snapshotoption = snapshotOptionDAO.store(snapshotoption);
		snapshotOptionDAO.flush();

		snapshot = snapshotDAO.store(snapshot);
		snapshotDAO.flush();

		return snapshotoption;
	}
}
