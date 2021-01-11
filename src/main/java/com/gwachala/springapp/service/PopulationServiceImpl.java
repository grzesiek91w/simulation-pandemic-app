package com.gwachala.springapp.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwachala.springapp.model.PopulationSimulation;

import com.gwachala.springapp.repository.PopulationSimulationRepository;


@Service
@Transactional
public class PopulationServiceImpl implements PopulationService {

	@Autowired
    private PopulationSimulationRepository populationRepository;

    public PopulationServiceImpl(PopulationSimulationRepository populationRepository) {
        this.populationRepository = populationRepository;
    }

    @Override
    public Iterable<PopulationSimulation> getAllPopulationSimulations() {
        return populationRepository.findAll();
    }

    @Override
    public PopulationSimulation  getPopulationSimulation(long id) {
        return populationRepository
          .findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Override
    public PopulationSimulation save(PopulationSimulation population) {
        return populationRepository.save(population);
    }

	@Override
	public void delete(long id) {
		populationRepository.deleteById(id);
		
	}
	 @Override
	    public void update(PopulationSimulation population) {
	        this.populationRepository.save(population);
	    }
}