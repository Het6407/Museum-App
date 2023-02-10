package com.museum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museum.model.Setting;
import com.museum.model.User;
import com.museum.repository.SettingRepository;

@Service
public class SettingServiceImpl implements SettingService {

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

	@Override
	public Setting getSettingById() {
		Setting setting = null;
		List<Setting> versions = settingRepository.findAll();
		if (versions != null && !versions.isEmpty()) {
			setting = versions.get(0);
		}
		return setting;
	}

}
