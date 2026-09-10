# Assembléia de Votação API

## Sobre o projeto

API REST desenvolvida para gerenciamento de sessões de votação em assembleias.

O sistema permite:

* Cadastro de associados
* Criação de pautas
* Abertura de sessões de votação
* Verificação da aptidão do associado para votar
* Registro de votos
* Apuração de resultados

O projeto também possui um **mock de serviço externo** responsável por simular a consulta de aptidão do associado para votação.

Projeto desenvolvido com foco em boas práticas de desenvolvimento backend utilizando **Java e Spring Boot**.

---

## Tecnologias utilizadas

* Java 25
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Bean Validation
* Swagger/OpenAPI
* Docker
* Docker Compose

---

## Estrutura do projeto

```text
api-assembleia-votacao
├── src
│   └── main
│       └── java
│           ├── controller
│           ├── service
│           ├── repository
│           ├── model
│           ├── dto
│           ├── mapper
│           ├── exceptions
│           └── config
│
├── docs
│   └── create-tables.sql
│
├── mock-user-info
│   ├── src
│   ├── pom.xml
│   └── Dockerfile
│
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

O projeto `mock-user-info` é uma aplicação Spring Boot independente utilizada para simular um serviço externo de consulta de aptidão para votação.

---

## Funcionalidades

### Associados

* Criar associados
* Buscar associados
* Listar associados
* Atualizar associado
* Remover associado

### Pautas

* Criar pauta
* Buscar pauta
* Listar pautas
* Abrir sessão de votação
* Apurar resultado

### Votos

* Registrar voto
* Buscar votos
* Impedir voto duplicado
* Consultar votos por CPF

### Aptidão para votação

Antes do registro do voto, a aplicação consulta o serviço `mock-user-info` para verificar se o associado está apto a votar.

O mock simula as respostas de um serviço externo de consulta de usuários, permitindo testar a integração entre APIs sem depender de um serviço externo real.

---

# Como executar o projeto

O projeto pode ser executado de duas formas:

1. **Localmente**, utilizando Java, Maven e MySQL.
2. **Utilizando Docker Compose**, que inicia a API, o banco de dados e o serviço mock.

---

# Opção 1 — Execução local

## Pré-requisitos

Para executar a aplicação localmente, é necessário ter instalado:

* Java 25
* Maven
* MySQL

### Clonar o repositório

```bash
git clone https://github.com/henriqueValgas/api-assembleia-votacao.git
cd api-assembleia-votacao
```

### Configuração do banco de dados

Crie o banco de dados MySQL:

```sql
CREATE DATABASE assembleiadb;
```

As tabelas necessárias para a aplicação estão disponíveis no arquivo:

```text
docs/create-tables.sql
```

A aplicação utiliza variáveis de ambiente para configurar a conexão com o banco de dados:

```text
DB_URL
DB_USER
DB_PASSWORD
```

### Rodar a aplicação

O projeto possui Maven Wrapper, portanto não é necessário ter o Maven configurado globalmente.

No Windows:

```bash
./mvnw.cmd clean install
```

Depois:

```bash
./mvnw.cmd spring-boot:run
```

A aplicação estará disponível em:

```text
http://localhost:8080
```

> Para utilizar a funcionalidade de verificação de aptidão para votação durante a execução local, o serviço `mock-user-info` também deverá estar em execução.

---

# Opção 2 — Execução com Docker Compose

## Pré-requisito

Para executar o projeto utilizando Docker, é necessário ter instalado:

* Docker Desktop

Não é necessário instalar Java, Maven ou MySQL na máquina para executar a aplicação utilizando esta opção.

O projeto já possui o serviço `mock-user-info`, portanto toda a estrutura necessária para executar e testar a integração entre as APIs está incluída no repositório.

## Iniciar a aplicação

Na raiz do projeto, execute:

```bash
docker compose up --build
```

O Docker Compose irá:

1. Criar o container do MySQL.
2. Inicializar o banco de dados utilizando o script `docs/create-tables.sql`.
3. Construir a aplicação principal utilizando o Dockerfile.
4. Criar o container da API.
5. Construir e iniciar o serviço `mock-user-info`.
6. Configurar a comunicação entre os containers.
7. Iniciar a API conectada ao banco de dados e ao serviço mock.

### Containers

Após a inicialização, os principais serviços serão:

```text
assembleia-mysql
assembleia-api
mock-user-info
```

É possível verificar o estado dos containers com:

```bash
docker compose ps
```

### Acessar a aplicação

Após os containers iniciarem, a API estará disponível em:

```text
http://localhost:8080
```

O serviço mock estará disponível na máquina host em:

```text
http://localhost:8081
```

### Swagger

A documentação da API pode ser acessada através do Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

Com o Swagger é possível:

* Visualizar todos os endpoints
* Testar requisições diretamente pela interface
* Consultar os DTOs
* Consultar os contratos da API

---

## Comunicação entre os containers

O Docker Compose cria uma rede interna para permitir a comunicação entre os serviços.

A API utiliza o nome do serviço para acessar o MySQL:

```text
jdbc:mysql://mysql:3306/assembleiadb
```

Da mesma forma, a API utiliza o nome do serviço `mock-user-info` para acessar o serviço de verificação de aptidão:

```text
http://mock-user-info:8081
```

Dentro da rede Docker, os containers não utilizam `localhost` para se comunicar entre si.

A porta `3307` é utilizada para acesso ao MySQL a partir da máquina host, enquanto a comunicação entre os containers ocorre através da porta interna `3306`.

As portas utilizadas são:

| Serviço        | Porta do host | Porta do container |
| -------------- | ------------: | -----------------: |
| API            |          8080 |               8080 |
| Mock User Info |          8081 |               8081 |
| MySQL          |          3307 |               3306 |

---

## Testando a integração com o Mock User Info

Ao registrar um voto, a API consulta o serviço `mock-user-info` para verificar se o CPF informado está apto a votar.

O fluxo simplificado é:

```text
Cliente
   │
   │ registra voto
   ▼
API Assembleia
   │
   │ consulta CPF
   ▼
Mock User Info
   │
   │ retorna aptidão
   ▼
API Assembleia
   │
   ├── apto → registra voto
   │
   └── não apto → rejeita votação
```

Dessa forma, a integração com um serviço externo pode ser testada localmente sem depender de uma API de terceiros.

---

## Parar os containers

Para parar a aplicação:

```bash
docker compose down
```

Para iniciar novamente os containers sem reconstruir as imagens:

```bash
docker compose up
```

### Dados do banco

O banco de dados utiliza um volume Docker para persistir os dados:

```text
mysql_data
```

Por isso, ao executar:

```bash
docker compose down
```

os containers são removidos, mas os dados do banco permanecem no volume.

Para remover também o volume e apagar os dados persistidos:

```bash
docker compose down -v
```

> **Atenção:** o comando `docker compose down -v` remove o volume do banco de dados e, consequentemente, os dados persistidos.

---

# Testes

O projeto possui testes unitários focados principalmente na camada de serviço e nas regras de negócio.

Entre os cenários testados estão:

* Validação de regras de negócio
* Cadastro e manipulação de entidades
* Cenário de voto duplicado

Para executar os testes utilizando o Maven Wrapper:

```bash
./mvnw.cmd test
```

---

# Documentação da API

A documentação da API está disponível através do Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# Melhorias futuras

* Autenticação e autorização utilizando JWT
* Paginação de resultados
* Auditoria de votos
* Substituição do mock por uma integração com serviço externo real

---

# Observações

* Este projeto foi desenvolvido com foco no aprendizado de desenvolvimento backend e na aplicação de boas práticas de arquitetura utilizando Java e Spring Boot.
* O desafio utilizado como base para o desenvolvimento está disponível no [Reddit](https://www.reddit.com/r/brdev/comments/1fgh625/ajuda_com_desafio_t%C3%A9cnico_em_javaspring_para_vaga/?tl=pt-br).
* Optei por utilizar **mappers manuais** como forma de aprofundar o entendimento sobre conversão entre entidades e DTOs.
* O projeto possui suporte tanto para execução local quanto para execução completa utilizando Docker Compose.
* O serviço `mock-user-info` está incluído no próprio repositório para permitir a execução e o teste da integração entre APIs sem depender de serviços externos.
