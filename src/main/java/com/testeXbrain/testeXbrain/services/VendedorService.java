package com.testeXbrain.testeXbrain.services;

import com.testeXbrain.testeXbrain.DTOs.vendedores.VendedorDTO;
import com.testeXbrain.testeXbrain.DTOs.vendedores.VendedorGetDTO;
import com.testeXbrain.testeXbrain.exceptions.EntidadeNaoEncontrada;
import com.testeXbrain.testeXbrain.model.Vendedor;
import com.testeXbrain.testeXbrain.repositories.VendedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private VendaService vendaServices;

    public List<VendedorDTO> findAllVendedorDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return vendedorRepository.findAll().stream()
                .map(a -> modelMapper.map(a, VendedorDTO.class))
                .collect(Collectors.toList());
    }

    public VendedorDTO findByIdVendedorDTO(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(getById(id), VendedorDTO.class);
    }

    public Vendedor getById(Long id){
        return vendedorRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontrada(Vendedor.class, id));
    }


    public Vendedor salvarVendedor(VendedorDTO vendedorDTO) {
        Vendedor vendedor;
        boolean existeVendedor;
        boolean existeNomeVendedor = false;

        // Verifica id: Caso venha id é update, caso não é cadastro
        if (vendedorDTO.getId() != null && vendedorDTO.getId() > 0) {
            vendedor = getById(vendedorDTO.getId());
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

        // Verifica se existe nome de vendedor já cadastrado
        if ((existeVendedor == true && existeNomeVendedor == true) || (existeVendedor == false && existeNomeVendedor == false)) {
            vendedor.setId(vendedorDTO.getId());
            vendedor.setNome(vendedorDTO.getNome());
            return vendedorRepository.save(vendedor);
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

    private Long totalVendasPorVendedor(Vendedor vendedor, LocalDate data_inicial, LocalDate data_final){
        return vendaServices.findByVendasPeriodo(data_inicial,data_final).stream()
                .filter(a -> a.getVendedor().getId().equals(vendedor.getId()))
                .count();

    }

    private double mediaVendasPorVendedor(Vendedor vendedor, LocalDate data_inicial, LocalDate data_final){
        Long dias = ChronoUnit.DAYS.between(data_inicial, data_final) + 1;
        Double totalVendas = Double.valueOf(totalVendasPorVendedor(vendedor, data_inicial, data_final));
        Double media = totalVendas / dias;
        return media;
    }

    private VendedorGetDTO instanciaVendedorGetDTO(Vendedor vendedor, LocalDate data_inicial, LocalDate data_final){
        VendedorGetDTO dto = new VendedorGetDTO();
        Long totalVendas = totalVendasPorVendedor(vendedor, data_inicial, data_final);
        Double media = mediaVendasPorVendedor(vendedor, data_inicial, data_final);
        dto.setTotal_vendas(totalVendas);
        dto.setMedia_vendas_diaria(media);
        dto.setId(vendedor.getId());
        dto.setNome(vendedor.getNome());
        return dto;
    }


    public List<VendedorGetDTO> retornaVendedoresPorPeriodo(LocalDate data_inicial, LocalDate data_final){
        if (data_final.isBefore(data_inicial)) {
            throw new IllegalArgumentException("A data inicial deve ser menor ou igual a data final");
        }else {
            List<VendedorGetDTO> result = vendedorRepository.findAll().stream()
                    .map(vendedor -> instanciaVendedorGetDTO(vendedor, data_inicial, data_final))
                    .collect(Collectors.toList());

            return result;
        }
    }

    public VendedorGetDTO retornaVendedorPorPeriodo(Long vendedor_id, LocalDate data_inicial, LocalDate data_final){
        if (data_final.isBefore(data_inicial)) {
            throw new IllegalArgumentException("A data inicial deve ser menor ou igual a data final");
        }else {
            ModelMapper modelMapper = new ModelMapper();
            VendedorGetDTO result = modelMapper.map( instanciaVendedorGetDTO(getById(vendedor_id), data_inicial, data_final), VendedorGetDTO.class);

            return result;
        }
    }
}
