package com.testeXbrain.testeXbrain.services;

import com.testeXbrain.testeXbrain.DTOs.VendedorDTO;
import com.testeXbrain.testeXbrain.DTOs.VendedorGetDTO;
import com.testeXbrain.testeXbrain.exceptions.EntidadeNaoEncontrada;
import com.testeXbrain.testeXbrain.model.Vendedor;
import com.testeXbrain.testeXbrain.repositories.VendaRepository;
import com.testeXbrain.testeXbrain.repositories.VendedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
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

//    public List<VendedorDTO> listaVendedor(Instant dataInicial, Instant dataFinal){
//        vendedorRepository.findAll().stream()
//    }
//
//    public List<VendedorGetDTO>

    public Long totalVendasPorVendedor(Long id, LocalDate dataInicial, LocalDate dataFinal){
        return vendaServices.findByVendasPeriodo(dataInicial,dataFinal).stream()
                .filter(a -> a.getVendedor().getId().equals(id))
                .count();

    }

    public double mediaVendasPorVendedor(Long id, LocalDate dataInicial, LocalDate dataFinal){
        Long dias = ChronoUnit.DAYS.between(dataInicial, dataFinal) + 1;
        Double totalVendas = Double.valueOf(totalVendasPorVendedor(id, dataInicial, dataFinal));
        Double media = totalVendas / dias;
        return media;
    }

    public VendedorGetDTO instanciaVendedorGetDTO(Long id, LocalDate dataInicial, LocalDate dataFinal){
        VendedorGetDTO dto = new VendedorGetDTO();
        Vendedor vendedor = getById(id);
        Long totalVendas = totalVendasPorVendedor(id, dataInicial, dataFinal);
        Double media = mediaVendasPorVendedor(id, dataInicial, dataFinal);
        dto.setTotalVendas(totalVendas);
        dto.setMediaVendasDiaria(media);
        dto.setId(id);
        dto.setNome(vendedor.getNome());
        return dto;
    }


    public List<VendedorGetDTO> retornaVendedores(LocalDate dataInicial, LocalDate dataFinal){
        List<VendedorGetDTO> result = vendedorRepository.findAll().stream()
                .map(vendedor -> instanciaVendedorGetDTO(vendedor.getId(), dataInicial, dataFinal))
                .collect(Collectors.toList());

        return result;
    }
}
