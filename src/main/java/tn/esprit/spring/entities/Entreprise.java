package tn.esprit.spring.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Entreprise {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id; 
	private String name;
	private String raisonSocial;
	
	//@OneToMany  Departement *  1 entreprise
		@OneToMany (cascade=CascadeType.ALL, mappedBy = "entreprise")
		private	Set< Departement > departements;
	
	
	
	
	public Entreprise(Long id, String name, String raisonSocial) {
			super();
			this.id = id;
			this.name = name;
			this.raisonSocial = raisonSocial;
		}
	@Override
		public String toString() {
			return "Entreprise [id=" + id + ", name=" + name + ", raisonSocial=" + raisonSocial + ", departements="
					+ departements + "]";
		}
	public Entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Entreprise(String name, String raisonSocial) {
		super();
		this.name = name;
		this.raisonSocial = raisonSocial;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRaisonSocial() {
		return raisonSocial;
	}
	public void setRaisonSocial(String raisonSocial) {
		this.raisonSocial = raisonSocial;
	}
	public Set<Departement> getDepartements() {
		return departements;
	}
	public void setDepartements(Set<Departement> departements) {
		this.departements = departements;
	}


	
}
