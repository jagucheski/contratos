package br.com.jagucheski.contratos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
    @RequestMapping("/gerenciadorContratos")
    public String index(){
        return "index";
    }
           
}