package com.testeXbrain.testeXbrain.DTOs.vendas;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.testeXbrain.testeXbrain.DTOs.vendedores.VendedorDTO;
import com.testeXbrain.testeXbrain.DTOs.vendedores.VendedorSemVendasDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class VendaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone="GMT-3")
    private LocalDate data_venda;
    private Double valor;
    private VendedorSemVendasDTO vendedor;
}
