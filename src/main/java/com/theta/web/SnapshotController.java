package com.theta.web;

import com.theta.dao.SnapshotDAO;
import com.theta.dao.SnapshotOptionDAO;

import com.theta.domain.Snapshot;
import com.theta.domain.SnapshotOption;

import com.theta.service.SnapshotService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller that handles CRUD requests for Snapshot entities
 * 
 */

@Controller("SnapshotController")
public class SnapshotController {

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
	 * Service injected by Spring that provides CRUD operations for Snapshot entities
	 * 
	 */
	@Autowired
	private SnapshotService snapshotService;

	/**
	 * View an existing SnapshotOption entity
	 * 
	 */
	@RequestMapping("/selectSnapshotSnapshotOptions")
	public ModelAndView selectSnapshotSnapshotOptions(@RequestParam Integer snapshot_snapshotId, @RequestParam Integer snapshotoption_snapshotOptionId) {
		SnapshotOption snapshotoption = snapshotOptionDAO.findSnapshotOptionByPrimaryKey(snapshotoption_snapshotOptionId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("snapshot_snapshotId", snapshot_snapshotId);
		mav.addObject("snapshotoption", snapshotoption);
		mav.setViewName("snapshot/snapshotoptions/viewSnapshotOptions.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/snapshotController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Select the child SnapshotOption entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSnapshotSnapshotOptions")
	public ModelAndView confirmDeleteSnapshotSnapshotOptions(@RequestParam Integer snapshot_snapshotId, @RequestParam Integer snapshotoption_snapshotOptionId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snapshotoption", snapshotOptionDAO.findSnapshotOptionByPrimaryKey(snapshotoption_snapshotOptionId));
		mav.addObject("snapshot_snapshotId", snapshot_snapshotId);
		mav.setViewName("snapshot/snapshotoptions/deleteSnapshotOptions.jsp");

		return mav;
	}

	/**
	 * Show all Snapshot entities
	 * 
	 */
	@RequestMapping("/indexSnapshot")
	public ModelAndView listSnapshots() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snapshots", snapshotService.loadSnapshots());

		mav.setViewName("snapshot/listSnapshots.jsp");

		return mav;
	}

	/**
	 * Create a new SnapshotOption entity
	 * 
	 */
	@RequestMapping("/newSnapshotSnapshotOptions")
	public ModelAndView newSnapshotSnapshotOptions(@RequestParam Integer snapshot_snapshotId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("snapshot_snapshotId", snapshot_snapshotId);
		mav.addObject("snapshotoption", new SnapshotOption());
		mav.addObject("newFlag", true);
		mav.setViewName("snapshot/snapshotoptions/editSnapshotOptions.jsp");

		return mav;
	}

	/**
	 * Entry point to show all Snapshot entities
	 * 
	 */
	public String indexSnapshot() {
		return "redirect:/indexSnapshot";
	}

	/**
	 * Select the Snapshot entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSnapshot")
	public ModelAndView confirmDeleteSnapshot(@RequestParam Integer snapshotIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snapshot", snapshotDAO.findSnapshotByPrimaryKey(snapshotIdKey));
		mav.setViewName("snapshot/deleteSnapshot.jsp");

		return mav;
	}

	/**
	 * Edit an existing Snapshot entity
	 * 
	 */
	@RequestMapping("/editSnapshot")
	public ModelAndView editSnapshot(@RequestParam Integer snapshotIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snapshot", snapshotDAO.findSnapshotByPrimaryKey(snapshotIdKey));
		mav.setViewName("snapshot/editSnapshot.jsp");

		return mav;
	}

	/**
	 * Select an existing Snapshot entity
	 * 
	 */
	@RequestMapping("/selectSnapshot")
	public ModelAndView selectSnapshot(@RequestParam Integer snapshotIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snapshot", snapshotDAO.findSnapshotByPrimaryKey(snapshotIdKey));
		mav.setViewName("snapshot/viewSnapshot.jsp");

		return mav;
	}

	/**
	 * Register custom, context-specific property editors
	 * 
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register static property editors.
		binder.registerCustomEditor(java.util.Calendar.class, new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class, new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Double.class, true));
	}

	/**
	 * Save an existing Snapshot entity
	 * 
	 */
	@RequestMapping("/saveSnapshot")
	public String saveSnapshot(@ModelAttribute Snapshot snapshot) {
		snapshotService.saveSnapshot(snapshot);
		return "forward:/indexSnapshot";
	}

	/**
	 * Delete an existing Snapshot entity
	 * 
	 */
	@RequestMapping("/deleteSnapshot")
	public String deleteSnapshot(@RequestParam Integer snapshotIdKey) {
		Snapshot snapshot = snapshotDAO.findSnapshotByPrimaryKey(snapshotIdKey);
		snapshotService.deleteSnapshot(snapshot);
		return "forward:/indexSnapshot";
	}

	/**
	 * Edit an existing SnapshotOption entity
	 * 
	 */
	@RequestMapping("/editSnapshotSnapshotOptions")
	public ModelAndView editSnapshotSnapshotOptions(@RequestParam Integer snapshot_snapshotId, @RequestParam Integer snapshotoption_snapshotOptionId) {
		SnapshotOption snapshotoption = snapshotOptionDAO.findSnapshotOptionByPrimaryKey(snapshotoption_snapshotOptionId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("snapshot_snapshotId", snapshot_snapshotId);
		mav.addObject("snapshotoption", snapshotoption);
		mav.setViewName("snapshot/snapshotoptions/editSnapshotOptions.jsp");

		return mav;
	}

	/**
	 * Save an existing SnapshotOption entity
	 * 
	 */
	@RequestMapping("/saveSnapshotSnapshotOptions")
	public ModelAndView saveSnapshotSnapshotOptions(@RequestParam Integer snapshot_snapshotId, @ModelAttribute SnapshotOption snapshotoption) {
		Snapshot snapshot = snapshotService.saveSnapshotSnapshotOptions(snapshot_snapshotId, snapshotoption);

		ModelAndView mav = new ModelAndView();
		mav.addObject("snapshot_snapshotId", snapshot_snapshotId);
		mav.addObject("snapshot", snapshot);
		mav.setViewName("snapshot/viewSnapshot.jsp");

		return mav;
	}

	/**
	 * Delete an existing SnapshotOption entity
	 * 
	 */
	@RequestMapping("/deleteSnapshotSnapshotOptions")
	public ModelAndView deleteSnapshotSnapshotOptions(@RequestParam Integer snapshot_snapshotId, @RequestParam Integer snapshotoption_snapshotOptionId) {
		ModelAndView mav = new ModelAndView();

		Snapshot snapshot = snapshotService.deleteSnapshotSnapshotOptions(snapshotoption_snapshotOptionId, snapshot_snapshotId);

		mav.addObject("snapshot_snapshotId", snapshot_snapshotId);
		mav.addObject("snapshot", snapshot);
		mav.setViewName("snapshot/viewSnapshot.jsp");

		return mav;
	}

	/**
	 * Create a new Snapshot entity
	 * 
	 */
	@RequestMapping("/newSnapshot")
	public ModelAndView newSnapshot() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snapshot", new Snapshot());
		mav.addObject("newFlag", true);
		mav.setViewName("snapshot/editSnapshot.jsp");

		return mav;
	}

	/**
	 * Show all SnapshotOption entities by Snapshot
	 * 
	 */
	@RequestMapping("/listSnapshotSnapshotOptions")
	public ModelAndView listSnapshotSnapshotOptions(@RequestParam Integer snapshotIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snapshot", snapshotDAO.findSnapshotByPrimaryKey(snapshotIdKey));
		mav.setViewName("snapshot/snapshotoptions/listSnapshotOptions.jsp");

		return mav;
	}
}