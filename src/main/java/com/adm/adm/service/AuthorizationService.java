package com.adm.adm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adm.adm.model.Usuario;
import com.adm.adm.repository.UsuarioRepository;

@Service
public class AuthorizationService implements UserDetailsService{
	
	@Autowired
	UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        System.out.println("Senha no banco: " + usuario.getSenha());
        return usuario;
    }
	
	

}
