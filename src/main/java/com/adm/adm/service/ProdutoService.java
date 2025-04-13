package com.adm.adm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adm.adm.model.Produto;
import com.adm.adm.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public List<Produto> listarTodos() {
		return repository.findAll();
	}

	@Transactional
	public void salvar(Produto produto) {
		repository.save(produto);
	}

	public void deleteById(Long codigo) {
		repository.deleteById(codigo);
	}

	public Produto buscarPorId(Long codigo) {
		return repository.findById(codigo)
		.orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + codigo));
	}

}
