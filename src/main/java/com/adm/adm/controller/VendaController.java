package com.adm.adm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adm.adm.model.Venda;
import com.adm.adm.service.VendaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/venda")
public class VendaController {
	
	@Autowired
	private VendaService service;
	
	@GetMapping("/novo")
	public ModelAndView novo(Venda venda) {
		
		ModelAndView mv = new ModelAndView("CadastroVenda");
		mv.addObject("venda", venda);
	    mv.addObject("clientes", service.listarClientes());
	    mv.addObject("produtos", service.listarProdutos());
		return mv;
		
	}
	
	@PostMapping("/nova")
	public ModelAndView salvar(@Valid Venda venda, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(venda);
		}
		service.salvar(venda);
		attributes.addFlashAttribute("mensagem", "Venda salva!");
		return new ModelAndView("redirect:/venda");
		}
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaVendas");
		mv.addObject("vendas",service.listarVendas());
		return mv;
	}
	
	@PostMapping("/excluir/{codigo}")
	public String excluir(@PathVariable Long codigo) {
		service.deleteById(codigo);
		return "redirect:/venda";
		
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Venda venda = service.buscarPorId(codigo);
		ModelAndView mv = new ModelAndView("CadastroVenda");
		mv.addObject("venda", venda);
	    mv.addObject("clientes", service.listarClientes());
	    mv.addObject("produtos", service.listarProdutos());
		return mv;
	}
}
