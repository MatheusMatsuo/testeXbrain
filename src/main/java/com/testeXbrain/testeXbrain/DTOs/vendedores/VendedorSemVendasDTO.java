package com.testeXbrain.testeXbrain.DTOs.vendedores;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class VendedorSemVendasDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "Campo nome é obrigatório")
    private String nome;
}
