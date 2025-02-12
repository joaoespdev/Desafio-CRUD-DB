package com.dbcrud.pessoa.service;

import com.dbcrud.pessoa.entity.Endereco;
import com.dbcrud.pessoa.repository.EnderecoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @Test
    void deveSalvarERetornarEndereco() {
        Endereco endereco = new Endereco();
        endereco.setRua("Rua Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setCep("12345-678");

        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);

        Endereco resultado = enderecoService.criarEndereco(endereco);

        assertNotNull(resultado);
        assertEquals("Rua Teste", resultado.getRua());
        verify(enderecoRepository, times(1)).save(endereco);
    }

    @Test
    void deveRetornarListaDeEnderecos() {
        Endereco endereco1 = new Endereco();
        Endereco endereco2 = new Endereco();
        List<Endereco> enderecos = Arrays.asList(endereco1, endereco2);

        when(enderecoRepository.findAll()).thenReturn(enderecos);

        List<Endereco> resultado = enderecoService.listarEnderecos();

        assertEquals(2, resultado.size());
        verify(enderecoRepository, times(1)).findAll();
    }

    @Test
    void buscarEnderecoPorId_quandoIdExisteDeveRetornarEndereco() {
        Long id = 1L;
        Endereco endereco = new Endereco();
        endereco.setId(id);

        when(enderecoRepository.findById(id)).thenReturn(Optional.of(endereco));

        Endereco resultado = enderecoService.buscarEnderecoPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
    }

    @Test
    void buscarEnderecoPorId_quandoIdNaoExisteDeveLancarExcecao() {
        Long id = 99L;

        when(enderecoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> enderecoService.buscarEnderecoPorId(id));
    }

    @Test
    void atualizarEnderecoQuandoIdExiste_DeveAtualizarERetornarEndereco() {
        Long id = 1L;
        Endereco enderecoExistente = new Endereco();
        enderecoExistente.setId(id);
        enderecoExistente.setRua("Rua Antiga");
        enderecoExistente.setCidade("Cidade Antiga");
        enderecoExistente.setCep("00000-000");

        Endereco novosDados = new Endereco();
        novosDados.setRua("Rua Nova");
        novosDados.setCidade("Cidade Nova");
        novosDados.setCep("11111-111");

        when(enderecoRepository.findById(id)).thenReturn(Optional.of(enderecoExistente));
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoExistente);

        Endereco resultado = enderecoService.atualizarEndereco(id, novosDados);

        assertEquals("Rua Nova", resultado.getRua());
        assertEquals("Cidade Nova", resultado.getCidade());
        assertEquals("11111-111", resultado.getCep());
        verify(enderecoRepository, times(1)).save(enderecoExistente);
    }

    @Test
    void quandoUsuarioTentarAtualizarUmIdQueNaoExisteDeveLancarExcecao() {
        Long id = 99L;
        Endereco novosDados = new Endereco();

        when(enderecoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> enderecoService.atualizarEndereco(id, novosDados));
    }

    @Test
    void quandoIdExisteDeveDeletarEndereco() {
        Long id = 1L;
        Endereco endereco = new Endereco();
        endereco.setId(id);

        when(enderecoRepository.findById(id)).thenReturn(Optional.of(endereco));
        doNothing().when(enderecoRepository).delete(endereco);

        enderecoService.deletarEndereco(id);

        verify(enderecoRepository, times(1)).delete(endereco);
    }

    @Test
    void quandoUsuarioTentarRemoverUmIdQueNaoExisteDeveLancarExcecao() {
        Long id = 99L;

        when(enderecoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> enderecoService.deletarEndereco(id));
    }
}