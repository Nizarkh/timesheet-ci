package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;




@Entity
@Table( name = "Employe")
public class Employe implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="Id")
	private int id; 
	private String prenom;
	private String nom;
	private String email;
	private boolean actif;
	private String password;

	@Enumerated(EnumType.ORDINAL)
	Role role;
	
	@OneToOne (mappedBy= "employe")
	private	Contrat contrat ;
	
	//ManyToMany  Employe* * Mission
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private	Set<Mission> missions;
	
	//ManyToMany  Employe* * Departement
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private	Set<Departement> departements;

	public Employe() {
		super();
		// TODO Auto-generated constructor stub
	}

	





	public Employe(int id, String prenom, String nom, String email, boolean actif, String password, Role role) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.actif = actif;
		this.password = password;
		this.role = role;
	}


	public Employe(String nom, String prenom, String email, String password, boolean actif, Role role) {
		// TODO Auto-generated constructor stub
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.actif = actif;
		this.password = password;
		this.role = role;
	}

	public Employe(Integer employeIdToBeUpdated, String nom, String prenom, String email, String password,
			boolean actif, Role role) {
		// TODO Auto-generated constructor stub
		this.id = employeIdToBeUpdated;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.actif = actif;
		this.password = password;
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}







	public int getId() {
		return id;
	}







	public void setId(int id) {
		this.id = id;
	}







	public String getPrenom() {
		return prenom;
	}







	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}







	public String getNom() {
		return nom;
	}







	public void setNom(String nom) {
		this.nom = nom;
	}







	public String getEmail() {
		return email;
	}







	public void setEmail(String email) {
		this.email = email;
	}







	public boolean isActif() {
		return actif;
	}







	public void setActif(boolean actif) {
		this.actif = actif;
	}







	public String getPassword() {
		return password;
	}







	public void setPassword(String password) {
		this.password = password;
	}







	public Role getRole() {
		return role;
	}







	public void setRole(Role role) {
		this.role = role;
	}







	public Contrat getContrat() {
		return contrat;
	}







	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}







	public Set<Mission> getMissions() {
		return missions;
	}







	public void setMissions(Set<Mission> missions) {
		this.missions = missions;
	}







	public Set<Departement> getDepartements() {
		return departements;
	}







	public void setDepartements(Set<Departement> departements) {
		this.departements = departements;
	}


	
}
