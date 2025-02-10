package com.dbcrud.pessoa.entity;

import com.dbcrud.pessoa.dto.EnderecoDTO;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(description = "Entidade que representa uma pessoa")
public class Pessoa implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Schema(hidden = true)
        private Long id;

        @Column(name = "nome", nullable = false)
        @Schema(description = "Nome da pessoa", example = "João da Silva", required = true)
        private String nome;

        @Column(name = "cpf", nullable = false, unique = true)
        @Schema(description = "CPF da pessoa", example = "123.456.789-10", required = true)
        private String cpf;

        @Column(name = "idade", nullable = false)
        @Schema(description = "Idade da pessoa", example = "30", required = true, minimum = "0")
        private int idade;

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "pessoa_id")
        @Schema(description = "Lista de endereços associados à pessoa")
        @ArraySchema(schema = @Schema(implementation = Endereco.class))
        private List<Endereco> endereco;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
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

}
