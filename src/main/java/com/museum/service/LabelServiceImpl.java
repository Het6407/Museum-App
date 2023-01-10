package com.museum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museum.model.Label;

import com.museum.repository.LabelRepository;

@Service
public class LabelServiceImpl implements LabelService{

	@Autowired
	private LabelRepository labelRepository;
	
	@Override
	public List<Label> getAllLabels() {
		return labelRepository.findAll();
	}

	@Override
	public void saveLabel(Label label) {
		labelRepository.save(label);
		
	}

	@Override
	public Label getLabelById(long id) {
Optional<Label> optional = labelRepository.findById(id);
		
		Label label = null;
		if(optional.isPresent()) {
			label = optional.get();		
			}
		else {
			throw new RuntimeException("User not found for id :: " + id);
		}
		return label;
	}

	@Override
	public void deleteLabelById(long id) {
		
		this.labelRepository.deleteById(id);
		
	}

}
