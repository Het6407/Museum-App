package com.museum.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.museum.model.Setting;

@Service
public interface SettingService {

	List<Setting> getAllSetting();

	void saveSetting(Setting setting);
}
