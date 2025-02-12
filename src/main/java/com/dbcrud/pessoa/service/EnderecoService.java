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
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco buscarEnderecoPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }

    public Endereco atualizarEndereco(Long id, Endereco endereco) {
        Endereco enderecoExistente = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        enderecoExistente.setRua(endereco.getRua());
        enderecoExistente.setCidade(endereco.getCidade());
        enderecoExistente.setCep(endereco.getCep());

        return enderecoRepository.save(enderecoExistente);
    }

    public void deletarEndereco(Long id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        enderecoRepository.delete(endereco);
    }
}


