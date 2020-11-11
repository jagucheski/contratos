package br.com.jagucheski.contratos.controller;

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

import br.com.jagucheski.contratos.model.Fiscal;
import br.com.jagucheski.contratos.service.FiscalService;


@Controller
public class FiscalController {

	@Autowired
	private FiscalService fiscalService;
	
    @RequestMapping("/cadastroFiscal")
    public String cadastroFiscal(Model model){
    	model.addAttribute("fiscais",fiscalService.obterTodos());
    	return "cadastroFiscal";
    }
    
    @RequestMapping(value = "/salvarFiscal", method = RequestMethod.POST )
    @Transactional
	public String salvar(@ModelAttribute @Valid Fiscal fiscal, BindingResult result, Model model, RedirectAttributes redirectAttrs){
    	if(result.hasErrors()) {
    		redirectAttrs.addFlashAttribute("mensagem", "Preencha os campos obrigatórios*");
    	}else {
    		fiscalService.salvar(fiscal);
	    	redirectAttrs.addFlashAttribute("mensagem", "Fiscal Cadastrado com sucesso!");
    	}
    	model.addAttribute("fiscais",fiscalService.obterTodos());
    	return "redirect:cadastroFiscal";	
	}
    
    @RequestMapping(value = "/excluirFiscal", method = RequestMethod.POST )
	public String excluir(Long fiscalId, RedirectAttributes redirectAttrs){
    	fiscalService.excluir(fiscalId);
    	redirectAttrs.addFlashAttribute("mensagem", "Fiscal Excluído com sucesso!");
		return "redirect:cadastroFiscal";
	}

    @RequestMapping("/detalharFiscal")
    public ModelAndView detalharFiscal(Long fiscalId){
    	ModelAndView model = new ModelAndView("detalharFiscal");
    	model.addObject("fiscal", fiscalService.findById(fiscalId));
    	return model;
    }
    
    @RequestMapping(value = "/atualizarFiscal", method = RequestMethod.POST )
	public String atualizar(@ModelAttribute @Valid Fiscal fiscal, BindingResult result, Model model){
    	if(result.hasErrors()) {
    		model.addAttribute("mensagem", "Preencha os campos obrigatórios*");
    	}else {
    		fiscalService.salvar(fiscal);
    		model.addAttribute("mensagem", "Fiscal atualizado com sucesso!");
    	}
    	return "detalharFiscal";	
	}
    
    
}