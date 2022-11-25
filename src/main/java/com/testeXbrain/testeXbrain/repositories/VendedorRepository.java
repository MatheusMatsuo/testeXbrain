package com.testeXbrain.testeXbrain.repositories;


import com.testeXbrain.testeXbrain.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    Optional<Vendedor> findByNome(String nome);
}
