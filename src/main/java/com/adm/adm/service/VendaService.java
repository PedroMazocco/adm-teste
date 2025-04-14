package com.adm.adm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adm.adm.model.Cliente;
import com.adm.adm.model.Produto;
import com.adm.adm.model.Venda;
import com.adm.adm.repository.ClientesRepository;
import com.adm.adm.repository.ProdutoRepository;
import com.adm.adm.repository.VendaRepository;

import jakarta.validation.Valid;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository repository;
	
	@Autowired
	private ClientesRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	

	public void salvar(@Valid Venda venda) {
		repository.save(venda);
	}

	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}

	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}

	public List<Venda> listarVendas() {
		return repository.findAll();
	}

}
