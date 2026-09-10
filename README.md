# Assembléia de Votação API

## Sobre o projeto

API REST desenvolvida para gerenciamento de sessões de votação em assembleias.

O sistema permite:

* Cadastro de associados
* Criação de pautas
* Abertura de sessões de votação
* Registro de votos
* Apuração de resultados

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
src/main/java
├── controller
├── service
├── repository
├── model
├── dto
├── mapper
├── exceptions
└── config
```

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
* Abrir sessão de votação
* Buscar pauta
* Listar pautas
* Apurar resultado

### Votos

* Registrar voto
* Buscar votos
* Impedir voto duplicado

---

# Como executar o projeto

O projeto pode ser executado de duas formas:

1. **Localmente**, utilizando Java, Maven e MySQL.
2. **Utilizando Docker Compose**, que inicia a API e o banco de dados em containers.

---

## Opção 1 — Execução local

### Pré-requisitos

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

---

# Opção 2 — Execução com Docker

### Pré-requisito

Para executar o projeto utilizando Docker, é necessário ter instalado:

* Docker Desktop

Não é necessário instalar Java, Maven ou MySQL na máquina para executar a aplicação utilizando esta opção.

### Iniciar a aplicação

Na raiz do projeto, execute:

```bash
docker compose up --build
```

O Docker Compose irá:

1. Criar o container do MySQL.
2. Inicializar o banco de dados.
3. Criar o container da API.
4. Construir a aplicação utilizando o Dockerfile.
5. Iniciar a API conectada ao banco de dados.

### Verificar os containers

Em outro terminal, execute:

```bash
docker compose ps
```

Os principais serviços são:

```text
assembleia-api
assembleia-mysql
```

### Acessar a aplicação

Após os containers iniciarem, a API estará disponível em:

```text
http://localhost:8080
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

### Parar os containers

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
assembleiavotacao_mysql_data
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

### Comunicação entre os containers

Quando a aplicação é executada através do Docker Compose, a API não utiliza `localhost` para acessar o MySQL.

A comunicação entre os containers utiliza o nome do serviço definido no `docker-compose.yml`:

```text
mysql
```

A conexão da API com o banco utiliza:

```text
jdbc:mysql://mysql:3306/assembleiadb
```

A porta `3307` é utilizada para acesso ao MySQL a partir da máquina host, enquanto a comunicação entre os containers ocorre através da porta interna `3306`.

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

---

# Observações

* Este projeto foi desenvolvido com foco no aprendizado de desenvolvimento backend e na aplicação de boas práticas de arquitetura utilizando Java e Spring Boot.
* O desafio utilizado como base para o desenvolvimento está disponível no Reddit.
* - O desafio utilizado como base para o desenvolvimento está disponível no [Reddit](https://www.reddit.com/r/brdev/comments/1fgh625/ajuda_com_desafio_t%C3%A9cnico_em_javaspring_para_vaga/?tl=pt-br).
* Optei por utilizar **mappers manuais** como forma de aprofundar o entendimento sobre conversão entre entidades e DTOs.
* O projeto possui suporte tanto para execução local quanto para execução completa utilizando Docker Compose.
