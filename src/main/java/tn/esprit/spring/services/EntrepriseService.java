package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;



@Service
public class EntrepriseService implements IEntrepriseService{
	
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	DepartementRepository departementRepository;
	
	private static final Logger l = LogManager.getLogger(EntrepriseService.class);
	
	@Override
	public int ajouterEntreprise(Entreprise entreprise) {
		
		/*if (entreprise.getId() != null) {
            //cannot create 
            return 0;
		}
		*/
		entrepriseRepository.save(entreprise);
		//return Integer.parseInt(entrepriseRepository.save(entreprise).getId().toString());
		return 1;
	}
	
	
	/*@Override
	public int ajouterEntreprise(Entreprise entreprise) {
		return Integer.parseInt(entrepriseRepository.save(entreprise).getId().toString());
	}*/
	
	
	
	
	//**********************************************************************************
	@Override
	public Entreprise addEntreprise(Entreprise entreprise) {
		
		return entrepriseRepository.save(entreprise);
	}
	//***********************************************************************************	

	@Override
	public int ajouterDepartement(Departement dep) {
		
		/*if (entreprise.getId() != null) {
        //cannot create 
        return 0;
	}
	*/
		departementRepository.save(dep);  return 1;
	
		//return Integer.parseInt(departementRepository.save(dep).getId().toString());
	}

	@Override
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
	
		Departement departement= departementRepository.findById((long) depId).get();
		if(departement!=null){
			Entreprise entreprise = entrepriseRepository.findById((long) entrepriseId).get();
			if(entreprise!=null){
				departement.setEntreprise(entreprise);
				departementRepository.save(departement);
			}
		}
		
	}

	@Override
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		// TODO Auto-generated method stub
		Entreprise entreprise = entrepriseRepository.findById((long) entrepriseId).get();
		Set<Departement> allDepartment = entreprise.getDepartements();
		List<String> ListDepartement = new ArrayList<>();
		for(Departement d : allDepartment){
			
			ListDepartement.add(d.getName());
		}
		return ListDepartement;
		
	}
	public Entreprise getEntrepriseById(int entrepriseId) {
		return entrepriseRepository.findById((long) entrepriseId).get();	
	}
	
}
