package com.testeXbrain.testeXbrain.services;

import com.testeXbrain.testeXbrain.DTOs.VendedorDTO;
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

    @Autowired
    private VendaServices vendaServices;

    public List<VendedorDTO> findAllVendedorDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return vendedorRepository.findAll().stream()
                .map(a -> modelMapper.map(a, VendedorDTO.class))
                .collect(Collectors.toList());
    }

    public VendedorDTO findByIdVendedorDTO(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(vendedorRepository.findById(id), VendedorDTO.class);
    }

    public void save(VendedorDTO vendedorDTO) {
        Vendedor vendedor;
        boolean existeVendedor;
        boolean existeNomeVendedor = false;
        boolean condicaoParaUpdate = vendedorDTO.getId() != null && vendedorDTO.getId() > 0;

        if (condicaoParaUpdate) {
            vendedor = vendedorRepository.findById(vendedorDTO.getId()).get();
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

        if (existeNomeVendedor == false) {
            vendedor.setId(vendedorDTO.getId());
            vendedor.setNome(vendedorDTO.getNome());
            vendedorRepository.save(vendedor);
        } else {
            throw new IllegalArgumentException("Esse nome de vendedor já existe");
        }
    }
}
