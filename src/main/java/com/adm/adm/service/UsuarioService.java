package com.adm.adm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adm.adm.model.Usuario;
import com.adm.adm.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	public void cadastrar(@Valid Usuario usuario) {
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        repository.save(usuario);
	}

	public List<Usuario>  listaUsuarios() {
		return repository.findAll();
		
	}

	public void deleteById(Long codigo) {
		repository.deleteById(codigo);
		
	}


}
