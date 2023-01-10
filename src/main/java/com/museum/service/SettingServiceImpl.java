package com.museum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museum.model.Setting;
import com.museum.repository.SettingRepository;

@Service
public class SettingServiceImpl implements SettingService{

	@Autowired
	private SettingRepository settingRepository;
	
	@Override
	public List<Setting> getAllSetting() {
		return settingRepository.findAll();
	}

	@Override
	public void saveSetting(Setting setting) {
		
	
		
		settingRepository.save(setting);
		
	}

}
