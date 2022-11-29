package com.testeXbrain.testeXbrain.DTOs;

import com.testeXbrain.testeXbrain.model.Venda;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;

@Setter
@Getter
public class VendedorGetDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;
    private Long totalVendas;
    private Double mediaVendasDiaria;
}
