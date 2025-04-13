package com.adm.adm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adm.adm.model.Cliente;
import com.adm.adm.repository.ClientesRepository;

import jakarta.transaction.Transactional;

@Service
public class ClientesService {
	
	@Autowired
	private ClientesRepository repository;
	
	@Transactional
	public void salvar(Cliente cliente) {
		repository.save(cliente);
	}

	public List<Cliente> listarTodos() {
		return repository.findAll();
	}

	public void deleteById(Long codigo) {
		repository.deleteById(codigo);
		
	}

	public Cliente buscarPorId(Long codigo) {
	    return repository.findById(codigo)
	        .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + codigo));
	}



}
