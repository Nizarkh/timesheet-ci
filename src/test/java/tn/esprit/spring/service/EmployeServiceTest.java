package tn.esprit.spring.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IEmployeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceTest {
	
	@Autowired
	IEmployeService ES;
	@Autowired
	EmployeRepository ER;
	
	@Test
	public void testajouterEmploye() {
		Employe E = new Employe("Nizar", "Khlifi", "Test@esprit.tn", "passwordTest", true, Role.CHEF_DEPARTEMENT);
		Employe addedEmploye = ES.ajouterEmploye(E);
		Assert.assertEquals(E.getNom(), addedEmploye.getNom());
	}
	@Test
	public void testajouterContrat() throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2015-03-23");
		Contrat c = new Contrat(2, d, "CDI", 1500);
		Contrat addedContrat = ES.ajouterContrat(c);
		Assert.assertEquals(c.getReference(), addedContrat.getReference());
	}
	
	@Test
	public void testmettreAjourEmailByEmployeId() {
		String email = "emailupdated@test.tn";
		int employeTestId = 5;
		ES.mettreAjourEmailByEmployeId(email, employeTestId);
		Assert.assertEquals(email, ES.getEmployeByID(employeTestId).getEmail());
		
	}
	
	@Test
	public void testdeleteEmployeById() {
		ES.deleteEmployeById(3);
		Assert.assertNull(ES.getEmployeByID(3));
	}
	
	@Test
	public void testaffecterContratAEmploye() throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2015-03-23");
		Contrat c = new Contrat(1, d, "CDI", 1500);
		ES.ajouterContrat(c);
		ES.affecterContratAEmploye(1, 1);
		Assert.assertEquals("CDI", ES.getEmployeByID(1).getContrat().getTypeContrat());
		
	}
	@Test
	public void testaffecterEmployeADepartement() {
		ES.affecterEmployeADepartement(2, 1);
	}
	@Test
	public void testgetEmployePrenomById(){
		Employe E = new Employe("NameTest", "Prenom", "Test@esprit.tn", "passwordTest", true, Role.CHEF_DEPARTEMENT);
		ES.ajouterEmploye(E);
		Assert.assertEquals(E.getPrenom(), ES.getEmployePrenomById(4));
	}
	@Test
	public void testgetNombreEmployeJPQL() {
		int nombre = ES.getNombreEmployeJPQL();
		Assert.assertEquals(3, nombre);
	}
	
	
	@Test
	public void testgetAllEmployes() {
		List<Employe> listEmploye = ES.getAllEmployes(); 
		
		Assert.assertEquals(3, listEmploye.size());
	}
	
	
	
	
	
	
}
