package com.museum;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.museum.model.Setting;
import com.museum.model.User;
import com.museum.repository.SettingRepository;
import com.museum.repository.UserRepository;

@SpringBootApplication
public class SpringBootMuseumAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMuseumAppApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	
	
	
	
	/*
	 * @Bean CommandLineRunner demo(UserRepository museumRepository) { return (args)
	 * -> {
	 * 
	 * museumRepository.save(User.builder() .username("Super Admin")
	 * .email("superadmin@museum.com") .status(true).role("SUPER_ADMIN")
	 * .password(passwordEncoder().encode("123")) .build()); }; }
	 */
	 
	 
	 
	 
	
	/*
	 * @Bean CommandLineRunner demo2(SettingRepository settingRepository) { return
	 * (args) -> {
	 * 
	 * settingRepository.save(Setting.builder() .android_Version("1.0")
	 * .ios_Version("1.0.0") .build()); }; }
	 */
	 
  
	 

}
