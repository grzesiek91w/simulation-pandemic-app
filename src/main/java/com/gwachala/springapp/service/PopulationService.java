package com.gwachala.springapp.service;

import org.springframework.validation.annotation.Validated;

import com.gwachala.springapp.model.PopulationSimulation;

@Validated
public interface PopulationService {
	
	Iterable<PopulationSimulation> getAllPopulationSimulations();

    PopulationSimulation getPopulationSimulation (long id);

    PopulationSimulation save(PopulationSimulation populationSimulation);
    
    void delete(long id);

	void update(PopulationSimulation populationSimulation);

}
