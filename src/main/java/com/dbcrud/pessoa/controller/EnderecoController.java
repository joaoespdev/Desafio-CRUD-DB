package com.dbcrud.pessoa.controller;

import com.dbcrud.pessoa.dto.EnderecoDTO;
import com.dbcrud.pessoa.entity.Endereco;
import com.dbcrud.pessoa.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoDTO> criarEndereco(@RequestBody EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoService.criarEndereco(enderecoDTO.toEntity()); // Converter DTO para Entidade
        EnderecoDTO createdEndereco = new EnderecoDTO(endereco); // Converter Entidade para DTO
        return new ResponseEntity<>(createdEndereco, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listarEnderecos() {
        List<EnderecoDTO> enderecos = enderecoService.listarEnderecos().stream()
                .map(endereco -> new EnderecoDTO(endereco)) // Converter Entidade para DTO
                .collect(Collectors.toList());
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarEnderecoPorId(@PathVariable Long id) {
        try {
            Endereco endereco = enderecoService.buscarEnderecoPorId(id); // Buscar Entidade
            EnderecoDTO enderecoDTO = new EnderecoDTO(endereco); // Converter Entidade para DTO
            return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {
        try {
            Endereco endereco = enderecoService.atualizarEndereco(id, enderecoDTO.toEntity()); // Atualizar com Entidade
            EnderecoDTO updatedEndereco = new EnderecoDTO(endereco); // Converter Entidade para DTO
            return new ResponseEntity<>(updatedEndereco, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
        try {
            enderecoService.deletarEndereco(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
