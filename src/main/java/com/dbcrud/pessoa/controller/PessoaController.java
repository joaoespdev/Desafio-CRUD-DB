package com.dbcrud.pessoa.controller;

import com.dbcrud.pessoa.dto.PessoaDTO;
import com.dbcrud.pessoa.entity.Pessoa;
import com.dbcrud.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<PessoaDTO> salvar(@RequestBody Pessoa pessoa) {
        Pessoa pessoaSalva = pessoaService.salvar(pessoa);
        PessoaDTO pessoaDTO = new PessoaDTO(pessoaSalva);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaDTO);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> listaPessoa() {
        List<Pessoa> pessoas = pessoaService.listaPessoa();
        List<PessoaDTO> pessoaDTOs = pessoas.stream()
                .map(PessoaDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pessoaDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> buscarPessoaPorId(@PathVariable("id") Long id) {
        Optional<Pessoa> pessoa = pessoaService.buscarPorId(id);
        return pessoa.map(p -> ResponseEntity.ok(new PessoaDTO(p)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPessoa(@PathVariable("id") Long id) {
        Optional<Pessoa> pessoa = pessoaService.buscarPorId(id);
        if (pessoa.isPresent()) {
            pessoaService.removerPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarPessoa(@PathVariable("id") Long id, @RequestBody Pessoa pessoa){
        Optional<Pessoa> pessoaBase = pessoaService.buscarPorId(id);
        if (pessoaBase.isPresent()) {
            modelMapper.map(pessoa, pessoaBase.get());
            pessoaService.salvar(pessoaBase.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
