# Formulário de Envio de Currículos

Este é um projeto de exemplo para um formulário de envio de currículos usando Java Spring Boot. O formulário permite que os usuários enviem informações pessoais e anexem um currículo, que é então armazenado em um banco de dados e enviado por e-mail.

## Funcionalidades

### Formulário para envio de currículos com os seguintes campos:

- Nome
- E-mail
- Telefone
- Cargo Desejado (Campo de texto livre)
- Escolaridade (Campo de seleção)
- Observações
- Arquivo (para envio do currículo)

Observações:
- Apenas o campo "Observações" é opcional.
- Apenas arquivos com as extensões .doc, .docx ou .pdf são aceitos.
- Tamanho máximo do arquivo: 1MB.


## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.3.1
  - Spring MVC
  - Spring Data JPA
  - Spring Mail
  - Spring Validation
- Thymeleaf
- Bootstrap
- PostgreSQL
- H2 Database (para testes)
- Maven

## Requisitos para Execução

- JDK 21 ou superior
- Maven
- PostgreSQL (para produção)

## Instalação

1. Clone o repositório:
    ```sh
    git clone https://github.com/Roberto-Vinicius/UGTSIG.git
    ```

2. Navegue até o diretório do projeto:
    ```sh
    cd UGTSIC
    ```
    - ache a o arquivo:
      
      application.properties


3. Configure o banco de dados MySQL no arquivo `application.properties`.
 - localizado em ``:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5431/seubancodedados    # Lembre-se de criar um banco com esse nome
    spring.datasource.username=seuusuario                                     # Lembre-se, este é o seu usuário do Postgre
    spring.datasource.password=suasenha                                       # Lembre-se, esta é a sua senha do Postgre
    spring.jpa.hibernate.ddl-auto=update
    ```

    Caso não tenha PostgreSQL, Fique à vontade para usar o H2 Database. Ele só guarda informações até o fim da aplicação. Para usar, apenas descomente as linhas referentes a ele e comente as linhas do MySQL, conforme mostrado na imagem:

5. Compile e execute o projeto:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

6. Abra seu navegador e acesse:
    ```sh
    http://localhost:8080
    ```

## Armazenamento dos Dados no Banco de Dados

### Usando pgAdmin:

1. Abra o pgAdmin.
2. Conecte-se ao servidor PostgreSQL.
3. No painel esquerdo, expanda a seção "Databases" para visualizar os bancos de dados disponíveis.
4. Encontre e clique no banco de dados que você criou (por exemplo, seubancodedados).
5. Você verá duas tabelas: curriculo e email_model.
6. Para visualizar as informações do currículo no banco de dados, abra uma nova query e digite:
    ```sql
    SELECT * FROM curriculo;
    ```
   para visualizar as informações do currículo no banco de dados.


### Envio de e-mail com os dados do formulário e o arquivo anexado:

6.1 Selecione uma nova query e digite:
  ```sql
    SELECT * FROM email_model;
  ```


### Usando o terminal psql:

1. Conecte-se ao PostgreSQL::
    ```sh
    psql -U seuusuario -d postgres
    ```
2. Crie o banco de dados:
    ```sql
    CREATE DATABASE seubancodedados;
    ```
3. Use o banco de dados:
    ```sql
    \c seubancodedados;
    ```
