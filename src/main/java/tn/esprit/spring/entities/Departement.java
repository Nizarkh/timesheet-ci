package tn.esprit.spring.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Departement {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id; 
	private String name;
	
	//ManyToMany  Employe* * Departement
	@ManyToMany(mappedBy= "departements" , cascade=CascadeType.ALL )
	private	Set<Employe> employes;
	
	//@OneToMany  Mission * 1 Departement
	@OneToMany (cascade=CascadeType.ALL, mappedBy ="departement")
	private	Set< Mission > missions;
	
	
	
	//@ManyToOne  Departement *  1 entreprise
		@ManyToOne
		private Entreprise entreprise;
	
	public Departement() {
		
		super();

	}
	public Departement(String name) {
		super();
		this.name = name;
	}
	
	public Departement(Long id, String name, Entreprise entreprise) {
		super();
		this.id = id;
		this.name = name;
		this.entreprise = entreprise;
	}
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public Set<Employe> getEmployes() {
		return employes;
	}
	public void setEmployes(Set<Employe> employes) {
		this.employes = employes;
	}
	public Set<Mission> getMissions() {
		return missions;
	}
	public void setMissions(Set<Mission> missions) {
		this.missions = missions;
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
	public Long getId() {
		return id;
	}
	
	
	
	


	
}
