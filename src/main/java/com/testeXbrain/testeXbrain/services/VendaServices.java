package com.testeXbrain.testeXbrain.services;

import com.testeXbrain.testeXbrain.DTOs.VendaDTO;
import com.testeXbrain.testeXbrain.DTOs.VendedorDTO;
import com.testeXbrain.testeXbrain.model.Venda;
import com.testeXbrain.testeXbrain.model.Vendedor;
import com.testeXbrain.testeXbrain.repositories.VendaRepository;
import com.testeXbrain.testeXbrain.repositories.VendedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaServices {
    @Autowired
    private VendaRepository vendaRepository;

    public List<VendaDTO> findAllVendaDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return vendaRepository.findAll().stream()
                .map(a -> modelMapper.map(a, VendaDTO.class))
                .collect(Collectors.toList());
    }

    public VendaDTO findByIdVendaDTO(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(vendaRepository.findById(id), VendaDTO.class);
    }

    public void save(VendaDTO vendaDTO) {
        Venda venda;

        if (vendaDTO.getId() != null && vendaDTO.getId() > 0) {
            venda = vendaRepository.findById(vendaDTO.getId()).get();
        } else {
            venda = new Venda();
        }
            venda.setId(vendaDTO.getId());
            venda.setData_venda(vendaDTO.getData_venda());
            venda.setValor(vendaDTO.getValor());
            venda.setVendedor(vendaDTO);
            vendaRepository.save(venda);

    }

}
