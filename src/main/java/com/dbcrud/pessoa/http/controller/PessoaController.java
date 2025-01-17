package com.dbcrud.pessoa.http.controller;

import com.dbcrud.pessoa.entity.Pessoa;
import com.dbcrud.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.modelmapper.ModelMapper;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa salvar(@RequestBody Pessoa pessoa){
        return pessoaService.salvar(pessoa);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa> listaPessoa() {
        return pessoaService.listaPessoa();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
        public Pessoa buscarPessoaPorId(@PathVariable("id") Long id){
        return pessoaService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerPessoa(@PathVariable("id") Long id){
        pessoaService.buscarPorId(id)
                .map(pessoa -> {
                    pessoaService.removerPorId(pessoa.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada."));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPessoa(@PathVariable("id") Long id, @RequestBody Pessoa pessoa){
        pessoaService.buscarPorId(id)
                .map(pessoaBase -> {
                    modelMapper.map(pessoa, pessoaBase);
                    pessoaService.salvar(pessoaBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada."));
    }
}
