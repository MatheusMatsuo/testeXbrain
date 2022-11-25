package com.testeXbrain.testeXbrain.DTOs;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
public class VendedorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank(message = "Campo nome é obrigatório")
    private String nome;

    private Collection<Long> collectionVendas;
}
