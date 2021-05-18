package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;



@Service
public class ContratImpl implements IContratService{
	@Autowired
	ContratRepository contratRepository;

	@Override
	public int ajouterContrat(Contrat contrat) {
		// TODO Auto-generated method stub
		contratRepository.save(contrat);
		return 1;
	}

	public List<Contrat> getAllContrat() {

		return null;
	}

	@Override
	public void addcontrat(Contrat contrat) {
		contratRepository.save(contrat);
		
		
	}



}
