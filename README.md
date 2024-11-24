# Sistema de Gerenciamento de Consultas

Este app foi desenvolvido utilizando **Spring 3** no back-end, **MySQL** como banco de dados e **Android Studio** para o desenvolvimento do front-end. Ele permite o gerenciamento de consultas médicas por meio de operações de CRUD (Criar, Ler, Atualizar e Deletar).


## Funcionalidades
- **Agendamento de Consultas**: Permite agendar e listar consultas.
- **Médicos**: Permite cadastrar, listar, editar e excluir médicos.
- **Pacientes**: Permite registrar, listar, editar e excluir dados dos pacientes.


## Tecnologias Utilizadas
- Back-end: Spring Framework 3
- Front-end: Android Studio
- Banco de Dados: MySQL
- IDEs: IntelliJ, Android Studio

# Como Executar o Projeto
Para executar o aplicativo, você precisa seguir os passos abaixo, tanto para o back-end quanto para o front-end.

1. Clone o repositório:

```bash
    git clone https://github.com/Jessicabm61/app-otes06.git
```
## Back-End

1. Configure o arquivo ``application.properties`` conforme abaixo:
```bash
spring.application.name=scheduling
spring.datasource.url=jdbc:mysql://localhost:3306/vollmed_api?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
2. Build e Execução do Back-End: Utilize o Maven ou Gradle para compilar e executar o back-end:
```bash
mvn spring-boot:run
```

## Front-End (Android Studio)

1. Após abrir o projeto no Android Studio, o Gradle irá automaticamente baixar as dependências configuradas no arquivo build.gradle. Caso isso não aconteça automaticamente, você pode forçar o download das dependências com o seguinte comando no terminal do Android Studio:
```bash
./gradlew build
 ```
2. Configuração do arquivo RetrofitClient.java: Adapte a URL base para o IP ou domínio onde seu back-end está rodando. No arquivo RetrofitClient.java, defina a URL do servidor:
```bash
private static final String BASE_URL = "http://IP:8080/";
```
3. Execução do Front-End: Após configurar o Retrofit e o back-end, execute o aplicativo no Emulador Android ou em um dispositivo físico.

# Dependências

## Back-End
- Spring Boot Starter Data JPA: Para integração com banco de dados usando JPA/Hibernate.
- Spring Boot Starter Web: Para criar e gerenciar a API REST.
- Flyway Core e Flyway MySQL: Para versionamento e migração de banco de dados.
- Spring Boot DevTools: Para facilitar o desenvolvimento com hot-reload.
- MySQL Connector: Driver para conexão com o banco de dados MySQL.
- Lombok: Para reduzir o código boilerplate com anotações como @Getter, @Setter, etc.
- Spring Boot Starter Validation: Para validações no back-end.
- Spring Boot Starter Test: Para criação de testes unitários e de integração.

## Front-End (Android Studio)
- implementation(libs.appcompat): Para compatibilidade com versões mais antigas do Android.
- implementation(libs.material): Para utilizar componentes de UI do Material Design.
- implementation(libs.activity): Para facilitar o gerenciamento do ciclo de vida das atividades.
- implementation(libs.constraintlayout): Para criar layouts dinâmicos e flexíveis.
- implementation("com.squareup.retrofit2:retrofit:2.11.0"): Para realizar requisições HTTP.
- implementation("com.squareup.retrofit2:converter-gson:2.11.0"): Para converter as respostas JSON da API em objetos Java/Kotlin.

## End-Points Disponíveis:
1. Criar e listar Agendamentos, Pacientes e CRUD de Médicos.
2. Sufixo padrão para os end-points: `http://localhost:8080`

### 1. Agendamento

### Criar um agendamento
- **URL**: POST | /agendar
- **Descrição**: Este endpoint cria um agendamento de consulta entre um médico e um paciente, especificando a data e hora da consulta.

### Listar todos os agendamentos
- **URL**: GET | /agendar
- **Descrição**: Este endpoint retorna todos os agendamentos de consultas, com informações sobre o médico, o paciente e a data/hora da consulta.

### 2. Pacientes

### Criar um Paciente
- **URL**: POST | /pacientes
- **Descrição**: Este endpoint cria um novo paciente, com informações pessoais e endereço.

### Listar todos os Pacientes
- **URL**: GET | /pacientes
- **Descrição**: Este endpoint lista todos os pacientes cadastrados, com informações pessoais e de contato.

### Atualizar dados de um Paciente
- **URL**: PUT | /pacientes/{id}
- **Descrição**: Este endpoint atualiza os dados de um paciente específico, utilizando o seu `id` na URL.

### Excluir um Paciente
- **URL**: DELETE | /pacientes/{id}
- **Descrição**: Este endpoint exclui um paciente específico, utilizando o seu `id` na URL.

### 3. Médicos

### Criar um Médico
- **URL**: POST | /medicos
- **Descrição**: Este endpoint cria um novo médico, com informações pessoais, especialidade, CRM e contato.

### Listar todos os Médicos
- **URL**: GET | /medicos
- **Descrição**: Este endpoint lista todos os médicos cadastrados, com informações de nome, especialidade, CRM e contato.

### Atualizar dados de um Médico
- **URL**: PUT | /medicos/{id}
- **Descrição**: Este endpoint atualiza os dados de um médico específico, utilizando o seu `id` na URL.

### Excluir um Médico
- **URL**: DELETE | /medicos/{id}
- **Descrição**: Este endpoint exclui um médico específico, utilizando o seu `id` na URL.

## Vídeo de Demonstração

Confira o vídeo de demonstração do app:

[Assista ao vídeo de demonstração](https://www.youtube.com/watch?v=ZCLGYL7RdO0)
