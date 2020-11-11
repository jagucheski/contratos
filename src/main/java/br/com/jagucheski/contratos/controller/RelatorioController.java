package br.com.jagucheski.contratos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.jagucheski.contratos.model.Fiscal;
import br.com.jagucheski.contratos.service.ContratoService;

@Controller
public class RelatorioController {

	@Autowired
	private ContratoService contratoService;

	@RequestMapping("/relatorioContratoFiscal")
	public String relatorioContratoFiscal() {
		return "relatorio/relatorioContratoFiscal";
	}

	@RequestMapping("/relatorioContratoFornecedor")
	public String relatorioContratoFornecedor() {
		return "relatorio/relatorioContratoFornecedor";
	}

	@RequestMapping(value = "/pesquisarContratoFiscal", method = RequestMethod.POST)
	public ModelAndView pesquisarContratoFiscal(@ModelAttribute Fiscal fiscal) {
		ModelAndView model = new ModelAndView("relatorio/relatorioContratoFiscal");
		if (fiscal.getNome().isEmpty()) {
			model.addObject("mensagem", "Preencha os campos de pesquisa");
		} else {
			model.addObject("contratos", contratoService.findContratoFiscal(fiscal.getNome()));
			model.addObject("mensagem", "Pesquisa realizada com sucesso!");
		}
		return model;
	}

}