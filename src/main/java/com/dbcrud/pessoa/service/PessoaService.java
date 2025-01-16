package com.dbcrud.pessoa.service;

import com.dbcrud.pessoa.entity.Pessoa;
import com.dbcrud.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvar(Pessoa pessoa){
        return pessoaRepository.save(pessoa); //Serve tanto para salvar quanto atualizar
    }

    public List<Pessoa> listaPessoa() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPorId(Long id){
        return pessoaRepository.findById(id);
    }

    public void removerPorId(Long id){
        pessoaRepository.deleteById(id);
    }

}
