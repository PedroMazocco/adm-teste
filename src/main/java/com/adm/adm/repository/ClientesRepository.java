package com.adm.adm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adm.adm.model.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Long>{



}