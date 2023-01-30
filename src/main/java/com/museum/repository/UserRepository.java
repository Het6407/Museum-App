package com.museum.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.museum.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>,PagingAndSortingRepository<User, Long> {

	
	User findByEmail(String email);

	User findByUsername(String userName);

	User findByProfileImage(Long id);
	
	User findImageById(Long Id);
	
	
	
	
	 List<User> findByMuseumsId(long museumId);

	Page<User> findAll(Pageable pageable);

	

	

	

	

	
	
	

	

	
}
