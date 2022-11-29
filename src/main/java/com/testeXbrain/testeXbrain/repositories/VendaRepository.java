package com.testeXbrain.testeXbrain.repositories;

import com.testeXbrain.testeXbrain.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("select v from Venda v where v.data_venda between ?1 and ?2")
    public List<Venda> findByVendasPeriodo(LocalDate dataInicial, LocalDate dataFinal);
}
