# DesafioSCTech

# Empreendimentos API

## Descrição da Solução

 **Empreendimentos API** é uma aplicação backend desenvolvida em **Java com Spring Boot**, voltada para o gerenciamento de empreendimentos.  
Permite o cadastro, listagem, atualização e exclusão de informações como:

- Nome do empreendimento  
- Nome do responsável  
- Município  
- Segmento de atuação  
- Email  
- Status  

O objetivo é fornecer uma API REST simples e funcional, que possa ser utilizada por qualquer frontend para integração, mesmo sem interface complexa.

---

## Tecnologias Utilizadas

- Java 17  
- Spring Boot  
- Spring Web  
- Spring Data JPA (para persistência)  
- H2 Database (banco de dados em memória para testes)  
- Jakarta Validation (Bean Validation)  
- Jackson (JSON)  
- Maven  
- Tomcat Embedded 

---

## Estrutura Geral do Projeto

## Estrutura Geral do Projeto

- **controller**
  - EmpreendimentoController.java
- **dto**
  - EmpreendimentoRequest.java
  - EmpreendimentoEdicaoRequest.java
  - EmpreendimentoResponse.java
- **exception**
  - RegistroExistenteException.java
  - RegistroNaoEncontradoException.java
  - RestExceptionHandler.java
- **model**
  - Empreendimento.java
  - ErroResponse.java
  - SegmentoAtuacao.java
  - Status.java
- **repository**
  - EmpreendimentoRepository.java
- **service**
  - EmpreendimentosApiApplication.java

> O backend utiliza **Spring Data JPA** para persistência dos dados e **H2 Database** em memória, facilitando testes locais sem necessidade de banco externo.

---

## Instruções para Execução

### 1. Clonar o repositório
```bash
git clone https://github.com/seu-usuario/empreendimentos-api.git
```

### 2.Entrar na pasta do projeto
```bash
cd empreendimentos-api
```
### 3.Executar a aplicação
com Maven:
```bash
mvn spring-boot:run
```
Ou rodando a classe principal:
```
EmpreendimentosApiApplication.java
```
# 4. Testar API

Base URL: http://localhost:8080

Endpoints disponíveis:

# POST  – Criar empreendimento

/empreendimento

Request:
```
{
  "nomeEmpreendimento": "Minha Empresa",
  "nomeResponsavel": "Rafael",
  "municipio": "Florianópolis",
  "segmentoAtuacao": "TECNOLOGIA",
  "email": "rafael@rafal.com.br",
  "status": "ativo"
}
```
Response (201 Created):
```
{
  "id": 1,
  "nomeEmpreendimento": "Minha Empresa",
  "nomeResponsavel": "Rafael",
  "municipio": "Florianópolis",
  "segmentoAtuacao": "TECNOLOGIA",
  "email": "rafael@rafal.com.br",
  "status": "ativo",
}
```


# GET  – Listar todos

/empreendimento

Response (200 OK):
```
[
  {
  "id": 1,
  "nomeEmpreendimento": "Minha Empresa",
  "nomeResponsavel": "Rafael",
  "municipio": "Florianópolis",
  "segmentoAtuacao": "TECNOLOGIA",
  "email": "rafael@rafal.com.br",
  "status": "ativo",
}
]
```

GET /empreendimento/{id} – Consultar por ID


# PUT – Atualizar

/empreendimento/{id}

Request:
```
{
  "nomeEmpreendimento": "Minha Empresa Atualizada",
  "status": "inativo"
}
```

Response (200 OK):
```
{
  "id": 1,
  "nomeEmpreendimento": "Minha Empresa Atualizada",
  "nomeResponsavel": "Rafael",
  "municipio": "Florianópolis",
  "segmentoAtuacao": "TECNOLOGIA",
  "email": "rafael@rafal.com.br",
  "status": "inativo"
}
```

# DELETE – Deletar

/empreendimento/{id}

Response (204 No Content):

Sem conteúdo de resposta, apenas confirma que deletou com sucesso.



___________________________________________________________________________________________________________________________________________

# Integração com API de Municípios (IBGE)

Para o preenchimento do campo **Município** no formulário de cadastro e edição de empreendimentos, a aplicação utiliza dados fornecidos pela API pública do **IBGE**.

A integração com o IBGE é realizada **no backend**, que é responsável por buscar os municípios do estado de Santa Catarina.

Essa abordagem foi adotada para centralizar a comunicação com APIs externas e permitir a implementação de **cache**, reduzindo a quantidade de requisições externas e melhorando o desempenho da aplicação.

### Fluxo de funcionamento

1. O **frontend** solicita a lista de municípios através da API do projeto.
2. O **backend** verifica se os dados estão disponíveis em cache.
3. Caso não estejam, o backend realiza uma requisição para a **API do IBGE**.
4. Os dados são armazenados em cache e retornados para o frontend.

### API externa utilizada

https://servicodados.ibge.gov.br/api/v1/localidades/estados/SC/municipios

### Exemplo de resposta

```json
[
  {
    "id": 4205407,
    "nome": "Florianópolis"
  },
  {
    "id": 4209102,
    "nome": "Joinville"
  }
]



_________________________________________________________________________________________________________________________________________________________________


# Frontend – Empreendimentos

## Descrição da Aplicação

O **Frontend de Empreendimentos** é uma aplicação desenvolvida em **Vue 3**, responsável pela interface de gerenciamento dos empreendimentos cadastrados na API.

A aplicação consome a **Empreendimentos API** e permite que o usuário realize operações de:

- Listagem de empreendimentos
- Cadastro de novos empreendimentos
- Edição de empreendimentos existentes
- Exclusão de registros

A interface foi desenvolvida utilizando **Vuetify**, proporcionando componentes visuais modernos e responsivos.

A comunicação com o backend é feita através de requisições HTTP utilizando **Axios**, consumindo os endpoints disponibilizados pela API.

---

# Tecnologias Utilizadas

- Vue 3  
- TypeScript  
- Vue Router  
- Pinia (Gerenciamento de Estado)  
- Vuetify  
- Axios  
- Vite  

---

# Estrutura Geral do Projeto

- **components**
  - ContainerDefault.vue

- **pages**
  - HomePage.vue
  - EmpreendimentoFormPage.vue

- **stores**
  - empreendimentos.js
  - municipios.js

- **services**
  - api.ts

- **router**
  - index.ts

- **composables**
  - useNotification.js
  - useApi.js

---

# Funcionalidades Implementadas

A aplicação possui as seguintes funcionalidades:

### Listagem de Empreendimentos

Exibe todos os empreendimentos cadastrados no sistema utilizando uma tabela.

### Cadastro de Empreendimento

Permite cadastrar um novo empreendimento informando:

- Nome do empreendimento
- Nome do responsável
- Município
- Segmento de atuação
- Email
- Status

### Edição de Empreendimento

Permite alterar informações de um empreendimento existente.

### Exclusão de Empreendimento

Permite remover um empreendimento através de um diálogo de confirmação.

---

# Integração com API

A aplicação consome os seguintes endpoints da API:

| Método | Endpoint | Descrição |
|------|------|------|
| GET | /empreendimento | Listar empreendimentos |
| GET | /empreendimento/{id} | Buscar empreendimento |
| POST | /empreendimento | Criar empreendimento |
| PUT | /empreendimento/{id} | Atualizar empreendimento |
| DELETE | /empreendimento/{id} | Excluir empreendimento |

---

# Instruções para Execução

## 1 – Instalar dependências

```bash
npm install

npm run dev
```

# Aplicação disponível em:
```
http://localhost:5173
```

##Comunicação com o Backend

Para que o frontend funcione corretamente, é necessário que a API backend esteja em execução.

#Por padrão, o frontend espera que a API esteja disponível em:
```
http://localhost:8080
```
##Considerações

#Este projeto foi desenvolvido como parte de um desafio técnico do SCTEC, com o objetivo de demonstrar conhecimentos:

Desenvolvimento de interfaces com Vue 3

Consumo de APIs REST

Gerenciamento de estado com Pinia

Organização de projetos frontend

Utilização de componentes visuais com Vuetify








