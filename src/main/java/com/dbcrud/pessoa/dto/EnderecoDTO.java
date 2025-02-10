package com.dbcrud.pessoa.dto;

import com.dbcrud.pessoa.entity.Endereco;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

public class EnderecoDTO {

    private Long id;

    @NotNull(message = "O nome da rua não pode ser nulo.")
    private String rua;

    @NotNull(message = "O nome da cidade não pode ser nulo.")
    private String cidade;

    @NotNull(message = "Cep não pode ser nulo.")
    private String cep;

    public EnderecoDTO(Endereco endereco) {
        BeanUtils.copyProperties(endereco, this);
        this.rua = endereco.getRua();
        this.cep = endereco.getCep();
        this.cidade = endereco.getCidade();
        this.id = endereco.getId();
    }

    public EnderecoDTO() {

    }

    public Endereco toEntity() {
        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(this, endereco);
        return endereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

}
