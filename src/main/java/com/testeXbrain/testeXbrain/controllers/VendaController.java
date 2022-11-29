package com.testeXbrain.testeXbrain.controllers;

import com.testeXbrain.testeXbrain.DTOs.VendaDTO;
import com.testeXbrain.testeXbrain.DTOs.VendaSemDataDTO;
import com.testeXbrain.testeXbrain.model.Venda;
import com.testeXbrain.testeXbrain.services.VendaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaServices vendaServices;
    @GetMapping
    public List<VendaDTO> findAllVendaDTO() {
        return vendaServices.findAllVendaDTO();
    }

    @GetMapping(value = "/{id}")
    public VendaDTO findByIdVendaDTO(@PathVariable Long id){
        return vendaServices.findByIdVendaDTO(id);
    }

    @PostMapping(value = "/novaVenda/vendedorId={vendedorId}")
    public void novaVenda(@Valid @PathVariable  Long vendedorId , @RequestBody VendaSemDataDTO vendaSemDataDTO){
        vendaServices.novaVenda(vendedorId, vendaSemDataDTO);
    }

    @GetMapping(value = "/findByDatas")
    public List<Venda> findByVendasPeriodo(@RequestParam("dataInicial")
                                                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial,@RequestParam("dataFinal")
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal){
       return vendaServices.findByVendasPeriodo(dataInicial, dataFinal);
    }
}
