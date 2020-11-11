package br.com.jagucheski.contratos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.jagucheski.contratos.Specification.SpecificationFornecedor;
import br.com.jagucheski.contratos.model.Fornecedor;
import br.com.jagucheski.contratos.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	public FornecedorRepository fornecedorRepository;
	
	public void salvar(Fornecedor fiscal) {
		fornecedorRepository.save(fiscal);
	}
	
	public void excluir(Long id) {
		fornecedorRepository.deleteById(id);
	}
	
	public Iterable<Fornecedor> obterTodos(){
		return fornecedorRepository.findAll();
	}
	
	public Fornecedor findById(Long id) {
		return fornecedorRepository.findById(id).get(); 
	}
	
	public void deleteAll() {
		fornecedorRepository.deleteAll(); 
	}
	
	public List<String> searchFornecedorAutocomplete(String razaoSocial) {
		return fornecedorRepository.searchFormecedorAutocomplete(razaoSocial); 
	}

	public Fornecedor findByRazaoSocial(String razaoSocial) {
		return fornecedorRepository.findByRazaoSocial(razaoSocial); 
	}
	
	/**
	 * Foi acrescentado JpaSpecificationExecutor no FornecedorRepository para utilização do JPA Criteria. 
	 * Dessa forma é necessario a criação da classe SpecificationFornecedor
	 * */
	public List<Fornecedor> findAll(Fornecedor fornecedor){
		return fornecedorRepository.findAll(Specification.where(
				SpecificationFornecedor.razaoSocial(fornecedor.getRazaoSocial())
				.or(SpecificationFornecedor.email(fornecedor.getEmail()))
					.or(SpecificationFornecedor.cnpj(fornecedor.getCnpj()))
							.or(SpecificationFornecedor.endereco(fornecedor.getEndereco()))));
	}
	
}
