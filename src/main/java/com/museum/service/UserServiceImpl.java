package com.museum.service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.museum.model.Museum;
import com.museum.model.User;
import com.museum.repository.MuseumRepository;
import com.museum.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
   private  PasswordEncoder passwordEncoder;
	
	@Autowired
	private MuseumRepository museumRepository;
	
	
	
	@Override
	public List<User> getAllUsers() { 
		return userRepository.findAll();
	}
	
	

	@Override
	public void saveUser(User user) {
		
		
		User userRepo = userRepository.findByEmail(user.getEmail());
		if(user.getId() == null) {
			if(userRepo != null && userRepo.getEmail() != null && !Strings.isEmpty(userRepo.getEmail()) && userRepo.getEmail().equals(user.getEmail())) {
				throw new InvalidParameterException("Email already exist");
			}
		}
		

		
		user.setRole("ADMIN");
		
		user.setStatus(true);
		
		user.setPassword(passwordEncoder.encode("user123"));
		
		
		
		userRepository.save(user);
     
		
		
	}
	
	@Override
	public Page<User> findPaginated(Pageable pageable) {
		  return userRepository.findAll(pageable);
		}
	
	@Override
	public void editUser(User user1) {
		
	
		user1.setEmail(user1.getEmail());
		user1.setRole("ADMIN");
		
		user1.setStatus(true);
		
		if(user1.getId() != null) {
			User userObj = userRepository.findById(user1.getId()).get();
			if(userObj != null) {
				user1.setPassword(userObj.getPassword());
			}
		}
		
	
		
		System.out.println(user1.getPassword());
		
		
		userRepository.save(user1);
	
	}
	
	@Override
	public void editProfile(User user2) {
		
		user2.setEmail(user2.getEmail());
		
user2.setRole("ADMIN");

if(user2.getId() != null) {
	User userProfile = userRepository.findById(user2.getId()).get();
	if(userProfile != null) {
		user2.setPassword(userProfile.getPassword());
	}
}
		
		user2.setStatus(true);
		
		userRepository.save(user2);
	}
	
	@Override
	public User getUserById(Long id) {
		Optional<User> optional = userRepository.findById(id);
		
		User user = null;
		if(optional.isPresent()) {
			user = optional.get();		
			}
		else {
			throw new RuntimeException("User not found for id :: " + id);
		}
		return user;
	}

	@Override
	public void deleteUserById(Long id) {
		
	Optional<User> userDelete = userRepository.findById(id);
	userDelete.get().setMuseums(null);
	
		this.userRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//System.out.println(userRepository.findByEmail(username));
		return userRepository.findByEmail(username);
		
	}

	@Override
	public void changeStatus(long id) {
		User user = userRepository.findById(id).orElse(null);
	
		if(user.getStatus()){
			user.setStatus(false);
			userRepository.save(user);	
		}else {
			user.setStatus(true);
			userRepository.save(user);	
		}
	}



	@Override
	public User findByUsername(String userName) {
		
		return userRepository.findByUsername(userName);
	}



	@Override
	public User findById(Long id) {
		
		return userRepository.findById(id).get();
	}



	@Override
	public User getProfileImage(Long id) {
		return userRepository.findByProfileImage(id);
	}



	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	 public List<User> getUsersByMuseumId(Integer museumId) {
	        return userRepository.findByMuseumsId(museumId);
	    }

	


	

	 
	

	
}
