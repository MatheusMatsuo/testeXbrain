package com.testeXbrain.testeXbrain.services;

import com.testeXbrain.testeXbrain.DTOs.vendedores.VendedorDTO;
import com.testeXbrain.testeXbrain.exceptions.EntidadeNaoEncontrada;
import com.testeXbrain.testeXbrain.model.Venda;
import com.testeXbrain.testeXbrain.model.Vendedor;
import com.testeXbrain.testeXbrain.repositories.VendedorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VendedorServiceTest {

    @Mock
    private VendedorRepository vendedorRepository;
    @InjectMocks
    private VendedorService vendedorService;

//    @Test
//    public void cadastrarVendedorSucessoTest(){
//        VendedorDTO vendedorDTO = new VendedorDTO();
//        vendedorDTO.setNome("Matsuo");
//
//        Vendedor vendedor = new Vendedor();
//        vendedor.setNome(vendedorDTO.getNome());
//        vendedor.setId(1L);
//
//        when(vendedorService.salvarVendedor(vendedorDTO)).thenReturn(vendedor);
//
//        vendedor = vendedorService.salvarVendedor(vendedorDTO);
//
//        assertEquals(1L, vendedor.getId());
//        assertEquals("Matsuo", vendedor.getNome());
//    }

    @Test
    public void cadastrarVendedorSucessoTest(){
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setNome("Matsuo");

        Vendedor vendedor = new Vendedor();
        vendedor.setNome(vendedorDTO.getNome());

        Vendedor vendedorCadastrado = new Vendedor();
        vendedorCadastrado.setNome(vendedor.getNome());
        vendedorCadastrado.setId(1L);

        when(vendedorRepository.save(vendedor)).thenReturn(vendedorCadastrado);

        assertThat(vendedorService.salvarVendedor(vendedorDTO))
                .extracting("id", "nome")
                .containsExactly(1L, "Matsuo");
    }

    @Test
    public void cadastrarVendedorNomeExistenteTest(){
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setNome("Matsuo");

        Vendedor vendedor = new Vendedor();
        vendedor.setNome(vendedorDTO.getNome());
        vendedor.setId(1L);

        Vendedor vendedorNome = new Vendedor();
        vendedorNome.setNome("Matsuo");
        vendedorNome.setId(2L);

        when(vendedorRepository.findByNome("Matsuo")).thenReturn(Optional.of(vendedorNome));
        when(vendedorRepository.save(vendedor)).thenThrow(new IllegalArgumentException());


        assertThatThrownBy(() -> vendedorService.salvarVendedor(vendedorDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Esse nome de vendedor já existe");
    }

    @Test
    public void editarVendedorSucessoTest(){
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setNome("Matsuo editado");
        vendedorDTO.setId(1L);

        Vendedor vendedor = new Vendedor();
        vendedor.setNome("Daniel");
        vendedor.setId(1L);

        Vendedor vendedorEditado = new Vendedor();
        vendedorEditado.setNome(vendedorDTO.getNome());
        vendedorEditado.setId(1L);

        when(vendedorRepository.findById(1L)).thenReturn(Optional.of(vendedor));
        when(vendedorRepository.save(vendedor)).thenReturn(vendedorEditado);

        Vendedor vendedorTeste = vendedorService.salvarVendedor(vendedorDTO);

        assertEquals(vendedorDTO.getNome(), vendedorTeste.getNome());
        assertEquals(vendedorDTO.getId(), vendedorTeste.getId());
    }

    @Test
    public void editarVendedorNaoEncontradoTest(){
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setNome("Matsuo editado");
        vendedorDTO.setId(1L);

        when(vendedorRepository.findById(1L)).thenThrow(new EntidadeNaoEncontrada(Vendedor.class, 1L));

        assertThatThrownBy(() -> vendedorService.salvarVendedor(vendedorDTO))
                .isInstanceOf(EntidadeNaoEncontrada.class)
                .hasMessage("Vendedor não localizado para o id = 1");
    }

    @Test
    public void editarVendedorNomeExistenteTest(){
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setNome("Matsuo editado");
        vendedorDTO.setId(1L);

        Vendedor vendedor = new Vendedor();
        vendedor.setNome("Matsuo");
        vendedor.setId(1L);

        Vendedor vendedorNome = new Vendedor();
        vendedorNome.setNome("Matsuo editado");
        vendedorNome.setId(2L);

        when(vendedorRepository.findById(1L)).thenReturn(Optional.of(vendedor));
        when(vendedorRepository.findByNome(vendedorDTO.getNome())).thenReturn(Optional.of(vendedorNome));
        when(vendedorRepository.save(vendedor)).thenThrow(new IllegalArgumentException());


        assertThatThrownBy(() -> vendedorService.salvarVendedor(vendedorDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Esse nome de vendedor já existe");
    }

    @Test
    public void retornaVendedorPorPeriodoSucessoTest(){
        Vendedor vendedor = new Vendedor(1L, "Matsuo", null);

        Venda venda = new Venda(1L, LocalDate.now(),50.0, vendedor);
        vendedor.getVendas().add(venda);


    }
}
