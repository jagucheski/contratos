package br.com.jagucheski.contratos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jagucheski.contratos.model.Fiscal;
import br.com.jagucheski.contratos.repository.FiscalRepository;

@Service
public class FiscalService {

	@Autowired
	private FiscalRepository fiscalRepository;
	
	public void salvar(Fiscal fiscal) {
		fiscalRepository.save(fiscal);
	}
	
	public void excluir(Long id) {
		fiscalRepository.deleteById(id);
	}
	
	public Iterable<Fiscal> obterTodos(){
		return fiscalRepository.findAll();
	}
	
	public Fiscal findById(Long id) {
		return fiscalRepository.findById(id).get(); 
	}
	
	public void deleteAll() {
		fiscalRepository.deleteAll(); 
	}

	public List<String> searchFiscalAutocomplete(String nome) {
		return fiscalRepository.searchFiscalAutocomplete(nome); 
	}

	public Fiscal findByNome(String fiscalNome) {
		return fiscalRepository.findByNome(fiscalNome); 
	}
		
}
