package com.example.sea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sea.model.ModelUsers;
@Repository
public interface RepositoryUsers extends JpaRepository<ModelUsers, Integer>{
	ModelUsers findByEmail(String email);
	ModelUsers findByName(String name);
	ModelUsers findByPassword(String password);
	ModelUsers findByCodconfiguration(String codconfiguration);
}
