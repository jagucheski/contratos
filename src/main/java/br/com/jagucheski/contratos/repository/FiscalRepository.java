package br.com.jagucheski.contratos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.jagucheski.contratos.model.Fiscal;

@Repository
public interface FiscalRepository extends CrudRepository<Fiscal, Long> {

	@Query(value = "select f.nome from Fiscal f where f.nome like %:nome%")
	List<String> searchFiscalAutocomplete(String nome);

	Fiscal findByNome(String fiscalNome);
	
}
