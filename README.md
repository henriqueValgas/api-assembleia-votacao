# Assembléia de Votação API

## Sobre o projeto 

API REST desenvolvida para genrenciamento de sessoões de votação em assembléias.

O sistema permite:

- Cadastro de associados
- Criação de pautas
- Abertura de sessões de votação
- Registro de votos
- Apuracao de resultados

Projeto desenvolvido com foco em boas práticas de desenvolvimento backend utilizando spring boot

---

### Tecnologias utilizadas

- java 25
- spring boot 
- Spring Data JPA
- Hibenate 
- MySQL
- Maven
- Bean Validation

## Estrutura do projeto
```text
src/main/java
├──controller
├──service
├──repository
├──model
├──dto
├──mapper
├──excepions
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
git clone http://github.com/###colocar aqui o link do repositorio assim que disponivel
