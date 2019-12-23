package com.theta.service;

import com.theta.domain.Snapshot;
import com.theta.domain.SnapshotOption;

import java.util.Set;

/**
 * Spring service that handles CRUD requests for Snapshot entities
 * 
 */
public interface SnapshotService {

	/**
	 * Save an existing SnapshotOption entity
	 * 
	 */
	public Snapshot saveSnapshotSnapshotOptions(Integer snapshotId, SnapshotOption snapshotoption);

	/**
	 * Save an existing Snapshot entity
	 * 
	 */
	public void saveSnapshot(Snapshot snapshot);

	/**
	 * Load an existing Snapshot entity
	 * 
	 */
	public Set<Snapshot> loadSnapshots();

	/**
	 * Delete an existing Snapshot entity
	 * 
	 */
	public void deleteSnapshot(Snapshot snapshot_1);

	/**
	 * Delete an existing SnapshotOption entity
	 * 
	 */
	public Snapshot deleteSnapshotSnapshotOptions(Integer snapshotoption_snapshotOptionId, Integer snapshot_snapshotId);
}