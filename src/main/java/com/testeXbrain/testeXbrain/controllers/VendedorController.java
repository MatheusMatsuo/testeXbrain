package com.testeXbrain.testeXbrain.controllers;

import com.testeXbrain.testeXbrain.DTOs.vendedores.VendedorDTO;
import com.testeXbrain.testeXbrain.DTOs.vendedores.VendedorGetDTO;
import com.testeXbrain.testeXbrain.model.Vendedor;
import com.testeXbrain.testeXbrain.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorServices;

    @GetMapping
    public List<VendedorDTO> findAllVendedorDTO(){
        return vendedorServices.findAllVendedorDTO();
    }
    @GetMapping(value = "/{id}")
    public VendedorDTO findByIdVendedorDTO(@PathVariable Long id){
        return vendedorServices.findByIdVendedorDTO(id);
    }

    @PostMapping
    public Vendedor salvarVendedor(@Valid @RequestBody VendedorDTO vendedorDTO){
        return vendedorServices.salvarVendedor(vendedorDTO);
    }
//    @DeleteMapping
//    public void delete(@PathVariable Long id){
//        vendedorServices.delete(id);
//    }

    @GetMapping(value = "/listaVendedores")
    public List<VendedorGetDTO> retornaVendedoresPorPeriodo(@RequestParam("dataInicial")
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data_inicial, @RequestParam("dataFinal")
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data_final){
        return vendedorServices.retornaVendedoresPorPeriodo(data_inicial,data_final);
    }

    @GetMapping(value = "/listaVendedor/{vendedor_id}")
    public VendedorGetDTO retornaVendedorPorPeriodo(@PathVariable Long vendedor_id, @RequestParam("dataInicial")
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data_inicial, @RequestParam("dataFinal")
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data_final){
        return vendedorServices.retornaVendedorPorPeriodo(vendedor_id, data_inicial,data_final);
    }
}
