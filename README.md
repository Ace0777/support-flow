<img width="2264" height="1145" alt="{0E3CC5EF-549E-4CE3-8B2C-34EE5ACD6035}" src="https://github.com/user-attachments/assets/e2901779-69a3-4de8-a4cd-59323568d757" />


## Tecnologias Utilizadas

- **Spring Boot**: Framework Java para criação de aplicações web e microsserviços.
- **Spring Cloud**: Ferramentas para construção de sistemas distribuídos (Config Server, Service Discovery, etc.).
- **Spring Security**: Mecanismo de autenticação e autorização.
- **JWT (JSON Web Token)**: Para autenticação e autorização seguras entre serviços.
- **RabbitMQ**: Mensageria para comunicação assíncrona entre microsserviços.
- **MongoDB Atlas**: Banco de dados NoSQL na nuvem.
- **Docker**: Contêineres para empacotamento e deploy dos serviços.

---

## Estrutura do Projeto

O sistema é dividido em microsserviços independentes, organizados por responsabilidade:

### Componentes Principais

- **API Gateway**  
  Roteia e gerencia todas as requisições para os microsserviços, aplicando segurança e balanceamento.

- **Config Server**  
  Centraliza e distribui configurações para todos os serviços da aplicação.

- **Service Discovery (Eureka)**  
  Registra e descobre dinamicamente os microsserviços em execução.

- **Auth Service API**  
  Responsável pela autenticação de usuários e geração/validação de tokens JWT.

- **Commons Lib**  
  Biblioteca compartilhada entre os serviços, contendo DTOs, exceções, utilitários, etc.

- **User Service API**  
  Gerencia os dados dos usuários, como cadastro, edição e consulta.

- **Ticket Service API**  
  Gerencia os tickets de atendimento criados pelos usuários, permitindo rastreio, atualização e fechamento.

- **Email Service**  
  Responsável por enviar notificações por e-mail utilizando mensagens recebidas via RabbitMQ.

- **Log Service API**  
  Captura logs das requisições de todos os microsserviços através de um interceptor (middleware). Os dados são recebidos por mensageria (RabbitMQ) e persistidos no banco.

---

## Funcionalidades

-  Autenticação de usuários com JWT
-  Abertura e gerenciamento de tickets de atendimento
-  Envio assíncrono de e-mails via RabbitMQ
-  Registro centralizado de logs de requisições
-  Configuração centralizada e dinâmica
-  Descoberta automática de microsserviços
-  Arquitetura desacoplada e escalável via Docker

