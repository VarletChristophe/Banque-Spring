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

import models.Client;

@RestController
@RequestMapping("clients")
public class ClientController {

	// FAKE Base de Données
	private List<Client> clients = initClient();
	
	
	@GetMapping() //localhost:8080/clients --> GET
	public List<Client> findAll() {
		return this.clients;
	}
	
	
	// CRUD : Create / Read / Update / Delete
	//Read
	@GetMapping("/{id}") //localhost:8080/users/id --> GET
	public Client findById(@PathVariable int id) {
		if (id > this.clients.size() - 1) {
			return null;
		}
		return this.clients.get(id);
	}
	
	//Exercice ... User qui donne la liste des clients qui ont le nom "nom"
	@GetMapping("/nom/{nom}") //localhost:8080/users/nom/user_nom--> GET
	public List<Client> findByNom(@PathVariable String nom) {
		// Initialisation d'une liste de nom vide
		List<Client> clients = new ArrayList<>();
		// pour chaque utilisateur de mal liste
		for (Client client : this.clients) {
			if (nom.toLowerCase().equals(client.getNom().toLowerCase())) {
				clients.add(client);
			}
		}
		return clients;
	}
	
	//Create
	@PostMapping() //localhost:8080/users  --> POST
	public Client create(@RequestBody Client client) {
		this.clients.add(client);
		return client;
	}
	
	//Update
	@PutMapping("/{id}") //localhost:8080/users/id  --> PUT
	public Client update(@PathVariable int id, @RequestBody Client client) {
		// Récupère l'ancien utilisateur pas encore modifié
		Client newClient = findById(id);
		// Modifie les éléments de m'ancien utilisateur avec ses nouveuax attributs
		newClient.setNom(client.getNom());
		newClient.setPrenom(client.getPrenom());
		newClient.setAge(client.getAge());
		newClient.setNumero(client.getNumero());
		return newClient;
	}
	
	//Delete
	@DeleteMapping("/{id}") //localhost:8080/users  --> POST
	public Client delete(@PathVariable int id) {
		// Récupère l'ancien client pas encore modifié
		Client client = findById(id);
		this.clients.remove(id);
		return client;
	}
	
	
	/**
	 * Méthode qui crée et retourne une liste intanciée de clients
	 * @return
	 */
	public List<Client> initClient() {
		List<Client> clients = new ArrayList<>();
		clients.add(new Client("Varlet", "Christophe", 53, 1234));
		clients.add(new Client("Varlet", "Dorothée", 50, 4321));
		clients.add(new Client("Varlet", "Apolline", 26, 2431));
		clients.add(new Client("Varlet", "Faustine", 24, 3124));
		clients.add(new Client("Hoste", "Vivien", 30, 9009));
		return clients;
	}
}
