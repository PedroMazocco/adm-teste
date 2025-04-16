package com.adm.adm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adm.adm.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


	Usuario findByEmail(String email);
	

}
