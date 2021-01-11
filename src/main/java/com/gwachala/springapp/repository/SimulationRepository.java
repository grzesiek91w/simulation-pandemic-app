package com.gwachala.springapp.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gwachala.springapp.model.Simulation;


@Repository
public interface SimulationRepository extends CrudRepository<Simulation, Serializable> {
}