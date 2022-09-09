package tech.lucaschaves.lcmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.lucaschaves.lcmeta.entities.Sale;
import tech.lucaschaves.lcmeta.services.SaleService;
import tech.lucaschaves.lcmeta.services.SmsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/sales")
public class SaleController {

	@Autowired
	SaleService service;
	
	@Autowired
	SmsService sms;
	
	//Aqui é o controller, responsavel pela requisição HTTP
	//O controler então ele vai retornar responseEntity de uma Lista de Sale
	//E esse metodo decidir chamader de getAll, pois ele vai pegar/puxar as informações do banco e enviar para o front
	@GetMapping
	public ResponseEntity<Page<Sale>> getAll(@RequestParam(value="minDate", defaultValue="") String minDate, @RequestParam(value="maxDate", defaultValue="")String maxDate, Pageable pageable){ //minDate e maxDate é para conseguir pesquisar apenas vendas entre esse periodo
		//Aqui estou passando para uma lista de Sale(Que chamamos de page por causa da paginação) o serviço de findAll que criamos
		Page<Sale> listSale = service.findAll(minDate, maxDate, pageable);
		
		//Então, quando encontrar estou pedindo para retornar uma resposta HTTP OK e que retorne no corpo dela uma lista de Sale que o service buscou do banco de dados
		return ResponseEntity.ok().body(listSale);
	}
	
	//Preparando o endpoint para enviar o SMS
	@GetMapping("/{id}/notification")
	public void notifySms(@PathVariable Long id) {
		sms.sendSms(id);
	}
	
}
