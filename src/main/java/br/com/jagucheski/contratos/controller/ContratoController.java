package br.com.jagucheski.contratos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jagucheski.contratos.model.Contrato;
import br.com.jagucheski.contratos.service.ContratoService;
import br.com.jagucheski.contratos.service.FiscalService;
import br.com.jagucheski.contratos.service.FornecedorService;

@Controller
public class ContratoController {

	@Autowired
	private ContratoService contratoService;

	@Autowired
	private FiscalService fiscalService;

	@Autowired
	private FornecedorService fornecedorService;

	@RequestMapping("/cadastroContrato")
	public String cadastroContrato(Model model) {
		Iterable<Contrato> contratos = contratoService.obterTodos();
		model.addAttribute("contratos", contratos);
		return "cadastroContrato";
	}

	@RequestMapping(value = "/salvarContrato", method = RequestMethod.POST)
	@Transactional
	public String salvar(@ModelAttribute @Valid Contrato contrato, BindingResult result, Model model,
			RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			model.addAttribute("mensagem", "Preencha os campos obrigatórios*");
			return "cadastroContrato";
		} else {
			contrato.setFiscal(fiscalService.findByNome(contrato.getFiscalNome()));
			contrato.setFornecedor(fornecedorService.findByRazaoSocial(contrato.getFornecedorRazao()));
			contratoService.salvar(contrato);
			redirectAttrs.addFlashAttribute("mensagem", "Contrato Cadastrado com sucesso!");
		}
		model.addAttribute("contratos", contratoService.obterTodos());
		return "redirect:cadastroContrato";
	}

	@RequestMapping(value = "/excluirContrato", method = RequestMethod.POST)
	public String excluir(Long contratoId, RedirectAttributes redirectAttrs) {
		contratoService.excluir(contratoId);
		redirectAttrs.addFlashAttribute("mensagem", "Contrato Excluído com sucesso!");
		return "redirect:cadastroContrato";
	}

	@RequestMapping("/detalharContrato")
	public ModelAndView detalharContrato(Long contratoId) {
		ModelAndView model = new ModelAndView("detalharContrato");
		Contrato contrato = contratoService.findById(contratoId);
		contrato.garregaCampos();
		model.addObject("contrato", contrato);
		return model;
	}

	@RequestMapping(value = "/atualizarContrato", method = RequestMethod.POST)
	public String atualizar(@ModelAttribute Contrato contratoTemp, BindingResult result, Model model) {
		if (!validaCampos(contratoTemp)) {
			model.addAttribute("mensagem", "Preencha os campos obrigatórios*");
			Contrato contrato = contratoService.findById(contratoTemp.getId());
			contrato.garregaCampos();
			model.addAttribute("contrato", contrato);			
		} else {
			Contrato contrato = contratoService.findById(contratoTemp.getId());
			contrato.setObjeto(contratoTemp.getObjeto());
			contrato.setValor(contratoTemp.getValor());
			contrato.setFiscal(fiscalService.findByNome(contratoTemp.getFiscalNome()));
			contrato.setFornecedor(fornecedorService.findByRazaoSocial(contratoTemp.getFornecedorRazao()));

			contratoService.salvar(contrato);
			model.addAttribute("mensagem", "Contrato atualizado com sucesso!");
		}
		return "detalharContrato";
	}

		/**
	 * Carrega campo fiscal autocomplete
	 **/
	@Transactional
	@RequestMapping(value = "/searchFiscalAutocomplete", method = RequestMethod.GET, headers = "Accept=*/*")
	@ResponseBody
	public List<String> searchFiscalAutocomplete(@RequestParam("term") String nomeFiscal) {
		return fiscalService.searchFiscalAutocomplete(nomeFiscal.replace(",", ""));
	}

	/**
	 * Carrega campo fornecedor autocomplete
	 **/
	@Transactional
	@RequestMapping(value = "/searchFornecedorAutocomplete", method = RequestMethod.GET, headers = "Accept=*/*")
	@ResponseBody
	public List<String> searchFornecedorAutocomplete(@RequestParam("term") String razaoSocial) {
		return fornecedorService.searchFornecedorAutocomplete(razaoSocial.replace(",", ""));
	}
	
	private boolean validaCampos(Contrato contrato) {
		boolean camposValidos = true;
		
		if(contrato.getObjeto().isEmpty() || contrato.getObjeto() == null)
			camposValidos = false;

		if(contrato.getFiscalNome().isEmpty())
			camposValidos = false;

		if(contrato.getFornecedorRazao().isEmpty())
			camposValidos = false;
		
		if(contrato.getValor() == null)
			camposValidos = false;
		
		return camposValidos;
	}

}