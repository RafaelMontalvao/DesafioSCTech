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

# POST /empreendimento – Criar empreendimento
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


# GET /empreendimento – Listar todos
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


# PUT /empreendimento/{id} – Atualizar

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

# DELETE /empreendimento/{id} – Deletar

Response (204 No Content):

Sem conteúdo de resposta, apenas confirma que deletou com sucesso.






