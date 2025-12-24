# PersonRegistry
API REST para gerenciamento de **Pessoas** e seus respectivos **Endereços** construída com **Spring Boot**.  
Cada Pessoa pode ter **apenas um Endereço**, mas um Endereço pode possuir **várias pessoas** — representando uma relação *One-to-Many* e *Many-to-One*

## 📌 Funcionalidades

### Pessoa
- Criar pessoa  
- Buscar pessoa por ID  
- Listar todos as pessoas  
- Atualizar pessoa  
- Deletar pessoa  
- Associa pessoa a um endereço 

### Endereço
- Criar um endereço  
- Buscar endereço por ID  
- Listar todas os endereços  
- Atualizar endereço  
- Deletar endereço  
- Lista pessoas relacionadas ao endereço  

## 🛠️ Tecnologias utilizadas

- **Java 17+**
- **Spring Boot**
  - Spring Web
  - Spring Data JPA
- **Hibernate**
- **SpringDoc OpenAPI (Swagger UI)**
- **Banco de dados**: Postgres via Docker
- **Maven**
- **Docker**

## 🚀 Como executar o projeto

### 1. Clone o repositório
```bash
git clone https://github.com/SEU-USUARIO/dev.bruno.PersonRegistry
cd PersonRegistry

Suba o banco de dados (certifique-se de ter docker baixado na máquina):

docker compose up -d
```
### 2. Configure o banco de dados (PosgreSQL)
```bash
No arquivo application.properties ou application.yml.

Posgres:

spring.datasource.url=jdbc:postgresql://localhost:5431/db_personregistry
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class=org.postgresql.Driver
```
### 3. Execute o projeto usando Maven:

```bash
mvn spring-boot:run
````
### 📚 Documentação da API (Swagger)

Após rodar o projeto, acesse:

```bash
http://localhost:8082/swagger-ui.html
```

### 🧩 Arquitetura

O projeto segue uma arquitetura em camadas:

```bash
src/
 └── main/java
     ├── controller      → Camada de entrada (REST)
     |__ service         → Regra de negócio                 
     |── repository      → Persistência (JPA)   
     ├── dto             → Objetos de transferência de dados
     |── model           → Entidades do banco
     |── mappers         → Conversão Entity ↔ DTO
````

### 🧩 Endpoints Pessoa
#### Listar ninjas:

```http
  GET /person
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `-` | `List` | Retorna as pessoas cadastradas |

#### Listar pessoa por ID:

```http
  GET /person/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `ListPersonDTO` | **Obrigatório**. O ID da pessoa que você quer listar |


```http
  POST /person/create
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `CreatePersonDTO` | `Person` | Cria uma pessoa |


```http
  DELETE /person/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `void` | Deleta uma pessoa por ID |

### 🧩 Endpoints Endereço

#### Listar Endereços:

```http
  GET /adress
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `-` | `ListAdressDTO` | Retorna os endereços cadastrados |

#### Listar endereços por ID:

```http
  GET /adress/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `ListAdressDTO` | **Obrigatório**. O ID do endereço que você quer |


#### Cria um endereço:

```http
  POST /missoes/criar
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `CreateAdressDTO` | `AdressDTO` | Cria um endereço |


```http
  DELETE /adress
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `void` | Deleta um endereço por ID |

```http
  PUT adress/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `UpdateAdressDTO` | Altera um endereço por ID |


#### Interage comigo aqui👇:

Linkedin: https://www.linkedin.com/in/bruno-barbosa-97b848382/