package com.testeXbrain.testeXbrain.DTOs.vendas;

import com.testeXbrain.testeXbrain.DTOs.vendedores.VendedorDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class VendaSemDataDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Double valor;

    private VendedorDTO vendedor;
}
