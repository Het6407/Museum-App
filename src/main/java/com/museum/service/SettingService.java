package com.museum.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.museum.model.Setting;
import com.museum.model.User;

@Service
public interface SettingService {

	List<Setting> getAllSetting();

	void saveSetting(Setting setting);
Setting getSettingById(long id);
}
