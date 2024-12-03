<div align="center">
  <img alt="Ada" style="border-radius: 50%; width: 50px;" src="ImagensReadme/Ada.png">
  <h1 style="font-size: 20px;"><b>REST API - JAVA SPRING BOOT </b></h1>
</div>

<h1 align="center">
<img alt="Santander Coders" src="https://ada-strapi-production.s3.sa-east-1.amazonaws.com/Thumb_Meta_20_f25502065b.png" width="330" height="200">
</h1></h1>

<div align="center">
<b><span style="font-size: 18px;">Módulo 5: Programação Web II </span></b><br>Projeto desenvolvido pelo grupo 5 da turma 1173 do programa <b>Santander Coders</b> 2024.1 em parceria com a <b>Ada Tech</b>
</div>

## Objetivos do Projeto

Construir uma API REST com os seguintes itens:

- Persistência em banco de dados (H2 ou Postgres);
- Configuração de segurança: controle de rota e login (jwt opcional);
- Consumo de uma API externa pública;
- Opcionais: implementação de Swagger e Front-end.

## Descrição do Projeto

O projeto é uma aplicação desenvolvida em Java utilizando o framework Spring Boot. Foi desenvolvida uma API RESTful para
consultar informações sobre filmes, e também inclui funcionalidades de persistência de dados, segurança e consumo da API
externa pública do [The Movie Database (TMDb)](https://www.themoviedb.org/).

Também foi desenvolvida uma implementação inicial de front-end para que usuário possa interagir com a aplicação.

### Principais Funcionalidades

1. **Persistência em Banco de Dados**:
    - Utilização do banco de dados H2 em memória para armazenamento temporário dos dados.

2. **Console H2**:
    - Acesso ao console H2 para visualização e manipulação dos dados armazenados no banco de dados em memória.

3. **Configuração de Segurança**:
    - Implementação de controle de rota e autenticação básica com login e senha.

4. **Consumo de API Externa**:
    - Integração com a API pública do The Movie Database (TMDb) para obter informações sobre filmes.

5. **Front-end**:
    - Implementação inicial de front-end com as seguintes funcionalidades:
        - Cadastro de usuários;
        - Cadastro e classificação de filmes;
        - Busca de filmes cadastrados no banco de dados.

<table align="center" style="width: 80%;">
  <tr>
    <td align="center" style="width: 20%;">
<img style="border-radius: 5%;" src="ImagensReadme/Front1.jpeg" height="290px" width="280px;" alt=""/><br />
    <td align="center" style="width: 20%;">
<img style="border-radius: 5%;" src="ImagensReadme/Front2.jpeg" height="290px" width="280px;" alt=""/><br />  </tr>
</table>

## Linguagem de Programação e Framework Utilizados

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)

## Ferramentas Utilizadas

![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)

## Guia de Uso

1. **Clone o repositório para sua máquina local**:
   ```sh
   git clone https://github.com/lauluah/API-filmes.git
   ```

2. **Navegue até o diretório do projeto**:
   ```sh
   cd seu-repositorio
   ```

3. **Execute o comando para iniciar a aplicação**:
   ```sh
   ./mvnw spring-boot:run
   ```

4. **Acesse o console H2 para visualizar o banco de dados**:

   Acesse `http://localhost:8080/h2` em seu navegador. Utilize as seguintes credenciais para login:
    - **JDBC URL**: `jdbc:h2:mem:test`
    - **User Name**: `adatech`
    - **Password**: `54321`

5. **Interaja com a aplicação e com a API REST**

    - Cadastre usuários e filmes no banco de dados e consulte as informações utilizando as rotas disponíveis.
    - Envie requisições à API externa para buscar informações sobre filmes.
    - Utilize uma ferramenta HTTP Client, como [Postman](https://www.postman.com/)
      ou [Insomnia](https://insomnia.rest/).
        - URL base: `http://localhost:8080` (ou de acordo com a porta configurada na máquina).

### EXEMPLOS DE REQUISIÇÕES

#### Criar um Usuário no Banco de Dados

- **Endpoint**: `http://localhost:8080/usuarios`
- **Método**: POST
- **Corpo da Requisição**:
  ```json
  {
    "nome": "Nome do Usuário",
    "idade": "Idade do Usuário",
    "cpf": "CPF do Usuário", //(necessário ser um CPF válido)
    "password": "Senha do Usuário",
    "email": "Email do Usuário"
    }

#### Buscar Informações de um Usuário Criado

- **Endpoint**: `/usuarios/{id}`
- **Autorizar com login e senha**: email e senha do usuário criado
- **Método**: GET
- **Exemplo de Requisição**:
  ```sh
  curl -X GET  http://localhost:8080/usuarios/1
  ```
- É possível buscar o usuário pelos seguintes dados: {id}, {nome}, {email} e {cpf} (basta substituir o valor final do
  parâmetro na URL).

#### Deletar um Usuário do Banco de Dados

- **Endpoint**: `/usuarios/{id}`
- **Autorizar com login e senha**: email e senha do usuário criado
- **Método**: DELETE
- **Exemplo de Requisição**:
  ```sh
  curl -X DELETE  http://localhost:8080/usuarios/1
  ```

#### Criar um Filme no Banco de Dados

- **Endpoint**: `/filmes`
- **Método**: POST
- **Corpo da Requisição**:
  ```json
  {
    "nomeFilme": "Nome do Filme",
    "genero": "Gênero do Filme",
    "nota": "Nota do Filme",
    "comentario": "Comentário do Filme",
    "corAvaliacao": "Cor da Avaliação do Filme"  
  }
  ```

#### Buscar Informações de um Filme Criado

- **Endpoint**: `/filmes/nomeFilme?nome={nomeFilme}`
- **Método**: GET
- **Exemplo de Requisição**:
  ```sh
  curl -X GET  http://localhost:8080/filmes/nomeFilme?nome={nomeFilme}
  ```
- É possível buscar o filme criado pelos seguintes dados: {id}, {nome}, {gênero}, {cor da avaliação} e {nota} (basta
  substituir o valor final do parâmetro na URL).

#### Buscar Filme pelo Nome na API Externa

- **Endpoint**: `/api/filmes/tmdb/nome/{nomeFilme}`
- **Método**: GET
- **Exemplo de Requisição**:
  ```sh
  curl -X GET  http://localhost:8080/api/filmes/tmdb/nome/Matrix
  ```
- Será retornado um JSON com as seguinte informações sobre o filme buscado: título, sinopse, data de lançamento, tempo
  de duração, gênero, popularidade e idioma original.

#### Popular o Banco de Dados com Filmes da API Externa

- **Endpoint**: `/api/filmes/tmdb/nome/{nomeFilme}`
- **Método**: POST
- **Exemplo de Requisição**:
  ```sh
  curl -X POST  http://localhost:8080/api/filmes/tmdb/nome/Inception
  ```
## Diagrama de Classes
<img style="border-radius: 5%;" src="ImagensReadme/diagrama.jpeg" height="400px" width="460px;" alt="Diagrama de Classes"/><br />

## Entregas do Projeto

- [x] Código fonte: API REST em Java com Spring Boot com persistência de dados, configuração de segurança com login e
  senha e consumo de API externa
- [x] Documentação com descrição do projeto e guia de uso
- [x] Apresentação do código, da documentação e da aplicação em funcionamento
- [x] Implementação inicial do Front-end
- [x] Diagrama de classes

## O Que Não Foi Entregue

- [ ] Implementação de Swagger
- [ ] Autenticação JWT

## Principais Desafios Encontrados

- **Persistência de Dados**: Configurar o banco de dados H2 e realizar operações CRUD.
- **Integração com API Externa**: Aprender a consumir uma API externa e tratar os dados recebidos.
- **Configuração de Segurança**: Implementar controle de rota e autenticação básica.
- **Front-end**: Desenvolver a interface do usuário para interagir com a aplicação e implementar a comunicação com o
  back-end.
  
## **Testes Automatizados**  
Nosso projeto conta com **testes unitários**, **testes de integração** e **testes end-to-end** para garantir a qualidade e a confiabilidade do sistema.

<img src="https://hermes.dio.me/assets/articles/7057c5f3-4df0-4a41-a913-bd7d6eafa7e4.png" alt="Testes Automatizados" width="500" height="300">

### **Como executar os testes**
1. Navegue até o pacote de testes no projeto (`src/test/java`).  
2. Clique com o botão direito em `com.adatech.filmes_API`.  
3. Selecione a opção **"Run tests in com.adatech.filmes_API"** para executar todos os testes de uma vez.  
   - **Alternativamente**: Você pode rodar os testes individualmente, acessando as classes de teste específicas e clicando em **"Run"** na barra lateral.

### **Cobertura dos testes**
- **Unitários**: Validam métodos isolados em classes específicas.  
- **Integração**: Testam a interação entre diferentes partes do sistema.  
- **End-to-end**: Verificam o comportamento completo da aplicação, simulando fluxos reais.

## Integrantes

<table align="center" style="width: 80%;">
  <tr>
    <td align="center" style="width: 20%;">
      <img style="border-radius: 5%;" src="ImagensReadme/LauraFoto.jpeg" height="90px" width="100px;" alt=""/><br />
      <sub><b>Ana Laura Marques Rodrigues</b></sub><br />
      <a href="https://github.com/lauluah" target="_blank">GitHub</a>
    </td>
    <td align="center" style="width: 20%;">
      <img style="border-radius: 5%;" src="ImagensReadme/Arthur.jpeg" height="90px" width="100px;" alt=""/><br />
      <sub><b>Arthur Gabriel De Menezes Viana</b></sub><br />
      <a href="https://github.com/arthurgmv" target="_blank">GitHub</a>
    </td>
    <td align="center" style="width: 20%;">
      <img style="border-radius: 5%;" src="ImagensReadme/Taina.jpeg" height="90px" width="100px;" alt=""/><br />
      <sub><b>Tainá Souza Peixoto</b></sub><br />
      <a href="https://github.com/peixotots" target="_blank">GitHub</a>
    </td>
  </tr>
</table>

