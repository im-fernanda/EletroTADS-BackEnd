[JAVA_BADGE]:https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[DOCKER_BADGE]: https://img.shields.io/badge/docker-257bd6?style=for-the-badge&logo=docker&logoColor=white
[ANGULAR_BADGE]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white

<h1 align="center" style="font-weight: bold;">EletroTADS 💻</h1>

![java][JAVA_BADGE]
![spring][SPRING_BADGE]
![docker][DOCKER_BADGE]
![angular][ANGULAR_BADGE]

<p align="center">
  <a href="#started">Getting Started</a> • 
  <a href="#routes">API Endpoints</a> 
</p>

<p align="justify">
  <b>Este projeto foi desenvolvido como parte da disciplina de Programação Web do curso de TADS da UFRN. O objetivo principal é demonstrar o uso do Spring Boot, juntamente com o Spring Security, utilizando Docker para a construção do contêiner do banco de dados.</b>
</p>

<h2> 📌 Funcionalidades</h2>
✔️ CRUD completo para todas as entidades <br>
✔️ Relacionamentos 1-1, 1-N e N-N <br>
✔️ Implementação de Soft Delete ou Logger para monitoramento de operações no banco de dados <br>
✔️ Uso adequado de verbos HTTP e status codes <br>
✔️ API seguindo RESTful nível 3 <br>
✔️ DTOs para request/response (criação, atualização e listagem) <br>
✔️ Endpoint de login <br>
✔️ Segurança aplicada a todos os endpoints com JWT ou OAuth2.0 (Stateless) <br>
✔️ Paginação nas consultas <br>
✔️ Interface para consumir a API para qualquer tabela do banco <br>

<h2 id="started">📖 Como executar </h2>

1. Crie um Banco de Dados no seu administrador de desenvolvimento para o PostgreSQL ou diretamente no contêiner Docker, criando assim o Banco de Dados inicial para testes. Com isso feito, siga os passos abaixo.
2. Clone o repositório e acesse a pasta do projeto:
  
       $ git clone im-fernanda/EletroTADS-BackEnd
       $ cd nome-da-sua-pasta

3. Crie um arquivo application.propertiers na raiz do projeto e insira suas credencias. Utilize como exemplo:
  ```yaml
  spring.datasource.url=jdbc:postgresql://localhost:5432/nome-do-banco
  spring.datasource.username=nome-do-usuario
  spring.datasource.password=senha-do-banco
  
  spring.datasource.driver-class-name=org.postgresql.Driver
  spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
  spring.jpa.hibernate.ddl-auto=update
  spring.servlet.multipart.enabled=true
  ```
5. Execute o projeto e abra localhost:8080;

<h2> 🔒 Autenticação </h2>
A aplicação utiliza JWT para autenticação. Para acessar os endpoints protegidos, é necessário obter um token através do endpoint de login.

<h2 id="routes">📍 API Endpoints</h2>

| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /listarEnderecos</kbd>     | acesso à homepage do site com acesso à listagem de endereços dos clientes
| <kbd>GET /cadastrarEndereco</kbd>     | acesso à página de cadastro de endereço

