package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Contrat;



public interface IContratService {
	
	int ajouterContrat(Contrat contrat);

	List<Contrat> getAllContrat();

	void addcontrat(Contrat contrat);




}
