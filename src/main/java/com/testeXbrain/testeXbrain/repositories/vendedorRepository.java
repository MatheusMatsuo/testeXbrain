package com.testeXbrain.testeXbrain.repositories;


import com.testeXbrain.testeXbrain.model.Vendedores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface vendedorRepository extends JpaRepository<Vendedores, Long> {
}
