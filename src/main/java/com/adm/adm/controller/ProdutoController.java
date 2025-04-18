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

import com.adm.adm.model.Produto;
import com.adm.adm.service.ProdutoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping("/novo")
	private ModelAndView novo(Produto produto) {
		ModelAndView mv = new ModelAndView("CadastroProduto");
		mv.addObject("produto", produto);
		return mv;
	}
	
	@PostMapping("/nova")
	private ModelAndView cadastrar(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(produto);
		}
		service.salvar(produto);
		return new ModelAndView("redirect:/produto");
	}
	
	@GetMapping
	private ModelAndView listarProduto() {
		ModelAndView mv = new ModelAndView("ListaProduto");
		mv.addObject("produto" , service.listarTodos());
		return mv;
	}
	
	@PostMapping("/{codigo}/excluir")
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		service.deleteById(codigo);
		return "redirect:/produto";
		
	}
	
	 @PostMapping("/{codigo}")
	    public ModelAndView atualizar(@PathVariable Long codigo, @Valid Produto produto, BindingResult result, RedirectAttributes attributes) {
	        if (result.hasErrors()) {
	            ModelAndView mv = new ModelAndView("CadastroProduto");
	            mv.addObject("produto", produto);
	            return mv;
	        }

	        produto.setCodigo(codigo);
	        service.salvar(produto);
	        attributes.addFlashAttribute("mensagem", "Produto atualizado com sucesso.");
	        return new ModelAndView("redirect:/produto");
	    }
	
	 @GetMapping("/{codigo}")
	    public ModelAndView editar(@PathVariable Long codigo) {
	        Produto produto = service.buscarPorId(codigo);
	        ModelAndView mv = new ModelAndView("CadastroProduto");
	        mv.addObject("produto", produto);
	        return mv;
	    }
}
