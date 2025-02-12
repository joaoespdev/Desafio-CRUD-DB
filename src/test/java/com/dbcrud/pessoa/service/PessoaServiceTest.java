package com.dbcrud.pessoa.service;

import com.dbcrud.pessoa.controller.PessoaController;
import com.dbcrud.pessoa.entity.Pessoa;
import com.dbcrud.pessoa.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private PessoaController pessoaController;

    @InjectMocks
    private PessoaService pessoaService;

    @Test
    void retornarNomeDaPessoaQuandoSalvarEAtualizarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Bruce Wayne");
        pessoa.setIdade(22);
        pessoa.setCpf("123.456.789-88");

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        Pessoa resultado = pessoaService.salvar(pessoa);

        assertNotNull(resultado);
        assertNotNull(pessoa);
        assertEquals("Bruce Wayne", pessoa.getNome());
        verify(pessoaRepository, times(1)).save(pessoa);
    }

    @Test
    void listarTodasAsPessoas() {
        List<Pessoa> listaDePessoas = new ArrayList<>();

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setId(1L);
        pessoa1.setNome("Bruce Wayne");
        pessoa1.setIdade(22);
        pessoa1.setCpf("123.456.789-88");

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setId(2L);
        pessoa2.setNome("Clark Kent");
        pessoa2.setIdade(20);
        pessoa2.setCpf("987.654.321-99");

        listaDePessoas.add(pessoa1);
        listaDePessoas.add(pessoa2);

        when(pessoaRepository.findAll()).thenReturn(listaDePessoas);

        List<Pessoa> resultado = pessoaService.listaPessoa();

        assertEquals(2, resultado.size());
        verify(pessoaRepository, times(1)).findAll();
    }

    @Test
    void buscarPessoaPorId() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Bruce Wayne");
        pessoa.setIdade(22);
        pessoa.setCpf("123.456.789-88");

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        Optional<Pessoa> resultado = pessoaService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(pessoa, resultado.get());
        //COLOCAR NOTNULL DEPOIS
    }

    @Test
    void removerPessoaPorId_DeveRetornarNoContentQuandoIdExiste() {
        Long idValido = 1L;

        doNothing().when(pessoaService).removerPorId(idValido);

        ResponseEntity<Void> response = pessoaController.removerPessoa(idValido);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(pessoaService, times(1)).removerPorId(idValido);
    }

    //POSSO MELHORAR OS TESTS DA MESMA FORMA QUE FIZ AGORA, EM VEZ DE CRIAR UM OBJETO DE PESSOA USAR SÓ UM ID
    //DEVEM TER OUTRAS FUNÇÕES QUE APENAS COM UM ID E MENOS LINHAS EU CONSIGA FAZER O MESMO

    @Test
    void removerPessoaPorId_DeveRetornarNotFoundQuandoIdNaoExiste() {
        Long idInvalido = 99L;

        doThrow(new EmptyResultDataAccessException(1)).when(pessoaService).removerPorId(idInvalido);

        ResponseEntity<Void> response = pessoaController.removerPessoa(idInvalido);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(pessoaService, times(1)).removerPorId(idInvalido);
    }


}
