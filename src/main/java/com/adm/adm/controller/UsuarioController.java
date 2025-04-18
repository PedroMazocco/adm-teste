package com.adm.adm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adm.adm.model.Usuario;
import com.adm.adm.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("CadastroUsuario");
		mv.addObject("usuario", usuario);
		return mv;
	}
	
	
	@PostMapping
	public ModelAndView cadastrar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(usuario);
		}
		service.cadastrar(usuario);
	    attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		return new ModelAndView("redirect:/usuario");
	}
	
	@GetMapping
	public ModelAndView listarUsuarios() {
		ModelAndView mv = new ModelAndView("ListaUsuarios");
		 mv.addObject("usuarios", service.listaUsuarios());
		return mv;
		
	}

}
