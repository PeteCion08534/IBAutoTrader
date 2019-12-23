package com.theta.service;

import com.theta.domain.Snapshot;
import com.theta.domain.SnapshotOption;

import java.util.Set;

/**
 * Spring service that handles CRUD requests for SnapshotOption entities
 * 
 */
public interface SnapshotOptionService {

	/**
	 * Load an existing SnapshotOption entity
	 * 
	 */
	public Set<SnapshotOption> loadSnapshotOptions();

	/**
	 * Delete an existing Snapshot entity
	 * 
	 */
	public SnapshotOption deleteSnapshotOptionSnapshot(Integer snapshotoption_snapshotOptionId, Integer snapshot_snapshotId);

	/**
	 * Save an existing SnapshotOption entity
	 * 
	 */
	public void saveSnapshotOption(SnapshotOption snapshotoption);

	/**
	 * Delete an existing SnapshotOption entity
	 * 
	 */
	public void deleteSnapshotOption(SnapshotOption snapshotoption_1);

	/**
	 * Save an existing Snapshot entity
	 * 
	 */
	public SnapshotOption saveSnapshotOptionSnapshot(Integer snapshotOptionId, Snapshot snapshot);
}