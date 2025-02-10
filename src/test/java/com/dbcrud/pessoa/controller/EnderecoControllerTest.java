package com.dbcrud.pessoa.controller;

import com.dbcrud.pessoa.dto.EnderecoDTO;
import com.dbcrud.pessoa.entity.Endereco;
import com.dbcrud.pessoa.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class EnderecoControllerTest {

    @Mock
    private EnderecoService enderecoService;

    @InjectMocks
    private EnderecoController enderecoController;

    private Endereco endereco;
    private EnderecoDTO enderecoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        endereco = new Endereco();
        endereco.setId(1L);
        endereco.setRua("Rua Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setCep("12345-678");

        enderecoDTO = new EnderecoDTO();
        enderecoDTO.setRua("Rua Teste");
        enderecoDTO.setCidade("Cidade Teste");
        enderecoDTO.setCep("12345-678");
    }

    @Test
    void criarEndereco_deveRetornarEnderecoCriadoComStatusCreated() {
        when(enderecoService.criarEndereco(any(Endereco.class))).thenReturn(endereco);

        ResponseEntity<EnderecoDTO> response = enderecoController.criarEndereco(enderecoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(endereco.getRua(), response.getBody().getRua());
        verify(enderecoService, times(1)).criarEndereco(any(Endereco.class));
    }

    @Test
    void listarEnderecos_deveRetornarListaVaziaQuandoNaoHouverEnderecos() {
        when(enderecoService.listarEnderecos()).thenReturn(Collections.emptyList());

        ResponseEntity<List<EnderecoDTO>> response = enderecoController.listarEnderecos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
        verify(enderecoService, times(1)).listarEnderecos();
    }

    @Test
    void listarEnderecos_deveRetornarListaDeEnderecos() {
        when(enderecoService.listarEnderecos()).thenReturn(List.of(endereco));

        ResponseEntity<List<EnderecoDTO>> response = enderecoController.listarEnderecos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(enderecoService, times(1)).listarEnderecos();
    }

    @Test
    void buscarEnderecoPorId_deveRetornarEnderecoQuandoExistir() {
        when(enderecoService.buscarEnderecoPorId(anyLong())).thenReturn(endereco);

        ResponseEntity<EnderecoDTO> response = enderecoController.buscarEnderecoPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(endereco.getId(), response.getBody().getId());
        verify(enderecoService, times(1)).buscarEnderecoPorId(1L);
    }

    @Test
    void buscarEnderecoPorId_deveRetornarNotFoundQuandoNaoExistir() {
        when(enderecoService.buscarEnderecoPorId(anyLong())).thenThrow(new RuntimeException());

        ResponseEntity<EnderecoDTO> response = enderecoController.buscarEnderecoPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(enderecoService, times(1)).buscarEnderecoPorId(1L);
    }

    @Test
    void atualizarEndereco_deveRetornarEnderecoAtualizado() {
        when(enderecoService.atualizarEndereco(anyLong(), any(Endereco.class))).thenReturn(endereco);

        ResponseEntity<EnderecoDTO> response = enderecoController.atualizarEndereco(1L, enderecoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(endereco.getCep(), response.getBody().getCep());
        verify(enderecoService, times(1)).atualizarEndereco(anyLong(), any(Endereco.class));
    }

    @Test
    void atualizarEndereco_deveRetornarNotFoundQuandoIdInvalido() {
        when(enderecoService.atualizarEndereco(anyLong(), any(Endereco.class)))
                .thenThrow(new RuntimeException());

        ResponseEntity<EnderecoDTO> response = enderecoController.atualizarEndereco(99L, enderecoDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(enderecoService, times(1)).atualizarEndereco(anyLong(), any(Endereco.class));
    }

    @Test
    void deletarEndereco_deveRetornarNoContentQuandoDeletado() {
        doNothing().when(enderecoService).deletarEndereco(anyLong());

        ResponseEntity<Void> response = enderecoController.deletarEndereco(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(enderecoService, times(1)).deletarEndereco(1L);
    }

    @Test
    void deletarEndereco_deveRetornarNotFoundQuandoIdInvalido() {
        doThrow(new RuntimeException()).when(enderecoService).deletarEndereco(anyLong());

        ResponseEntity<Void> response = enderecoController.deletarEndereco(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(enderecoService, times(1)).deletarEndereco(99L);
    }
}