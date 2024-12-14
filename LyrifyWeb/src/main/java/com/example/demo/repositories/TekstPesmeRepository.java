package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.TekstPesme;

public interface TekstPesmeRepository extends JpaRepository<TekstPesme, Integer> {
    Optional<TekstPesme> findByPesmaId(Integer pesmaId);
}
