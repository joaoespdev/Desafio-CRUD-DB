package com.dbcrud.pessoa.dto;

import com.dbcrud.pessoa.entity.Endereco;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnderecoDTOTest {

    @Test
    void testConstrutorComEndereco() {
        // Cria entidade Endereco para teste
        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setRua("Avenida Paulista");
        endereco.setCidade("São Paulo");
        endereco.setCep("01310-100");

        // Converte para DTO
        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);

        // Verifica cópia dos valores
        assertEquals(endereco.getId(), enderecoDTO.getId());
        assertEquals(endereco.getRua(), enderecoDTO.getRua());
        assertEquals(endereco.getCidade(), enderecoDTO.getCidade());
        assertEquals(endereco.getCep(), enderecoDTO.getCep());
    }

    @Test
    void testToEntity() {
        // Cria DTO
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setRua("Rua do Porto");
        enderecoDTO.setCidade("Santos");
        enderecoDTO.setCep("11010-200");

        // Converte para entidade
        Endereco endereco = enderecoDTO.toEntity();

        // Verifica conversão
        assertEquals(enderecoDTO.getRua(), endereco.getRua());
        assertEquals(enderecoDTO.getCidade(), endereco.getCidade());
        assertEquals(enderecoDTO.getCep(), endereco.getCep());
    }

    @Test
    void testGettersESetters() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        // Testa setters
        enderecoDTO.setRua("Praça da Sé");
        enderecoDTO.setCidade("São Paulo");
        enderecoDTO.setCep("01001-000");

        // Verifica getters
        assertEquals("Praça da Sé", enderecoDTO.getRua());
        assertEquals("São Paulo", enderecoDTO.getCidade());
        assertEquals("01001-000", enderecoDTO.getCep());
    }

    @Test
    void testConstrutorVazio() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        assertNull(enderecoDTO.getId());
        assertNull(enderecoDTO.getRua());
        assertNull(enderecoDTO.getCidade());
        assertNull(enderecoDTO.getCep());
    }
}
