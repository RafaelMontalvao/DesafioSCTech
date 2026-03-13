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

br.sc.sctec.empreendimentos_api
│
├── controller
│ └── EmpreendimentoController.java # Define os endpoints da API
│
├── dto
│ ├── EmpreendimentoRequest.java # DTO para criação
│ ├── EmpreendimentoEdicaoRequest.java # DTO para atualização
│ └── EmpreendimentoResponse.java # DTO para retorno das respostas
│
├── exception
│ ├── RegistroExistenteException.java # Exceção para registros duplicados
│ ├── RegistroNaoEncontradoException.java # Exceção para registros não encontrados
│ └── RestExceptionHandler.java # Handler global das exceções
│
├── model
│ ├── Empreendimento.java # Entidade principal
│ ├── ErroResponse.java # DTO para erros
│ ├── SegmentoAtuacao.java # Enum de segmento
│ └── Status.java # Enum de status
│
├── repository
│ └── EmpreendimentoRepository.java # Interface DAO para acesso ao banco
│
└── service
└── EmpreendimentosApiApplication.java # Classe principal da aplicação

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

POST /empreendimento – Criar empreendimento

GET /empreendimento – Listar todos

GET /empreendimento/{id} – Consultar por ID

PUT /empreendimento/{id} – Atualizar

DELETE /empreendimento/{id} – Deletar



