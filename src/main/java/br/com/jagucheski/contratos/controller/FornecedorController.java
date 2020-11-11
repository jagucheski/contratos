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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jagucheski.contratos.model.Fornecedor;
import br.com.jagucheski.contratos.service.FornecedorService;


@Controller
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;
	
    @RequestMapping("/cadastroFornecedor")
    public String cadastroFiscal(Model model){
    	model.addAttribute("fornecedores",fornecedorService.obterTodos()); 
    	return "cadastroFornecedor";
    }
    
    @RequestMapping("/listaFornecedores")
    public String listaFornecedores(){
    	return "listaFornecedores";
    }
  
    @RequestMapping(value = "/salvarFornecedor", method = RequestMethod.POST )
    @Transactional
	public String salvar(@ModelAttribute @Valid Fornecedor fornecedor, BindingResult result, Model model, RedirectAttributes redirectAttrs){
    	if(result.hasErrors()) {
    		redirectAttrs.addFlashAttribute("mensagem", "Preencha os campos obrigatórios*");
    	}else {
    		fornecedorService.salvar(fornecedor);
	    	redirectAttrs.addFlashAttribute("mensagem", "Fornecedor Cadastrado com sucesso!");
    	}
    	model.addAttribute("fornecedores",fornecedorService.obterTodos());
    	return "redirect:cadastroFornecedor";	
	}
    
    @RequestMapping(value = "/excluirFornecedor", method = RequestMethod.POST )
	public String excluir(Long fornecedorId, RedirectAttributes redirectAttrs){
    	fornecedorService.excluir(fornecedorId);
    	redirectAttrs.addFlashAttribute("mensagem", "Fornecedor Excluído com sucesso!");
		return "redirect:cadastroFornecedor";
	}

    @RequestMapping("/detalharFornecedor")
    public ModelAndView detalharFornecedor(Long fornecedorId){
    	ModelAndView model = new ModelAndView("detalharFornecedor");
    	model.addObject("fornecedor", fornecedorService.findById(fornecedorId));
    	return model;
    }
    
    @RequestMapping(value = "/atualizarFornecedor", method = RequestMethod.POST )
	public String atualizar(@ModelAttribute @Valid Fornecedor fornecedor, BindingResult result, Model model){
    	if(result.hasErrors()) {
    		model.addAttribute("mensagem", "Preencha os campos obrigatórios*");
    	}else {
    		fornecedorService.salvar(fornecedor);
    		model.addAttribute("mensagem", "Fornecedor atualizado com sucesso!");
    	}
    	return "detalharFornecedor";	
	}
    
    @RequestMapping(value = "/pesquisarFornecedores", method = RequestMethod.POST )
	public ModelAndView pesquisarFornecedores(@ModelAttribute Fornecedor fornecedor){
    	ModelAndView model = new ModelAndView("listaFornecedores");
    	
    	List<Fornecedor> fornecedores = fornecedorService.findAll(fornecedor);
    			    	
    	model.addObject("fornecedores", fornecedores);
    	return model;
	}
}