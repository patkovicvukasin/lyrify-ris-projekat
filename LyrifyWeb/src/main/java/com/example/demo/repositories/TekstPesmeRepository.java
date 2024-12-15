package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.TekstPesme;

public interface TekstPesmeRepository extends JpaRepository<TekstPesme, Integer> {
    List<TekstPesme> findByPesmaId(Integer pesmaId);
}
