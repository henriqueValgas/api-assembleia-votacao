# Assembléia de Votação API

## Sobre o projeto 

API REST desenvolvida para genrenciamento de sessoões de votação em assembleias.

O sistema permite:

- Cadastro de associados
- Criação de pautas
- Abertura de sessões de votação
- Registro de votos
- Apuração de resultados

Projeto desenvolvido com foco em boas práticas de desenvolvimento backend utilizando spring boot

---

### Tecnologias utilizadas

- java 25
- spring boot 
- Spring Data JPA
- Hibernate 
- MySQL
- Maven
- Bean Validation
- Swagger/OpenApi

## Estrutura do projeto
```text
src/main/java
├──controller
├──service
├──repository
├──model
├──dto
├──mapper
├──exceptions
└──config
```

## Funcionalidades

### Associados

- Criar associados
- Buscar associados
- Listar associados
- Atualizar associado
- Remover associado

### Pautas

- Criar pauta 
- Abrir sessão de votação
- Buscar pauta
- Listar pautas
- Apurar resultado

### Votos

- Registrar voto
- Buscar votos
- Impedir voto duplicado

---

## Como executar o projeto

### Pré-requisitos

- Java 25
- Maven
- MySQL

### Clonar repositório

```bash
git clone git@github.com:henriqueValgas/api-assembleia-votacao.git
```
## Rodar a aplicação
````bash
- mvn clean install
- mvn spring-boot:run
````

## Documentacao da Api(Swagger)

A documentação da API está disponivel via swagger UI:

http://localhost:8080/swagger-ui/index.html#/

Com ela é possivel:
- visualizar todos os endpoints
- Testar requisições diretamente no swagger
- Consultar DTOs e contratos da API


## Testes
- Testes unitarios no service
- Validação de regras de negocio
- Cenário de voto duplicado

## Melhorias futuras
- Autenticação e autorização (JWT)
- Paginação de resultados
- Auditoria de votos

### Observações 
- Esse projeto foi desenvolvido focando no aprendizado de backend utilizando e boas praticas
de arquitetura em java com spring boot 
- desafio esta disponivel no link: https://www.reddit.com/r/brdev/comments/1fgh625/ajuda_com_desafio_t%C3%A9cnico_em_javaspring_para_vaga/?tl=pt-br.
- Optei por utilizar Mappers manuais para aprender como funcionam