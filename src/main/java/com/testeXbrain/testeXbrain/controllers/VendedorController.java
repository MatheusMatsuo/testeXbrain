package com.testeXbrain.testeXbrain.controllers;

import com.testeXbrain.testeXbrain.DTOs.VendedorDTO;
import com.testeXbrain.testeXbrain.DTOs.VendedorGetDTO;
import com.testeXbrain.testeXbrain.model.Vendedor;
import com.testeXbrain.testeXbrain.services.VendedorServices;
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
    private VendedorServices vendedorServices;

    @GetMapping
    public List<VendedorDTO> findAllVendedorDTO(){
        return vendedorServices.findAllVendedorDTO();
    }
    @GetMapping(value = "/{id}")
    public VendedorDTO findByIdVendedorDTO(@PathVariable Long id){
        return vendedorServices.findByIdVendedorDTO(id);
    }

    @PostMapping
    public void salvarVendedor(@Valid @RequestBody VendedorDTO vendedorDTO){
        vendedorServices.salvarVendedor(vendedorDTO);
    }
//    @DeleteMapping
//    public void delete(@PathVariable Long id){
//        vendedorServices.delete(id);
//    }

    @GetMapping(value = "/listaVendedores")
    public List<VendedorGetDTO> retornaVendedores(@RequestParam("dataInicial")
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @RequestParam("dataFinal")
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal){
        return vendedorServices.retornaVendedores(dataInicial,dataFinal);
    }
}
