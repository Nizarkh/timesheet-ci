package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;




public interface IEmployeService {
	
	Employe ajouterEmploye(Employe employe);

	void affecterEmployeADepartement(int employeId, int depId);

	Contrat ajouterContrat(Contrat contrat);

	void affecterContratAEmploye(int contratId, int employeId);

	String getEmployePrenomById(int employeId);

	List<String> getAllEmployeNamesJPQL();

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise);

	Double getSalaireMoyenByDepartementId(int depId);

	Employe authenticate(String login, String password);

	int addOrUpdateEmploye(Employe employe);

	List<Employe> getAllEmployes();

	void deleteEmployeById(int employeId);

	Employe getEmployeByID(int employeId);
	
	public int getNombreEmployeJPQL();
	public List<Departement> getDepartementByEmployeId(int employeId);
	public void mettreAjourEmailByEmployeId(String email, int employeId);
}
