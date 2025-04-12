package com.adm.adm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adm.adm.model.Usuario;
import com.adm.adm.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/login")
	public ModelAndView login(Usuario usuario) {
		ModelAndView mv = new ModelAndView("Login");
		mv.addObject("usuario", usuario);
		return mv;
	}
	
	@PostMapping("/login")
	public String logar(@Valid Usuario usuario, BindingResult  result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return "Login";
		}
		if (!service.logar(usuario)) {
			attributes.addFlashAttribute("mensagem", "Usuário ou senha inválidos.");
			return "redirect:/login";
		}

		attributes.addFlashAttribute("mensagem", "Login realizado com sucesso.");
		return "redirect:/cliente";
	}

}
