package com.testeXbrain.testeXbrain.controllers;

import com.testeXbrain.testeXbrain.DTOs.VendedorDTO;
import com.testeXbrain.testeXbrain.model.Vendedor;
import com.testeXbrain.testeXbrain.services.VendedorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
}
