package com.adm.adm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adm.adm.model.Usuario;
import com.adm.adm.service.UsuarioService;

@Controller
public class LoginController {
	
	@Autowired
	UsuarioService service;
	
	@GetMapping("/login")
	public ModelAndView login(@AuthenticationPrincipal Usuario usuario) {
		ModelAndView mv = new ModelAndView("Login");

		if (usuario != null && usuario.getEmail() != null) {
			return new ModelAndView("redirect:/dashboard");
		}
        return mv;
		
		
	}
	

	


}
