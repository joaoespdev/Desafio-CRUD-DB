package com.dbcrud.pessoa.dto;

import java.util.List;
import com.dbcrud.pessoa.entity.Endereco;
import com.dbcrud.pessoa.entity.Pessoa;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

public class PessoaDTO {


    private Long id;

    @NotNull(message = "O nome não pode ser nulo.")
    private String nome;

    @NotNull(message = "O CPF não pode ser nulo.")
    private String cpf;

    @NotNull(message = "A idade não pode ser nula.")
    private int idade;

    private List<Endereco> endereco;

    public PessoaDTO(Pessoa pessoa) {
        BeanUtils.copyProperties(pessoa, this);
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.idade = pessoa.getIdade();
        this.cpf = pessoa.getCpf();
        this.endereco = pessoa.getEndereco();
    }

    public PessoaDTO() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

}
