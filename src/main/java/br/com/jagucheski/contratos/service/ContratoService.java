package br.com.jagucheski.contratos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jagucheski.contratos.dto.ContratoFiscal;
import br.com.jagucheski.contratos.model.Contrato;
import br.com.jagucheski.contratos.repository.ContratoRepository;

@Service
public class ContratoService {

	@Autowired
	private ContratoRepository contratoRepository;
	
	public void salvar(Contrato contrato) {
		contratoRepository.save(contrato);
	}
	
	public Iterable<Contrato> obterTodos(){
		return contratoRepository.findAll();
	}

	public void excluir(Long id) {
		contratoRepository.deleteById(id);
	}
	
	public Contrato findById(Long id) {
		return contratoRepository.findById(id).get(); 
	}
	 
	public List<ContratoFiscal> findContratoFiscal(String nome){
		return contratoRepository.findContratoFiscal(nome);
	}
		
	public void deleteAll(){
		contratoRepository.deleteAll();
	}
	
	
}
