package com.gwachala.springapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "orders")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="orderProducts")
public class PopulationSimulation implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Column(name="id", unique=true)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	private Long Pi; //licba osobzarazonych
	
	private Long Pv;  //liczba osob zdrowych podatnych na infekcje
	
	private Long Pm; //liczba osob zmarłych
	
	private Long Pr; //liczba osob, ktore wyzrowialy inabyly odpornosc

	private Long daySimulation; //dzien symulacji
	
	@ManyToOne
	@JoinColumn(name="simulation_id", nullable=false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Simulation simulation;
	
	
	

	public PopulationSimulation(Long pi, Long pv, Long pm, Long pr, Long daySimulation,
			Simulation simulation) {
		super();
		Pi = pi;
		Pv = pv;
		Pm = pm;
		Pr = pr;
		this.daySimulation = daySimulation;
		this.simulation = simulation;
	}
	
	

	public PopulationSimulation() {
		super();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	


	public Long getPi() {
		return Pi;
	}



	public void setPi(Long pi) {
		Pi = pi;
	}



	public Long getPv() {
		return Pv;
	}



	public void setPv(Long pv) {
		Pv = pv;
	}



	public Long getPm() {
		return Pm;
	}



	public void setPm(Long pm) {
		Pm = pm;
	}



	public Long getPr() {
		return Pr;
	}



	public void setPr(Long pr) {
		Pr = pr;
	}



	public Long getDaySimulation() {
		return daySimulation;
	}



	public void setDaySimulation(Long daySimulation) {
		this.daySimulation = daySimulation;
	}



	public Simulation getSimulation() {
		return simulation;
	}



	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Pi == null) ? 0 : Pi.hashCode());
		result = prime * result + ((Pm == null) ? 0 : Pm.hashCode());
		result = prime * result + ((Pr == null) ? 0 : Pr.hashCode());
		result = prime * result + ((Pv == null) ? 0 : Pv.hashCode());
		result = prime * result + ((daySimulation == null) ? 0 : daySimulation.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((simulation == null) ? 0 : simulation.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PopulationSimulation other = (PopulationSimulation) obj;
		if (Pi == null) {
			if (other.Pi != null)
				return false;
		} else if (!Pi.equals(other.Pi))
			return false;
		if (Pm == null) {
			if (other.Pm != null)
				return false;
		} else if (!Pm.equals(other.Pm))
			return false;
		if (Pr == null) {
			if (other.Pr != null)
				return false;
		} else if (!Pr.equals(other.Pr))
			return false;
		if (Pv == null) {
			if (other.Pv != null)
				return false;
		} else if (!Pv.equals(other.Pv))
			return false;
		if (daySimulation == null) {
			if (other.daySimulation != null)
				return false;
		} else if (!daySimulation.equals(other.daySimulation))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (simulation == null) {
			if (other.simulation != null)
				return false;
		} else if (!simulation.equals(other.simulation))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "PopulationSimulation [id=" + id + ", Pi=" + Pi + ", Pv=" + Pv + ", Pm=" + Pm + ", Pr=" + Pr
				+ ", daySimulation=" + daySimulation + ", simulation=" + simulation + "]";
	}
	
	
    
    
    
   
	

	
    
    
}