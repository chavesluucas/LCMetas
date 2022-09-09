package tech.lucaschaves.lcmeta.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.lucaschaves.lcmeta.entities.Sale;
import tech.lucaschaves.lcmeta.repositories.SaleRepository;

//services é a nossa regra de negocios

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	//Esse é a regra de negocio, nomeei essa regra como findAll, porque a função dela é encontrar as informações no banco de dados
	public List<Sale> findAll(){
		//Escrevi de uma forma mais facil de entender, mas a função a baixo, é uma Lista de sales que recebe a função findAll do repository
		List<Sale> listSale = repository.findAll();
		
		//E retorna uma lista de tudo que foi encontrado no banco de dados
		return listSale;
	}
	
}
