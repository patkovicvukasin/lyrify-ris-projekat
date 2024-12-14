package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Pesma;

public interface PesmaRepository extends JpaRepository<Pesma, Integer>{
	List<Pesma> findByNazivContainingIgnoreCase(String naziv);
	List<Pesma> findByZanrId(Integer zanrId);
}
