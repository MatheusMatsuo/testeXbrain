package com.testeXbrain.testeXbrain.services;

import com.testeXbrain.testeXbrain.DTOs.vendas.VendaDTO;
import com.testeXbrain.testeXbrain.DTOs.vendas.VendaSemDataDTO;
import com.testeXbrain.testeXbrain.exceptions.EntidadeNaoEncontrada;
import com.testeXbrain.testeXbrain.model.Venda;
import com.testeXbrain.testeXbrain.model.Vendedor;
import com.testeXbrain.testeXbrain.repositories.VendaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendedorService vendedorServices;


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

    public void novaVenda(Long vendedor_id , VendaSemDataDTO vendaSemDataDTO) {
        Venda venda = new Venda();
        Vendedor vendedor = vendedorServices.getById(vendedor_id);

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

    public List<Venda> findByVendasPeriodo(LocalDate data_inicial, LocalDate data_final){
        return vendaRepository.findByVendasPeriodo(data_inicial, data_final);
    }
}
