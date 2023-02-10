package com.museum.service;

import java.util.List;

import com.museum.model.Profile;
import com.museum.model.User;

public interface ProfileService {

	List<Profile> getAllProfile();
	
	void saveProfile(Profile profile);
	
	Profile getProfileById(Long id);
	
	Profile findById(Long id);
	
	void saveProfiles(User profile);

	

}
