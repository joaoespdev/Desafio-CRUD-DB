package com.dbcrud.pessoa.controller;

import com.dbcrud.pessoa.dto.PessoaDTO;
import com.dbcrud.pessoa.entity.Pessoa;
import com.dbcrud.pessoa.service.PessoaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PessoaControllerTest {

    @Mock
    private PessoaService pessoaService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PessoaController pessoaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salvar_deveRetornarPessoaDTOComStatusCreated() {

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João");

        PessoaDTO pessoaDTO = new PessoaDTO(pessoa);

        when(pessoaService.salvar(any(Pessoa.class))).thenReturn(pessoa);

        ResponseEntity<PessoaDTO> response = pessoaController.salvar(pessoa);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(pessoaDTO.getId(), response.getBody().getId());
        assertEquals(pessoaDTO.getNome(), response.getBody().getNome());
        verify(pessoaService, times(1)).salvar(any(Pessoa.class));
    }

    @Test
    void listaPessoa_deveRetornarListaDePessoaDTOComStatusOk() {
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setId(1L);
        pessoa1.setNome("João");

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setId(2L);
        pessoa2.setNome("Maria");

        List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2);

        when(pessoaService.listaPessoa()).thenReturn(pessoas);

        ResponseEntity<List<PessoaDTO>> response = pessoaController.listaPessoa();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(pessoaService, times(1)).listaPessoa();
    }

    @Test
    void buscarPessoaPorId_deveRetornarPessoaDTOComStatusOk() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João");

        when(pessoaService.buscarPorId(1L)).thenReturn(Optional.of(pessoa));

        ResponseEntity<PessoaDTO> response = pessoaController.buscarPessoaPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pessoa.getId(), response.getBody().getId());
        assertEquals(pessoa.getNome(), response.getBody().getNome());
        verify(pessoaService, times(1)).buscarPorId(1L);
    }

    @Test
    void buscarPessoaPorId_deveRetornarStatusNotFoundQuandoPessoaNaoExistir() {
        when(pessoaService.buscarPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<PessoaDTO> response = pessoaController.buscarPessoaPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(pessoaService, times(1)).buscarPorId(1L);
    }

    @Test
    void removerPessoa_deveRetornarStatusNoContentQuandoPessoaExistir() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João");

        when(pessoaService.buscarPorId(1L)).thenReturn(Optional.of(pessoa));

        ResponseEntity<Void> response = pessoaController.removerPessoa(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(pessoaService, times(1)).buscarPorId(1L);
        verify(pessoaService, times(1)).removerPorId(1L);
    }

    @Test
    void removerPessoa_deveRetornarStatusNotFoundQuandoPessoaNaoExistir() {
        when(pessoaService.buscarPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = pessoaController.removerPessoa(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(pessoaService, times(1)).buscarPorId(1L);
        verify(pessoaService, never()).removerPorId(1L);
    }

    @Test
    void atualizarPessoa_deveRetornarStatusNoContentQuandoPessoaExistir() {
        Pessoa pessoaExistente = new Pessoa();
        pessoaExistente.setId(1L);
        pessoaExistente.setNome("João");

        Pessoa pessoaAtualizada = new Pessoa();
        pessoaAtualizada.setNome("João Silva");

        when(pessoaService.buscarPorId(1L)).thenReturn(Optional.of(pessoaExistente));
        when(modelMapper.map(any(Pessoa.class), any())).thenReturn(pessoaExistente);

        ResponseEntity<Void> response = pessoaController.atualizarPessoa(1L, pessoaAtualizada);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(pessoaService, times(1)).buscarPorId(1L);
        verify(pessoaService, times(1)).salvar(pessoaExistente);
    }

    @Test
    void atualizarPessoa_deveRetornarStatusNotFoundQuandoPessoaNaoExistir() {
        Pessoa pessoaAtualizada = new Pessoa();
        pessoaAtualizada.setNome("João Silva");

        when(pessoaService.buscarPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = pessoaController.atualizarPessoa(1L, pessoaAtualizada);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(pessoaService, times(1)).buscarPorId(1L);
        verify(pessoaService, never()).salvar(any(Pessoa.class));
    }

}
