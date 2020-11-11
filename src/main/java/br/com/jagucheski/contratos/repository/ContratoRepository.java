package br.com.jagucheski.contratos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.jagucheski.contratos.dto.ContratoFiscal;
import br.com.jagucheski.contratos.model.Contrato;

@Repository
public interface ContratoRepository extends CrudRepository<Contrato, Long>{

	/**
	 * Para uso de DTO com Spring Data as funcoes get devem ter o mesmo nome da coluna do SQL.
	 * Ex.:  'getNumero' no DTO,  'c.numero'         no SQL;
	 * 		 'getFiscal' no DTO,  'f.nome as fiscal' no SQL;
	 * */
	@Query(value = "SELECT c.id, c.numero, c.valor, c.objeto, f.nome as fiscal FROM contrato c inner join fiscal f on f.id = c.fiscal_id where f.nome like %:nomeFiscal%" , nativeQuery = true)
	List<ContratoFiscal> findContratoFiscal(String nomeFiscal);
	
}
