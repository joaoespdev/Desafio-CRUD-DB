package com.dbcrud.pessoa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data //GetAndSetters e ToString automaticamente
@AllArgsConstructor // Criar construtor com as propriedades de Pessoa
@NoArgsConstructor // Criar construtor vazio
@Builder // Ajudar na criação de objetos Pessoa
@Entity // Informar que é uma entidade de banco de dados
public class Pessoa implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO) //Criar id por pessoa auto
        private Long id;

        @Column(name = "nome", nullable = false) //nullable false - obriga envio do campo nome
        private String nome;

        @Column(name = "cpf")
        private String cpf;

        @Column(name = "idade", nullable = false)
        private int idade;

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "pessoa_id")
        private List<Endereco> endereco;

        public Long getId() {
                return id;
        }

        public void setId (Long id) {
                this.id = id;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome){
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
