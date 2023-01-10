package com.museum.service;

import java.util.List;

import com.museum.model.Label;

public interface LabelService {

	List<Label> getAllLabels();
	
	void saveLabel(Label label);

	Label getLabelById(long id);

	void deleteLabelById(long id);
}
