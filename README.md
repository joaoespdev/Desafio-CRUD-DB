# Desafio - CRUD de Pessoa e Endereço com Spring Boot ☕

Este projeto implementa uma aplicação Java utilizando o **Spring Boot** para fornecer uma API REST que gerencia as entidades **Pessoa** e **Endereço**, com um relacionamento de um-para-muitos. O sistema permite a criação, leitura, atualização e exclusão (CRUD) dessas entidades, além de oferecer funcionalidades adicionais, como a listagem de pessoas e seus endereços, e a validação de campos obrigatórios.

<br>

## 📋 Tecnologias Utilizadas

- **Java** 21+
- **Spring Boot** 3.4.1+
- **Spring Boot Starter Web**
- **Spring Data JPA**
- **H2 Database** (em memória)
- **ModelMapper** 2.3.5+
- **Lombok**
- **Mockito & JUnit**
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
pessoa/
├── .idea/
├── .mvn/
├── src/
│ ├── main/
│ │ ├── java/com/dbcrud/pessoa/
│ │ │ ├── controller/
│ │ │ │ ├── EnderecoController.java
│ │ │ │ ├── PessoaController.java
│ │ │ ├── dto/
│ │ │ │ ├── EnderecoDTO.java
│ │ │ │ ├── PessoaDTO.java
│ │ │ ├── entity/
│ │ │ │ ├── Endereco.java
│ │ │ │ ├── Pessoa.java
│ │ │ ├── repository/
│ │ │ │ ├── EnderecoRepository.java
│ │ │ │ ├── PessoaRepository.java
│ │ │ ├── service/
│ │ │ │ ├── EnderecoService.java
│ │ │ │ ├── PessoaService.java
│ │ │ ├── PessoaApplication.java
│ │ ├── resources/
│ │ │ ├── static/
│ │ │ ├── templates/
│ │ │ ├── application.yml
│ ├── test/
│ │ ├── java/com/dbcrud/pessoa/
│ │ │ ├── controller/
│ │ │ │ ├── EnderecoControllerTest.java
│ │ │ │ ├── PessoaControllerTest.java
│ │ │ ├── dto/
│ │ │ │ ├── EnderecoDTOTeste.java
│ │ │ │ ├── PessoaDTOTeste.java
│ │ │ ├── service/
│ │ │ │ ├── EnderecoServiceTest.java
│ │ │ │ ├── PessoaServiceTest.java
│ │ │ ├── PessoaApplicationTests.java
├── target/
```
<br>

## 🚀 Funcionalidades da API

A API permite as seguintes operações:

- **Listar todas as pessoas** e seus respectivos endereços.
- **Criar uma nova pessoa** com um ou mais endereços.
- **Atualizar os dados de uma pessoa** e/ou seus endereços.
- **Excluir uma pessoa** e todos os seus endereços.
<br>

## ⚙ Testes Automatizados

Este projeto inclui testes unitários para as funcionalidades de CRUD (Create, Read, Update, Delete) das entidades `Pessoa` e `Endereco`. Os testes foram desenvolvidos utilizando JUnit 5 e Mockito para simular o comportamento das camadas de serviço e repositório.
<br>

Os testes estão organizados em pacotes separados para cada camada do projeto:

- 📦 **Controller**: Testes para os endpoints da API.
- 🔧 **Service**: Testes para a lógica de negócio.
- 📄 **DTO**: Testes para os objetos de transferência de dados (DTOs).

<br>

### Testes de Controller

#### `EnderecoControllerTest`
- **criarEndereco**: Verifica se um endereço é criado com sucesso e retorna o status `HTTP 201 Created`.
- **listarEnderecos**: Testa a listagem de endereços, retornando uma lista vazia ou uma lista com endereços.
- **buscarEnderecoPorId**: Verifica se um endereço é retornado corretamente quando o ID existe, ou retorna `HTTP 404 Not Found` quando o ID não existe.
- **atualizarEndereco**: Testa a atualização de um endereço existente e retorna `HTTP 200 OK`, ou `HTTP 404 Not Found` se o ID não existir.
- **deletarEndereco**: Verifica se um endereço é deletado com sucesso, retornando `HTTP 204 No Content`, ou `HTTP 404 Not Found` se o ID não existir.

#### `PessoaControllerTest`
- **salvar**: Verifica se uma pessoa é salva com sucesso e retorna o status `HTTP 201 Created`.
- **listaPessoa**: Testa a listagem de pessoas, retornando uma lista de pessoas.
- **buscarPessoaPorId**: Verifica se uma pessoa é retornada corretamente quando o ID existe, ou retorna `HTTP 404 Not Found` quando o ID não existe.
- **removerPessoa**: Testa a remoção de uma pessoa, retornando `HTTP 204 No Content` se o ID existir, ou `HTTP 404 Not Found` se o ID não existir.
- **atualizarPessoa**: Verifica a atualização de uma pessoa existente, retornando `HTTP 204 No Content`, ou `HTTP 404 Not Found` se o ID não existir.

### Testes de Service

#### `EnderecoServiceTest`
- **criarEndereco**: Verifica se um endereço é salvo no repositório.
- **listarEnderecos**: Testa a listagem de todos os endereços.
- **buscarEnderecoPorId**: Verifica se um endereço é retornado corretamente quando o ID existe, ou lança uma exceção se o ID não existir.
- **atualizarEndereco**: Testa a atualização de um endereço existente.
- **deletarEndereco**: Verifica se um endereço é deletado corretamente.

#### `PessoaServiceTest`
- **salvar**: Verifica se uma pessoa é salva no repositório.
- **listaPessoa**: Testa a listagem de todas as pessoas.
- **buscarPorId**: Verifica se uma pessoa é retornada corretamente quando o ID existe.
- **removerPorId**: Testa a remoção de uma pessoa pelo ID.

### Testes de DTO

#### `EnderecoDTOTest`
- **testConstrutorComEndereco**: Verifica se os valores de um endereço são copiados corretamente para o DTO.
- **testToEntity**: Testa a conversão de um DTO para uma entidade `Endereco`.
- **testGettersESetters**: Verifica os getters e setters do DTO.
- **testConstrutorVazio**: Testa o construtor vazio do DTO.

#### `PessoaDTOTest`
- **testConstrutorComPessoa**: Verifica se os valores de uma pessoa são copiados corretamente para o DTO.
- **testGettersESetters**: Testa os getters e setters do DTO.
- **testConstrutorDeveSerNull**: Verifica se o construtor vazio inicializa os campos como `null`.

<br>
  
## 💿 Como Rodar o Projeto

Siga os passos abaixo para clonar, configurar e executar o projeto localmente.

### 1. Clone este repositório
```bash
git clone https://github.com/joaoespdev/Desafio-CRUD-DB.git
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

### 5. 🐱‍🚀 Rode a aplicação e teste com Swagger
http://localhost:8080/swagger-ui/index.html
