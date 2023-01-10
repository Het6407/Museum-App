package com.museum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.museum.model.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long>{

}
