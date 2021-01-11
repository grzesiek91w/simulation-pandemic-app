package com.gwachala.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


import com.gwachala.springapp.model.Simulation;

import com.gwachala.springapp.service.SimulationService;

//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages={"com.gwachala.springapp"})
public class SimulationPandemicApp{// extends SpringBootServletInitializer {

	 /*
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(SimulationPandemicApp.class);
    }
   */ 
	public static void main(String[] args) {
		SpringApplication.run(SimulationPandemicApp.class, args);
	}
	
	/*
	@Bean
    CommandLineRunner runner(SimulationService simulationService) {
        return args -> {
            //simulationService.create(new Simulation("Polsk", 1000L,3,1.7,1.8,1,2,100));
            
          
        };
        
}
*/

}
