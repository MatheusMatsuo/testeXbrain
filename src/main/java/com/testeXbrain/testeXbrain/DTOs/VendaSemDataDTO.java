package com.testeXbrain.testeXbrain.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Getter
@Setter
public class VendaSemDataDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "Campo valor é obrigatório")
    private Double valor;

    private VendedorDTO vendedor;
}
