# Desafio - CRUD de Pessoa e Endereço com Spring Boot ☕

Este projeto implementa uma aplicação Java utilizando o **Spring Boot** para fornecer uma API REST que gerencia as entidades **Pessoa** e **Endereço**, com um relacionamento de um-para-muitos. O sistema permite a criação, leitura, atualização e exclusão (CRUD) dessas entidades, além de oferecer funcionalidades adicionais, como a listagem de pessoas e seus endereços, e a validação de campos obrigatórios.

## 📋 Tecnologias Utilizadas

- **Java** 21+
- **Spring Boot** 3.4.1+
- **Spring Boot Starter Web**
- **Spring Data JPA**
- **H2 Database** (em memória)
- **ModelMapper** 2.3.5+
- **Lombok**
- **Swagger** para documentação e testes da API

## 👨‍🏫 Funcionalidades

### Pessoa
A entidade **Pessoa** possui os seguintes campos:
- **ID** (auto gerado)
- **Nome**
- **Idade**
- **CPF**
- **Endereços** (obrigatório)
  
### Endereço
A entidade **Endereço** possui os seguintes campos:
- **ID** (auto gerado)
- **Rua**
- **Cidade**
- **CEP**
<br></br>

## 📂 Estrutura do Projeto

```plaintext
pessoa
├── .idea
├── .mvn
├── src
│   ├── main
│   │   └── java
│   │       └── com.dbcrud.pessoa
│   │           ├── entity
│   │           │   ├── Endereco
│   │           │   └── Pessoa
│   │           ├── http.controller
│   │           │   ├── EnderecoController
│   │           │   └── PessoaController
│   │           ├── repository
│   │           │   ├── EnderecoRepository
│   │           │   └── PessoaRepository
│   │           ├── service
│   │           │   ├── EnderecoService
│   │           │   └── PessoaService
│   │           └── PessoaApplication
│   └── resources
├── test
└── target
```
<br></br>
## 🚀 Funcionalidades da API

A API permite as seguintes operações:

- **Listar todas as pessoas** e seus respectivos endereços.
- **Criar uma nova pessoa** com um ou mais endereços.
- **Atualizar os dados de uma pessoa** e/ou seus endereços.
- **Excluir uma pessoa** e todos os seus endereços.
  
## 💿 Como Rodar o Projeto

Siga os passos abaixo para clonar, configurar e executar o projeto localmente.

### 1. Clone este repositório
```bash
git clone https://github.com/usuario/repo.git
cd repo
```
### 2. Verifique no terminal se o Java está configurado
```bash
java -version
```
### 3. Verifique a instalação do Maven
```bash
mvn -version
```
### 4. Configure o ambiente
Certifique-se de que as variáveis de ambiente **JAVA_HOME** e **MAVEN_HOME** estão configuradas corretamente.<br></br>

### 5. 🐱‍🚀 Teste a aplicação com Swagger
http://localhost:8080/swagger-ui/index.html
