package com.dbcrud.pessoa.service;

import com.dbcrud.pessoa.entity.Endereco;
import com.dbcrud.pessoa.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco criarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco); //salvando endereco na interface
    }

    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll(); //métdo responsável pela listagem dos enderecos
    }


    //orElseThrow vai retornar algo esperado, ou ao invés de retornar null, retornará o que eu escolher
    //Como uma mensagem por exemplo.
    public Endereco buscarEnderecoPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }

    public Endereco atualizarEndereco(Long id, Endereco enderecoAtualizado) {
        Endereco endereco = buscarEnderecoPorId(id);

        endereco.setRua(enderecoAtualizado.getRua());
        endereco.setCidade(enderecoAtualizado.getCidade());
        endereco.setCep(enderecoAtualizado.getCep());

        return enderecoRepository.save(endereco);

    }

    public void deletarEndereco(Long id) {
        Endereco endereco = buscarEnderecoPorId(id);
        enderecoRepository.delete(endereco);
    }

}
