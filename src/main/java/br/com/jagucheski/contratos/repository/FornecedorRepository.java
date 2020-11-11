package br.com.jagucheski.contratos.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.jagucheski.contratos.model.Fornecedor;


/**
 * Acrescentado JpaSpecificationExecutor para utilização de JPA Criteria 
 * JpaSpecificationExecutor: Interface to allow execution of {@link Specification}s based on the JPA criteria API.
 * */

@Repository
public interface FornecedorRepository extends CrudRepository<Fornecedor, Long>, JpaSpecificationExecutor<Fornecedor> {

	@Query(value = "select f.razaoSocial from Fornecedor f where f.razaoSocial like %:razaoSocial%")
	List<String> searchFormecedorAutocomplete(String razaoSocial);

	Fornecedor findByRazaoSocial(String razaoSocial);
	
}