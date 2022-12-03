package com.testeXbrain.testeXbrain.DTOs.vendedores;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testeXbrain.testeXbrain.model.Venda;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
public class VendedorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank(message = "Campo nome é obrigatório")
    private String nome;

    @Nullable
    private Collection<Venda> vendas;
}
