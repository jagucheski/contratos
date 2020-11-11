package br.com.jagucheski.contratos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jagucheski.contratos.dto.ContratoFiscal;
import br.com.jagucheski.contratos.model.Contrato;
import br.com.jagucheski.contratos.model.Fiscal;
import br.com.jagucheski.contratos.model.Fornecedor;
import br.com.jagucheski.contratos.service.ContratoService;
import br.com.jagucheski.contratos.service.FiscalService;
import br.com.jagucheski.contratos.service.FornecedorService;

@SpringBootTest
class ContratosApplicationTests {

	@Autowired
	private FiscalService fiscalService;

	@Autowired
	private FornecedorService fornecedorService;
	
	@Autowired
	private ContratoService contratoService;
	
	@Test
	void procuraFiscalNome() {
		
		contratoService.deleteAll();
		fiscalService.deleteAll();
		
		Fiscal f1 = new Fiscal("Joao", "001", "f1@gmail");
		Fiscal f2 = new Fiscal("Jose", "002", "f2@gmail");
		Fiscal f3 = new Fiscal("MARIA LOURDES", "003", "f3@gmail");
				
		fiscalService.salvar(f1);
		fiscalService.salvar(f2);
		fiscalService.salvar(f3);
		
		List<String> fiscais =  fiscalService.searchFiscalAutocomplete("lou");
		fiscais.forEach(s->System.out.println(s));
		
		assertEquals(1, fiscais.size());
	}

	@Test
	void procuraFornecedorRazaoSocial() {
		
		contratoService.deleteAll();
		fornecedorService.deleteAll();
		
		Fornecedor f1 = new Fornecedor("PM Parobé", "88.372.883/0001-01", "administracao@parobe.rs.gov.br", "(51)3543-8600", "Av. Joao mosmann Filho");
		Fornecedor f2 = new Fornecedor("SM Telecom", "12.372.883/1000-01", "sm@gmail", "(51)3541-1234", "Av. Getuli Vargas");
		Fornecedor f3 = new Fornecedor("Estrategia", "44.355.883/0001-01", "estrategia@hotmail.com", "(54)3669-1245", "Av. Joao Filho");

		fornecedorService.salvar(f1);
		fornecedorService.salvar(f2);
		fornecedorService.salvar(f3);
		
		List<String> fornecedores =  fornecedorService.searchFornecedorAutocomplete("te");
		fornecedores.forEach(s->System.out.println(s));
		
		assertEquals(2, fornecedores.size());
	}
	
	@Test
	void procuraContratoFiscalDto() {
		
		Contrato c1 = new Contrato("obj1", "001/2020", LocalDate.now(), LocalDate.now().plusYears(4), 12000.0, fiscalService.findByNome("Joao"),fornecedorService.findByRazaoSocial("PM Parobé"));
		Contrato c2 = new Contrato("obj2", "002/2020", LocalDate.now(), LocalDate.now().plusYears(4), 12000.0, fiscalService.findByNome("Jose"),fornecedorService.findByRazaoSocial("SM Telecom"));
		Contrato c3 = new Contrato("obj3", "003/2020", LocalDate.now(), LocalDate.now().plusYears(4), 12000.0, fiscalService.findByNome("MARIA LOURDES"),fornecedorService.findByRazaoSocial("Estrategia"));
		
		contratoService.salvar(c1);
		contratoService.salvar(c2);
		contratoService.salvar(c3);
		
		List<ContratoFiscal> contratos = contratoService.findContratoFiscal("maria");
		contratos.forEach(c->System.out.println("ID: " + c.getId() + " Número: " + c.getNumero() + " Valor: " +  c.getValor() + " Objeto: " + c.getObjeto() + " Fiscal: " + c.getFiscal() ));
		
		assertEquals(1, contratos.size());
	}

}
