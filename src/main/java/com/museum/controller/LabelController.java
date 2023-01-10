package com.museum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.museum.model.Label;
import com.museum.service.LabelService;

@Controller
public class LabelController {

	@Autowired
	private LabelService labelService; 
	
	@GetMapping("/label")
	public String viewLabel(Model model) {
			
		model.addAttribute("listLabel",labelService.getAllLabels());
		return "label";
	}
	
	@RequestMapping("/saveLabel")
	public String saveLabel(Label label,Model model) {
		labelService.saveLabel(label);
		return "redirect:/label";
	}
	
	@GetMapping("/addLabel")
	public String addLabel(Label label,Model model) {
		
		model.addAttribute("label",label);
		return "add_Label";
	}
	
	@GetMapping("/updateLabel/{id}")
	public String updateLabel(@PathVariable(value = "id") long id, Model model) {
		Label label = labelService.getLabelById(id);
		
		model.addAttribute("label", label);
		
		return "update_Label";
	}
	
	@RequestMapping(value = "/deleteLabel/{id}", method = RequestMethod.GET)
	public String deleteLabel(@PathVariable(value = "id") long id) {
		labelService.deleteLabelById(id);
		return "redirect:/label";
	}
}
