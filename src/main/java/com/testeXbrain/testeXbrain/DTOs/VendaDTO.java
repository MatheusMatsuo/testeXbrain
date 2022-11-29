package com.testeXbrain.testeXbrain.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class VendaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone="GMT-3")
    private LocalDate data_venda;
    @NotNull(message = "Campo valor é obrigatório")
    private Double valor;
    private VendedorDTO vendedor;
}
