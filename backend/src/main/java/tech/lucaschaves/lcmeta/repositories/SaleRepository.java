package tech.lucaschaves.lcmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tech.lucaschaves.lcmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{

	//Como o JPARepository não tem essas funções, vamos ter que criar na mão
	//Traduzindo a Query = Selecione o objeto do tipo sale ONDE a data do objeto ESTEJA ENTRE valor minimo e valor maximo
	@Query("SELECT obj FROM Sale obj WHERE obj.date BETWEEN :min AND :max ORDER BY obj.amount DESC")
	Page<Sale> findAllSales(LocalDate min, LocalDate max, Pageable pageable);
	
}
