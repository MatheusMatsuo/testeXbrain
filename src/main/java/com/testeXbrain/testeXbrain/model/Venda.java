package com.testeXbrain.testeXbrain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_vendas")
@Entity
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Venda")
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_venda;
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Vendedor vendedor;
}
