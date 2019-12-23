package com.theta.web;

import com.theta.dao.SnapshotDAO;
import com.theta.dao.SnapshotOptionDAO;

import com.theta.domain.Snapshot;
import com.theta.domain.SnapshotOption;

import com.theta.service.SnapshotOptionService;

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
 * Spring MVC controller that handles CRUD requests for SnapshotOption entities
 * 
 */

@Controller("SnapshotOptionController")
public class SnapshotOptionController {

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
	 * Service injected by Spring that provides CRUD operations for SnapshotOption entities
	 * 
	 */
	@Autowired
	private SnapshotOptionService snapshotOptionService;

	/**
	 * View an existing Snapshot entity
	 * 
	 */
	@RequestMapping("/selectSnapshotOptionSnapshot")
	public ModelAndView selectSnapshotOptionSnapshot(@RequestParam Integer snapshotoption_snapshotOptionId, @RequestParam Integer snapshot_snapshotId) {
		Snapshot snapshot = snapshotDAO.findSnapshotByPrimaryKey(snapshot_snapshotId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("snapshotoption_snapshotOptionId", snapshotoption_snapshotOptionId);
		mav.addObject("snapshot", snapshot);
		mav.setViewName("snapshotoption/snapshot/viewSnapshot.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/snapshotoptionController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
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
	 * Save an existing SnapshotOption entity
	 * 
	 */
	@RequestMapping("/saveSnapshotOption")
	public String saveSnapshotOption(@ModelAttribute SnapshotOption snapshotoption) {
		snapshotOptionService.saveSnapshotOption(snapshotoption);
		return "forward:/indexSnapshotOption";
	}

	/**
	 * Entry point to show all SnapshotOption entities
	 * 
	 */
	public String indexSnapshotOption() {
		return "redirect:/indexSnapshotOption";
	}

	/**
	 * Show all SnapshotOption entities
	 * 
	 */
	@RequestMapping("/indexSnapshotOption")
	public ModelAndView listSnapshotOptions() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snapshotoptions", snapshotOptionService.loadSnapshotOptions());

		mav.setViewName("snapshotoption/listSnapshotOptions.jsp");

		return mav;
	}

	/**
	 * Show all Snapshot entities by SnapshotOption
	 * 
	 */
	@RequestMapping("/listSnapshotOptionSnapshot")
	public ModelAndView listSnapshotOptionSnapshot(@RequestParam Integer snapshotOptionIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snapshotoption", snapshotOptionDAO.findSnapshotOptionByPrimaryKey(snapshotOptionIdKey));
		mav.setViewName("snapshotoption/snapshot/listSnapshot.jsp");

		return mav;
	}

	/**
	 * Create a new Snapshot entity
	 * 
	 */
	@RequestMapping("/newSnapshotOptionSnapshot")
	public ModelAndView newSnapshotOptionSnapshot(@RequestParam Integer snapshotoption_snapshotOptionId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("snapshotoption_snapshotOptionId", snapshotoption_snapshotOptionId);
		mav.addObject("snapshot", new Snapshot());
		mav.addObject("newFlag", true);
		mav.setViewName("snapshotoption/snapshot/editSnapshot.jsp");

		return mav;
	}

	/**
	 * Edit an existing SnapshotOption entity
	 * 
	 */
	@RequestMapping("/editSnapshotOption")
	public ModelAndView editSnapshotOption(@RequestParam Integer snapshotOptionIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snapshotoption", snapshotOptionDAO.findSnapshotOptionByPrimaryKey(snapshotOptionIdKey));
		mav.setViewName("snapshotoption/editSnapshotOption.jsp");

		return mav;
	}

	/**
	 * Delete an existing Snapshot entity
	 * 
	 */
	@RequestMapping("/deleteSnapshotOptionSnapshot")
	public ModelAndView deleteSnapshotOptionSnapshot(@RequestParam Integer snapshotoption_snapshotOptionId, @RequestParam Integer snapshot_snapshotId) {
		ModelAndView mav = new ModelAndView();

		SnapshotOption snapshotoption = snapshotOptionService.deleteSnapshotOptionSnapshot(snapshotoption_snapshotOptionId, snapshot_snapshotId);

		mav.addObject("snapshotoption_snapshotOptionId", snapshotoption_snapshotOptionId);
		mav.addObject("snapshotoption", snapshotoption);
		mav.setViewName("snapshotoption/viewSnapshotOption.jsp");

		return mav;
	}

	/**
	 * Select the child Snapshot entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSnapshotOptionSnapshot")
	public ModelAndView confirmDeleteSnapshotOptionSnapshot(@RequestParam Integer snapshotoption_snapshotOptionId, @RequestParam Integer snapshot_snapshotId) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snapshot", snapshotDAO.findSnapshotByPrimaryKey(snapshot_snapshotId));
		mav.addObject("snapshotoption_snapshotOptionId", snapshotoption_snapshotOptionId);
		mav.setViewName("snapshotoption/snapshot/deleteSnapshot.jsp");

		return mav;
	}

	/**
	 * Select the SnapshotOption entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSnapshotOption")
	public ModelAndView confirmDeleteSnapshotOption(@RequestParam Integer snapshotOptionIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snapshotoption", snapshotOptionDAO.findSnapshotOptionByPrimaryKey(snapshotOptionIdKey));
		mav.setViewName("snapshotoption/deleteSnapshotOption.jsp");

		return mav;
	}

	/**
	 * Edit an existing Snapshot entity
	 * 
	 */
	@RequestMapping("/editSnapshotOptionSnapshot")
	public ModelAndView editSnapshotOptionSnapshot(@RequestParam Integer snapshotoption_snapshotOptionId, @RequestParam Integer snapshot_snapshotId) {
		Snapshot snapshot = snapshotDAO.findSnapshotByPrimaryKey(snapshot_snapshotId, -1, -1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("snapshotoption_snapshotOptionId", snapshotoption_snapshotOptionId);
		mav.addObject("snapshot", snapshot);
		mav.setViewName("snapshotoption/snapshot/editSnapshot.jsp");

		return mav;
	}

	/**
	 * Select an existing SnapshotOption entity
	 * 
	 */
	@RequestMapping("/selectSnapshotOption")
	public ModelAndView selectSnapshotOption(@RequestParam Integer snapshotOptionIdKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snapshotoption", snapshotOptionDAO.findSnapshotOptionByPrimaryKey(snapshotOptionIdKey));
		mav.setViewName("snapshotoption/viewSnapshotOption.jsp");

		return mav;
	}

	/**
	 * Save an existing Snapshot entity
	 * 
	 */
	@RequestMapping("/saveSnapshotOptionSnapshot")
	public ModelAndView saveSnapshotOptionSnapshot(@RequestParam Integer snapshotoption_snapshotOptionId, @ModelAttribute Snapshot snapshot) {
		SnapshotOption snapshotoption = snapshotOptionService.saveSnapshotOptionSnapshot(snapshotoption_snapshotOptionId, snapshot);

		ModelAndView mav = new ModelAndView();
		mav.addObject("snapshotoption_snapshotOptionId", snapshotoption_snapshotOptionId);
		mav.addObject("snapshotoption", snapshotoption);
		mav.setViewName("snapshotoption/viewSnapshotOption.jsp");

		return mav;
	}

	/**
	 * Create a new SnapshotOption entity
	 * 
	 */
	@RequestMapping("/newSnapshotOption")
	public ModelAndView newSnapshotOption() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("snapshotoption", new SnapshotOption());
		mav.addObject("newFlag", true);
		mav.setViewName("snapshotoption/editSnapshotOption.jsp");

		return mav;
	}

	/**
	 * Delete an existing SnapshotOption entity
	 * 
	 */
	@RequestMapping("/deleteSnapshotOption")
	public String deleteSnapshotOption(@RequestParam Integer snapshotOptionIdKey) {
		SnapshotOption snapshotoption = snapshotOptionDAO.findSnapshotOptionByPrimaryKey(snapshotOptionIdKey);
		snapshotOptionService.deleteSnapshotOption(snapshotoption);
		return "forward:/indexSnapshotOption";
	}
}