package com.testeXbrain.testeXbrain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_vendas")
@Entity
public class Vendas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Vendas")
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_venda;
    private Double valor;
}
