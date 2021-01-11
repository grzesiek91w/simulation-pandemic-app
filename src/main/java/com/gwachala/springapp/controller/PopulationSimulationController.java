package com.gwachala.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gwachala.springapp.model.PopulationSimulation;
import com.gwachala.springapp.model.Simulation;
import com.gwachala.springapp.service.PopulationService;
import com.gwachala.springapp.service.SimulationService;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/app/populationSimulation")
public class PopulationSimulationController {

	@Autowired
    private PopulationService populationService;

   

    @GetMapping(value = {"/all"})
    public  Iterable<PopulationSimulation> getSimulations() {
        return populationService.getAllPopulationSimulations();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PopulationSimulation> getProduct(@PathVariable long id) {
		PopulationSimulation simulation = populationService.getPopulationSimulation(id);

		if (simulation != null) {
			return new ResponseEntity<>(populationService.getPopulationSimulation(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
    
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
	public ResponseEntity<?> addSimulation(@RequestBody PopulationSimulation simulation) {
		return new ResponseEntity<>(populationService.save(simulation), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteSimulation(@PathVariable long id) {
		PopulationSimulation currentSimulation = populationService.getPopulationSimulation(id);
		
		if (currentSimulation.getId() == id) {
			populationService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	 @PutMapping("/{id}")
	 public ResponseEntity<?> replaceSimulation(@RequestBody PopulationSimulation newSimulation, @PathVariable Long id) {
		 
		 PopulationSimulation currentSimulation = populationService.getPopulationSimulation(id);
		 if(currentSimulation!=null) {
			 currentSimulation.setPi(newSimulation.getPi());
			 currentSimulation.setPv(newSimulation.getPv());
			 currentSimulation.setPm(newSimulation.getPm());
			 currentSimulation.setPr(newSimulation.getPr());
			 currentSimulation.setDaySimulation(newSimulation.getDaySimulation());
			 
			 
			 
			  
			 return new ResponseEntity<>(populationService.save(currentSimulation),HttpStatus.OK);
		 }
		 else{
			 newSimulation.setId(id);
			 return new ResponseEntity<>(populationService.save(newSimulation),HttpStatus.OK);
		 }

	  }
    
    
}
