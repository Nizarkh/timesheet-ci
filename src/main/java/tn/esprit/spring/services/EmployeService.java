package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;




@Service
//@Transactional
public class EmployeService implements IEmployeService{
	
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository departementRepository;
	@Autowired
	ContratRepository contratRepository;
	
	
	private static final Logger l = Logger.getLogger(EmployeService.class);
	
	
	@Override
	public Employe ajouterEmploye(Employe employe) {
		try{		
		employeRepository.save(employe);
		l.info("en train d'ajouter un employe... ");
		}
		catch (Exception e) { l.error("Erreur d'ajout d'employée" + e); }
		l.info("l'employe '" + employe.getNom()+ " "+ employe.getPrenom()+"' est ajoutée a la base");
		return employe;
	}

	@Override
	public void affecterEmployeADepartement(int employeId, int depId) {
		
		Employe employe=employeRepository.findById(employeId).get();
		Departement departement=departementRepository.findById((long) depId).get();
		
		Set<Departement> deps=employe.getDepartements();
		deps.add(departement);
		employe.setDepartements(deps);
		employeRepository.save(employe);
		
	}

	@Override
	public Contrat ajouterContrat(Contrat contrat) {
		try{		
			contratRepository.save(contrat);
			l.info("en train d'ajouter un contrat... ");
			}catch (Exception e) { l.error("Erreur d'ajout du contrat" + e); }
		l.info("Le contrat avec la reference = '" + contrat.getReference()+ "' est ajoutée a la base");
		return contrat;
	}

	
	@Override
	public void affecterContratAEmploye(int contratId, int employeId) {
		Contrat contrat=contratRepository.findById(  contratId).get();
		Employe employe=employeRepository.findById( employeId).get();
		
		contrat.setEmploye(employe);
		contratRepository.save(contrat);
	
	}
	/*@Override
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
	
		Departement departement= departementRepository.findById((long) depId).get();
		if(departement!=null){
			Entreprise entreprise = entrepriseRepository.findById((long) entrepriseId).get();
			if(entreprise!=null){
				departement.setEntreprise(entreprise);
				departementRepository.save(departement);
			}
		}
		
	}*/
	
	@Override
	public String getEmployePrenomById(int employeId) {
		Optional<Employe> value = employeRepository.findById(employeId);
		if(value.isPresent()){
		return value.get().getPrenom();
		}
		return null;
	
	}



	@Override
	public Employe authenticate(String login, String password) {
	
		return employeRepository.getEmployeByEmailAndPassword(login, password);
	}

	@Override
	public int addOrUpdateEmploye(Employe employe) {
	employeRepository.save(employe);
	return employe.getId();
	}

	@Override
	public List<Employe> getAllEmployes() {
	 List<Employe> employes = (List<Employe>) employeRepository.findAll();
	 for(Employe e : employes){
			e.setContrat(null);
			e.setDepartements(null);
			e.setMissions(null);
		}
	 return employes;
	
	}
	
	@Override
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		List<Employe> employes=	employeRepository.getAllEmployeByEntreprise(entreprise);
		for(Employe e : employes){
			e.setDepartements(null);
		}
		return employes;	
	}
	@Override
	public Employe getEmployeByID(int employeId) {	

		Employe employe = employeRepository.findById(employeId).orElse(null);
		if (employe != null)
		{
		employe.getDepartements().clear();
		employe.getMissions().clear();
		employe.getContrat().setEmploye(null);
		}
	return employe ;
	}

	@Override
	public void deleteEmployeById(int employeId) {
		// TODO Auto-generated method stub
		employeRepository.deleteById(employeId);
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
	try{
		l.info("nouveau mail = " + email);
		l.info("en train de cherchée l'employe avec ID = " + employeId);
		Employe employe = employeRepository.findById(employeId).get();
		l.info("ancien e-mail trouvée = " + employe.getEmail());
		employe.setEmail(email);
		l.info("mise.à.jour d'e-mail du l'employée "+employe.getNom()+" "+employe.getPrenom() );
		employeRepository.save(employe);
		l.info("Enregistrement.... " );
	}
	catch (Exception e) { l.error("Erreur de m.à.jour e-mail" + e); }
		
		
	}

	public void deleteContratById(int contratId) {
		// TODO Auto-generated method stub
		
		contratRepository.deleteById(contratId);
	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}

	@Override
	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}
	

	@Override
	public Double getSalaireMoyenByDepartementId(int depId) {
		
		return employeRepository.getSalaireMoyenByDepartementId((long)depId);
	}

	public void desaffecterEmployeDuDepartement(int employeId, int depId) {
		Departement dep = departementRepository.findById((long) depId).get();
		Employe empl = employeRepository.findById(employeId).get();
		
		dep.getEmployes().remove(empl);
		
	}

	@Override
	public List<Departement> getDepartementByEmployeId(int employeId) {
		List<Departement> deps =  employeRepository.getDepartementByEmployeIdJPQL(employeId);
		for(Departement e : deps){
			e.setEmployes(null);
			e.setMissions(null);
			e.setEntreprise(null);
		}
		
		return deps;
	}
	

}
