package com.museum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.museum.model.Setting;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long>{

}
