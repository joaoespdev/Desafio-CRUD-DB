package com.dbcrud.pessoa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Entity
@Schema(description = "Entidade que representa endereços")
public class Endereco {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "rua")
    @Schema(description = "Nome da Rua", example = "Av José de Souza")
    private String rua;

    @Column(name = "cidade")
    @Schema(description = "Nome da cidade", example = "São Paulo")
    private String cidade;

    @Column(name = "cep", nullable = false)
    @Schema(description = "CEP/Código Postal", example = "22588-267", required = true)
    private String cep;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
