package com.gwachala.springapp.service;

import org.springframework.validation.annotation.Validated;


import com.gwachala.springapp.model.Simulation;


@Validated
public interface SimulationService {

    Iterable<Simulation> getAllSimulations();

    Simulation create(Simulation simulation);

    void update(Simulation simulation);

	Simulation getSimulation(long id);

	void delete(long id);
}