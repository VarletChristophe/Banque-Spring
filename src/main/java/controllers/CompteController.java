package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Compte;

@RestController
@RequestMapping("comptes")
public class CompteController {

	// FAKE Base de Données
	private List<Compte> comptes = initCompte();
	
	
	@GetMapping() //localhost:8080/clients --> GET
	public List<Compte> findAll() {
		return this.comptes;
	}
	
	
	// CRUD : Create / Read / Update / Delete
	//Read
	@GetMapping("/{id}") //localhost:8080/users/id --> GET
	public Compte findById(@PathVariable int id) {
		if (id > this.comptes.size() - 1) {
			return null;
		}
		return this.comptes.get(id);
	}
	
	//Exercice ... User qui donne la liste des comptes qui ont le numero "numero"
	@GetMapping("/numero/{numero}") //localhost:8080/users/nom/user_nom--> GET
	public List<Compte> findByNumero(@PathVariable String numero) {
		// Initialisation d'une liste de compte vide
		List<Compte> comptes = new ArrayList<>();
		// pour chaque utilisateur de mal liste
		for (Compte compte : this.comptes) {
			if (numero.equals(compte.getNumero())) {
				comptes.add(compte);
			}
		}
		return comptes;
	}
	
	//Create
	@PostMapping() //localhost:8080/users  --> POST
	public Compte create(@RequestBody Compte compte) {
		this.comptes.add(compte);
		return compte;
	}
	
	//Update
	@PutMapping("/{id}") //localhost:8080/users/id  --> PUT
	public Compte update(@PathVariable int id, @RequestBody Compte compte) {
		// Récupère l'ancien compte pas encore modifié
		Compte newCompte = findById(id);
		// Modifie les éléments de l'ancien compte avec ses nouveuax attributs
		newCompte.setNumero(compte.getNumero());
		newCompte.setSolde(compte.getSolde());
		return newCompte;
	}
	
	//Delete
	@DeleteMapping("/{id}") //localhost:8080/users  --> POST
	public Compte delete(@PathVariable int id) {
		// Récupère l'ancien compte pas encore modifié
		Compte compte = findById(id);
		this.comptes.remove(id);
		return compte;
	}
	
	
	/**
	 * Méthode qui crée et retourne une liste intanciée de clients
	 * @return
	 */
	public List<Compte> initCompte() {
		List<Compte> comptes = new ArrayList<>();
		comptes.add(new Compte(1234, 2500.0));
		comptes.add(new Compte(4321, 1100.0));
		comptes.add(new Compte(2431, 950.0));
		comptes.add(new Compte(3124, 12500.0));
		comptes.add(new Compte(9009, 2000.0));
		return comptes;
	}
}
