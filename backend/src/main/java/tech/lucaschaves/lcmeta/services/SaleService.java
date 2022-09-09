package tech.lucaschaves.lcmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tech.lucaschaves.lcmeta.entities.Sale;
import tech.lucaschaves.lcmeta.repositories.SaleRepository;

//services é a nossa regra de negocios

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	//Esse é a regra de negocio, nomeei essa regra como findAll, porque a função dela é encontrar as informações no banco de dados
	public Page<Sale> findAll(String minDate, String maxDate, Pageable pageable){
		
		//Criamos uma variavel today (hoje) para pegar a data de hoje, passamos nessa variavel um localDate com o metodo ofInstante que preciamos passar dois parametros, então informamos que é para pegar o horario de agora e que o fuso horario vai ser o mesmo do sistema 
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		
		
		//convertendo o minDate e maxDate de string para date
		//Se minDate for vazio, transforme ele em today menos 365 dias(um ano atrás), se for informado continua normalmente a conversão
		LocalDate min = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
		//se maxDate for vazio, transforme ele em today, senão continua normalmente com a conversão
		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		
		//Escrevi de uma forma mais facil de entender, mas a função a baixo, é uma Lista(Que estamos chamando de Page por causa da paginação) de sales que recebe a função findAllSales que criamos no repository
		Page<Sale> listSale = repository.findAllSales(min, max,pageable);
		
		//E retorna uma lista de tudo que foi encontrado no banco de dados
		return listSale;
	}
	
}
