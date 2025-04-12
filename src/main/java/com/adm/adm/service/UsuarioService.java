package com.adm.adm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adm.adm.model.Usuario;
import com.adm.adm.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;

	public Boolean logar(@Valid Usuario login) {
        return repository.existsByEmailAndSenha(login.getEmail(), login.getSenha());
		
	}

}
