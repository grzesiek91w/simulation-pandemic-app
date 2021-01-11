package com.gwachala.springapp.repository;


import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gwachala.springapp.model.PopulationSimulation;

@Repository
public interface PopulationSimulationRepository extends CrudRepository<PopulationSimulation, Serializable> {
}
