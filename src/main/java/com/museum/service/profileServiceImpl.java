package com.museum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.museum.model.Profile;
import com.museum.model.User;
import com.museum.repository.ProfileRepository;
import com.museum.repository.UserRepository;

@Service
public class profileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void saveProfile(Profile profile) {
		
		User user = userRepository.findById(profile.getId()).get();
		user.setMuseums(user.getMuseums());
		
		profileRepository.save(profile);
	}

	@Override
	public List<Profile> getAllProfile() {
		
		return profileRepository.findAll();
	}

	@Override
	public Profile findById(Long id) {
		
		return profileRepository.findById(id).get();
	}

	@Override
	public Profile getProfileById(Long id) {
		Optional<Profile> optional = profileRepository.findById(id);

		Profile profile = null;
		if (optional.isPresent()) {
			profile = optional.get();
		} else {
			throw new RuntimeException("User not found for id :: " + id);
		}

		return profile;
	}

	@Override
	public void saveProfiles(User profile) {
		userRepository.save(profile);
		
	}

}
