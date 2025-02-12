package com.dbcrud.pessoa.dto;

import com.dbcrud.pessoa.entity.Endereco;
import com.dbcrud.pessoa.entity.Pessoa;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PessoaDTOTeste {

    @Test
    void testConstrutorComPessoa() {
        Endereco endereco = new Endereco();
        endereco.setRua("Parque Ibirapuera");
        List<Endereco> enderecos = Collections.singletonList(endereco);

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Bruce Wayne");
        pessoa.setCpf("123.456.789-00");
        pessoa.setIdade(30);
        pessoa.setEndereco(enderecos);

        PessoaDTO pessoaDTO = new PessoaDTO(pessoa);

        assertEquals(pessoa.getId(), pessoaDTO.getId());
        assertEquals(pessoa.getNome(), pessoaDTO.getNome());
        assertEquals(pessoa.getCpf(), pessoaDTO.getCpf());
        assertEquals(pessoa.getIdade(), pessoaDTO.getIdade());
        assertEquals(pessoa.getEndereco(), pessoaDTO.getEndereco());
    }

    @Test
    void testGettersESetters() {
        PessoaDTO pessoaDTO = new PessoaDTO();

        Endereco endereco = new Endereco();
        pessoaDTO.setEndereco(Collections.singletonList(endereco));
        endereco.setRua("Belford Roxo");
        endereco.setCidade("Rio de Janeiro");
        endereco.setCep("12345-678");

        pessoaDTO.setNome("Steve Rogers");
        pessoaDTO.setCpf("987.654.321-00");
        pessoaDTO.setIdade(38);

        assertEquals("Belford Roxo", endereco.getRua());
        assertEquals("Rio de Janeiro", endereco.getCidade());
        assertEquals("12345-678", endereco.getCep());
        assertEquals("Steve Rogers", pessoaDTO.getNome());
        assertEquals("987.654.321-00", pessoaDTO.getCpf());
        assertEquals(38, pessoaDTO.getIdade());
    }

    @Test
    void testConstrutorDeveSerNull() {
        PessoaDTO pessoaDTO = new PessoaDTO();

        assertNull(pessoaDTO.getNome());
        assertNull(pessoaDTO.getCpf());
        assertEquals(0, pessoaDTO.getIdade());
        assertNull(pessoaDTO.getEndereco());
    }

}
