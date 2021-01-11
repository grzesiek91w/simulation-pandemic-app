package com.gwachala.springapp.model;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Entity
public class Simulation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
   // @Basic(optional = false)
    private String N; // nazwasymulacji

    
    private Long P; //wielkość

    private Integer I; //poczatkowa ilosc zakazonych
    
    private Double R; //wskaznik ile osob zaraz
    
    private Double M; // wskaznik smiertelnosci
    
    private Integer Ti; //ilosc dni od zarazenia do wyzdrowienia
    
    private Integer Tm; //ilosc dni od zarazenia do smierci
    
    private Integer Ts; //ilosc dni dla których ma byc przeprowadzona symulacja
   
    
    @OneToMany(mappedBy="simulation",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<PopulationSimulation> populationSimulation;

    
    
	public Simulation(String n, Long p, Integer i, Double r, Double m, Integer ti, Integer tm, Integer ts) {
		super();
		N = n;
		P = p;
		I = i;
		R = r;
		M = m;
		Ti = ti;
		Tm = tm;
		Ts = ts;
	}
	
	


	public Simulation() {
		super();
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getN() {
		return N;
	}


	public void setN(String n) {
		N = n;
	}


	public Long getP() {
		return P;
	}


	public void setP(Long p) {
		P = p;
	}


	public Integer getI() {
		return I;
	}


	public void setI(Integer i) {
		I = i;
	}


	public Double getR() {
		return R;
	}


	public void setR(Double r) {
		R = r;
	}


	public Double getM() {
		return M;
	}


	public void setM(Double m) {
		M = m;
	}


	public Integer getTi() {
		return Ti;
	}


	public void setTi(Integer ti) {
		Ti = ti;
	}


	public Integer getTm() {
		return Tm;
	}


	public void setTm(Integer tm) {
		Tm = tm;
	}


	public Integer getTs() {
		return Ts;
	}


	public void setTs(Integer ts) {
		Ts = ts;
	}


	public List<PopulationSimulation> getPopulationSimulation() {
		return populationSimulation;
	}


	public void setPopulationSimulation(List<PopulationSimulation> populationSimulation) {
		this.populationSimulation = populationSimulation;
	}


	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((I == null) ? 0 : I.hashCode());
		result = prime * result + ((M == null) ? 0 : M.hashCode());
		result = prime * result + ((N == null) ? 0 : N.hashCode());
		result = prime * result + ((P == null) ? 0 : P.hashCode());
		result = prime * result + ((R == null) ? 0 : R.hashCode());
		result = prime * result + ((Ti == null) ? 0 : Ti.hashCode());
		result = prime * result + ((Tm == null) ? 0 : Tm.hashCode());
		result = prime * result + ((Ts == null) ? 0 : Ts.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Simulation other = (Simulation) obj;
		if (I == null) {
			if (other.I != null)
				return false;
		} else if (!I.equals(other.I))
			return false;
		if (M == null) {
			if (other.M != null)
				return false;
		} else if (!M.equals(other.M))
			return false;
		if (N == null) {
			if (other.N != null)
				return false;
		} else if (!N.equals(other.N))
			return false;
		if (P == null) {
			if (other.P != null)
				return false;
		} else if (!P.equals(other.P))
			return false;
		if (R == null) {
			if (other.R != null)
				return false;
		} else if (!R.equals(other.R))
			return false;
		if (Ti == null) {
			if (other.Ti != null)
				return false;
		} else if (!Ti.equals(other.Ti))
			return false;
		if (Tm == null) {
			if (other.Tm != null)
				return false;
		} else if (!Tm.equals(other.Tm))
			return false;
		if (Ts == null) {
			if (other.Ts != null)
				return false;
		} else if (!Ts.equals(other.Ts))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "Simulation [id=" + id + ", N=" + N + ", P=" + P + ", I=" + I + ", R=" + R + ", M=" + M + ", Ti=" + Ti
				+ ", Tm=" + Tm + ", Ts=" + Ts + ", populationSimulation=" + populationSimulation + "]";
	}




	



	




	

	

	

	
    
    
}