package tech.lucaschaves.lcmeta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.lucaschaves.lcmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{

}
