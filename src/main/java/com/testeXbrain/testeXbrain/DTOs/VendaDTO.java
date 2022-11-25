package com.testeXbrain.testeXbrain.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class VendaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_venda;
    @NotBlank(message = "Campo valor é obrigatório")
    private Double valor;

    private VendedorDTO vendedorDTO;
}
