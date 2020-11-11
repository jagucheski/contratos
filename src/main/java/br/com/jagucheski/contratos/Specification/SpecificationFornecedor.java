package br.com.jagucheski.contratos.Specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.jagucheski.contratos.model.Fornecedor;

public class SpecificationFornecedor {

	public static Specification<Fornecedor> razaoSocial(String razaoSocial) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.like(root.get("razaoSocial"), "%" + razaoSocial + "%");
	}
	
	public static Specification<Fornecedor> cnpj(String cnpj) {
		return (root, criteriaQuery, criteriaBuilder) -> 
		criteriaBuilder.equal(root.get("cnpj"), "%" + cnpj + "%");
	}
	
	public static Specification<Fornecedor> email(String email) {
		return (root, criteriaQuery, criteriaBuilder) -> 
		criteriaBuilder.like(root.get("email"), "%" + email + "%");
	}
		
	public static Specification<Fornecedor> endereco(String endereco) {
		return (root, criteriaQuery, criteriaBuilder) -> 
		criteriaBuilder.like(root.get("endereco"), "%" + endereco + "%");
	}
}
