package com.gwachala.springapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gwachala.springapp.model.Simulation;

import com.gwachala.springapp.service.SimulationService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/app")
public class SimulationController {

	@Autowired
    private SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @GetMapping(value = {"/all"})
    public  Iterable<Simulation> getSimulations() {
        return simulationService.getAllSimulations();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Simulation> getProduct(@PathVariable long id) {
		Simulation simulation = simulationService.getSimulation(id);

		if (simulation != null) {
			return new ResponseEntity<>(simulationService.getSimulation(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
    
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
	public ResponseEntity<?> addSimulation(@RequestBody Simulation simulation) {
		return new ResponseEntity<>(simulationService.create(simulation), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteSimulation(@PathVariable long id) {
		Simulation currentSimulation = simulationService.getSimulation(id);
		
		if (currentSimulation.getId() == id) {
			simulationService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	 @PutMapping("/{id}")
	 public ResponseEntity<?> replaceSimulation(@RequestBody Simulation newSimulation, @PathVariable Long id) {
		 
		 Simulation currentSimulation = simulationService.getSimulation(id);
		 if(currentSimulation!=null) {
			 currentSimulation.setN(newSimulation.getN());
			 currentSimulation.setM(newSimulation.getM());
			 currentSimulation.setP(newSimulation.getP());
			 currentSimulation.setR(newSimulation.getR());
			 currentSimulation.setTi(newSimulation.getTi());
			 currentSimulation.setTm(newSimulation.getTm());
			 currentSimulation.setTs(newSimulation.getTs());
			 
			 
			  
			 return new ResponseEntity<>(simulationService.create(currentSimulation),HttpStatus.OK);
		 }
		 else{
			 newSimulation.setId(id);
			 return new ResponseEntity<>(simulationService.create(newSimulation),HttpStatus.OK);
		 }

	  }
    
    
}
