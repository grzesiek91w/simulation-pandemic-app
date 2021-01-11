package com.gwachala.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwachala.springapp.model.PopulationSimulation;
import com.gwachala.springapp.model.Simulation;
import com.gwachala.springapp.repository.PopulationSimulationRepository;
import com.gwachala.springapp.repository.SimulationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SimulationServiceImpl implements SimulationService {
	
	@Autowired
    private SimulationRepository simulationRepository;
	
	@Autowired
	private PopulationSimulationRepository populationSimulationRepository;

    public SimulationServiceImpl(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    @Override
    public Iterable<Simulation> getAllSimulations() {
        return this.simulationRepository.findAll();
    }

    @Override
    public Simulation create(Simulation simulation) {
    	
    	PopulationSimulation[] simDays;
    	final long[] infectIncPerDay;
    	simDays = new PopulationSimulation[simulation.getTs()];
		infectIncPerDay = new long[simulation.getTs()];
    	
    	
      //  order.setDateCreated(LocalDate.now());
    	//List<PopulationSimulation> populations = new ArrayList<>();
    	//solve(simulation, populations);
    	runSimulation(simulation,simDays,infectIncPerDay);
    	List<PopulationSimulation> populations=new ArrayList<PopulationSimulation>(Arrays.asList(simDays));
    	for (PopulationSimulation populationSimulation : populations) {
    		System.out.println(populationSimulation);
			
		}
    	//populations.addAll(simDays);
    	simulation.setPopulationSimulation(populations);
    	Simulation sim=simulationRepository.save(simulation);
    	//populationSimulationRepository.saveAll(populations);
        return sim;
    }
    
    
	public void runSimulation(Simulation simulation,PopulationSimulation []simDays, final long []infectIncPerDay)
	{
		long pi, pv, pm, pr;
		
		int day=simulation.getTs();
    	
    	double R=simulation.getR();
    	
    	long P=simulation.getP();
    	
    	double M=simulation.getM();
    	int Ti=simulation.getTi(); // ilosc dni od zarazenia do wyzdro
    	int Tm= simulation.getTm();// ilosc dni od zarazenia do smierc
    	int Ts =simulation.getTs();
    	int I =simulation.getI();
    	
    	infectIncPerDay[0] = I;
		simDays[0] = new PopulationSimulation((long)I, (long)((P - I)), 0L, 0L,0L,simulation);
		
        for (int i = 1; i < Ts; i++)
        {
        	long newInfections = (long)(R * simDays[i - 1].getPi());
        	long newDeaths = 0;
        	long newRecovered = 0;
        	
        	if (newInfections >= simDays[i - 1].getPv())
        	{
        		newInfections = simDays[i - 1].getPv();
        	}
        	infectIncPerDay[i] = newInfections;
        	pv = simDays[i - 1].getPv() - newInfections;
        	
        	if (i >= Tm)
        	{
        		if (simDays[i - 1].getPi() >= M)
        		{
        		    newDeaths = (long)M;
        		}
        		else if (simDays[i - 1].getPi() > 0 && simDays[i - 1].getPi() < M)
        		{
        			newDeaths = simDays[i - 1].getPi();
        		}
        	}
        	pm = simDays[i - 1].getPm() + newDeaths;
        	
        	if (i >= Ti && simDays[i - 1].getPi() > newDeaths && infectIncPerDay[i - Ti] > 0)
        	{
        	    if (i >= Tm)
        	    {
        		    newRecovered = infectIncPerDay[i - Ti] - (long)M;
        	    }
        	    else
        	    {
        	    	newRecovered = infectIncPerDay[i - Ti];
        	    }
        	}
        	else
        	{
        		newRecovered = 0;
        	}
        	pr = simDays[i - 1].getPr() + newRecovered;
        	
        	pi = simDays[i - 1].getPi() + newInfections - newDeaths;
        	if (pi >= newRecovered)
        	{
        	    pi -= newRecovered;
        	}
        	else
        	{
        	    pi = 0;
        	}
	
        	simDays[i] = new PopulationSimulation(pi, pv, pm, pr,(long)(i), simulation);
        }
	}
    
   

    
    /*
	private void solve(Simulation simulation,  List<PopulationSimulation> populations) {
		// TODO Auto-generated method stub
    	
    	PopulationSimulation populationSimulation = new PopulationSimulation();
    	//List<PopulationSimulation> populations = new ArrayList<>();
    	
    	int Pi= simulation.getI();
    	int Pv=(int)(simulation.getP()-Pi);
    	int Pm=0;
    	int Pr=0;
    	
    	int day=simulation.getTs();
    	
    	double R=simulation.getR();
    	
    	long P=simulation.getP();
    	
    	double M=simulation.getM();
    	int Ti=simulation.getTi(); // ilosc dni od zarazenia do wyzdro
    	int Tm= simulation.getTm();// ilosc dni od zarazenia do smierc
    	
    	int[]tab=new int[(int) day];
    	int[]tab2=new int[(int)day];
    	tab[0]=(int) Pi;
    	tab2[0]=0;
    	
    	int mDied=0;
    	
    	populationSimulation.setPi(Pi);
    	populationSimulation.setPv(Pv);
    	populationSimulation.setPm(Pm);
    	populationSimulation.setPr(Pr);
    	populationSimulation.setDaySimulation(1);
    	populationSimulation.setSimulation(simulation);
    	
    	populations.add(populationSimulation);
    	//populationSimulationRepository.save(populationSimulation);
    	
    	for(int i=1; i<=day;i++) {
    		Pi=(int) (Pi+Pi*R);
    		//Pm+=Pm;
    		//Pr+=Pr;
    		//Pv+=Pv;
    		
    		if(i>Tm) {
    		mDied= (int) (tab[(i-Tm)]*M);
    		tab[(i-Tm)]=(int) (tab[i-Tm]-mDied);
    		Pm=mDied;
    		}
    		if(i>Ti) {
    		Pr=tab[i-Ti];
    		Pv=(int) (P-(Pi+Pr+Pm));
    		}
    		
    		PopulationSimulation populationSim = new PopulationSimulation(Pi,Pv,Pm,Pr,i,simulation);
    		//populations.add(new PopulationSimulation(3L,Pi,Pv,Pm,Pr,(long)(i),simulation));	
    		populations.add(populationSim);
    		//populationSimulationRepository.saveAll(populations);
    		
    		//populationSimulationRepository.save(populationSim);
    	}
    	
    	for (PopulationSimulation p : populations) {
    		System.out.println(p);
			
		}
    	
		//populations.clear();
	}
*/
	@Override
    public Simulation  getSimulation(long id) {
        return simulationRepository
          .findById(id).orElseThrow();
    }
    
    @Override
	public void delete(long id) {
		simulationRepository.deleteById(id);
		
	}

    @Override
    public void update(Simulation simulation) {
        this.simulationRepository.save(simulation);
    }
}
