package com.testeXbrain.testeXbrain.services;

import com.testeXbrain.testeXbrain.DTOs.VendaDTO;
import com.testeXbrain.testeXbrain.DTOs.VendaSemDataDTO;
import com.testeXbrain.testeXbrain.DTOs.VendedorDTO;
import com.testeXbrain.testeXbrain.DTOs.VendedorGetDTO;
import com.testeXbrain.testeXbrain.exceptions.EntidadeNaoEncontrada;
import com.testeXbrain.testeXbrain.model.Venda;
import com.testeXbrain.testeXbrain.model.Vendedor;
import com.testeXbrain.testeXbrain.repositories.VendaRepository;
import com.testeXbrain.testeXbrain.repositories.VendedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaServices {
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendedorServices vendedorServices;


    public List<VendaDTO> findAllVendaDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return vendaRepository.findAll().stream()
                .map(a -> modelMapper.map(a, VendaDTO.class))
                .collect(Collectors.toList());
    }

    public VendaDTO findByIdVendaDTO(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Venda venda = vendaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontrada(Venda.class, id));
        return modelMapper.map(venda, VendaDTO.class);
    }

    public void novaVenda(Long vendedorId , VendaSemDataDTO vendaSemDataDTO) {
        Venda venda = new Venda();
        Vendedor vendedor = vendedorServices.getById(vendedorId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatado = LocalDate.now().format(formatter);
        LocalDate data = LocalDate.parse(dataFormatado, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        venda.setData_venda(data);

        venda.setValor(vendaSemDataDTO.getValor());
        venda.setVendedor(vendedor);
        vendedor.getVendas().add(venda);

        vendaRepository.save(venda);
        vendedorServices.save(vendedor);
    }

    public List<Venda> findByVendasPeriodo(LocalDate dataInicial, LocalDate dataFinal){
        return vendaRepository.findByVendasPeriodo(dataInicial, dataFinal);
    }
}
