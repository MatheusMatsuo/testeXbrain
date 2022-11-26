package com.testeXbrain.testeXbrain.services;

import com.testeXbrain.testeXbrain.DTOs.VendedorDTO;
import com.testeXbrain.testeXbrain.exceptions.EntidadeNaoEncontrada;
import com.testeXbrain.testeXbrain.model.Vendedor;
import com.testeXbrain.testeXbrain.repositories.VendedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendedorServices {
    @Autowired
    private VendedorRepository vendedorRepository;


    public List<VendedorDTO> findAllVendedorDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return vendedorRepository.findAll().stream()
                .map(a -> modelMapper.map(a, VendedorDTO.class))
                .collect(Collectors.toList());
    }

    public VendedorDTO findByIdVendedorDTO(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(vendedorRepository.getById(id), VendedorDTO.class);
    }

    public Vendedor getById(Long id){
        return vendedorRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontrada(Vendedor.class, id));
    }

    public void salvarVendedor(VendedorDTO vendedorDTO) {
        Vendedor vendedor;
        boolean existeVendedor;
        boolean existeNomeVendedor = false;
        boolean condicaoParaUpdate = vendedorDTO.getId() != null && vendedorDTO.getId() > 0;

        if (condicaoParaUpdate) {
            vendedor = vendedorRepository.getById(vendedorDTO.getId());
            existeVendedor = true;
            Optional<Vendedor> nome = vendedorRepository.findByNome(vendedorDTO.getNome());
            if (nome.isPresent() && nome.get().getId() != vendedorDTO.getId()) {
                existeNomeVendedor = false;
            } else {
                existeNomeVendedor = true;
            }
        } else {
            vendedor = new Vendedor();
            existeVendedor = false;
            Optional<Vendedor> nome = vendedorRepository.findByNome(vendedorDTO.getNome());
            if (nome.isPresent()) {
                existeNomeVendedor = true;
            }
        }

        if ((existeVendedor == true && existeNomeVendedor == true) || (existeVendedor == false && existeNomeVendedor == false)) {
            vendedor.setId(vendedorDTO.getId());
            vendedor.setNome(vendedorDTO.getNome());
            vendedorRepository.save(vendedor);
        } else {
            throw new IllegalArgumentException("Esse nome de vendedor já existe");
        }
    }

    public void save(Vendedor vendedor){
        vendedorRepository.save(vendedor);
    }

//    public void delete(Long id){
//        vendedorRepository.deleteById(id);
//    }
}
