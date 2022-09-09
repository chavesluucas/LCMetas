package tech.lucaschaves.lcmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.lucaschaves.lcmeta.entities.Sale;
import tech.lucaschaves.lcmeta.services.SaleService;

@RestController
@CrossOrigin("*")
@RequestMapping("/sale")
public class SaleController {

	@Autowired
	SaleService service;
	
	//Aqui é o controller, responsavel pela requisição HTTP
	//O controler então ele vai retornar responseEntity de uma Lista de Sale
	//E esse metodo decidir chamader de getAll, pois ele vai pegar/puxar as informações do banco e enviar para o front
	@GetMapping
	public ResponseEntity<List<Sale>> getAll(){
		//Aqui estou passando para uma lista de Sale o serviço de findAll que criamos
		List<Sale> listSale = service.findAll();
		
		//Então, quando encontrar estou pedindo para retornar uma resposta HTTP OK e que retorne no corpo dela uma lista de Sale que o service buscou do banco de dados
		return ResponseEntity.ok().body(listSale);
	}
	
}
