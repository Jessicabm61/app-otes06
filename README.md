# Sistema de Gerenciamento de Consultas

Este é um sistema de gerenciamento de consultas médicas, desenvolvido com **Spring 3** no back-end e **Android Studio** no front-end. O sistema permite o agendamento de consultas médicas e o gerenciamento de médicos e pacientes, utilizando um banco de dados **MySQL** para armazenar as informações.

## Funcionalidades
- **Agendamento de Consultas:** Permite agendar consultas, com a funcionalidade de listar todos os agendamentos realizados.
- **CRUD de Médicos:** Permite cadastrar, listar, editar e excluir médicos.
- **CRUD de Pacientes:** Permite registrar, listar e editar dados dos pacientes.

## Tecnologias Utilizadas
- **Back-end:** Spring Framework 3, Spring Boot
- **Front-end:** Android Studio, Retrofit
- **Banco de Dados:** MySQL
- **IDE's:** IntelliJ (para back-end), Android Studio (para front-end)
- **Ferramentas de Versionamento:** Git e GitHub

## Dependências

### No projeto **Back-end** (Spring):
- **Spring Boot Starter Data JPA:** Para integração com banco de dados usando JPA/Hibernate.
- **Spring Boot Starter Web:** Para criação e gerenciamento da API REST.
- **Flyway Core e Flyway MySQL:** Para versionamento e migração de banco de dados.
- **Spring Boot DevTools:** Para facilitar o desenvolvimento com hot-reload.
- **MySQL Connector:** Driver para conexão com o banco de dados MySQL.
- **Lombok:** Para reduzir o código boilerplate com anotações como `@Getter`, `@Setter`, etc.
- **Spring Boot Starter Validation:** Para validações no back-end.
- **Spring Boot Starter Test:** Para criação de testes unitários e de integração.

### No projeto **Front-end** (Android):
- **Retrofit:** Para consumir a API REST e fazer as requisições HTTP.
- **AppCompat, Material Design:** Para garantir uma interface amigável e moderna.
- **ConstraintLayout:** Para layouts flexíveis e responsivos.

---

# Configuração do Banco de Dados (Back-end)

Configure o banco de dados MySQL em seu ambiente local.

Altere o arquivo `application.properties` para definir as configurações do banco de dados. Exemplo:

```properties
spring.application.name=scheduling
spring.datasource.url=jdbc:mysql://localhost:3306/vollmed_api?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

1. **Clone o repositório do projeto:**
   
   Clone o repositório usando o seguinte comando:

   ```bash
   git clone https://github.com/heloisacst/app-otes06.git

End-points Disponíveis
1. Agendamento de Consultas
Criar um Agendamento:
URL: POST | /agendar
Exemplo de requisição:

json
Copiar código
{
  "idMedico": 1,
  "idPaciente": 2,
  "dataHoraConsulta": "2024-12-01T15:00:00"
}
Listar Todos os Agendamentos:
URL: GET | /agendar
2. CRUD de Pacientes
Criar um Paciente:
URL: POST | /pacientes
Exemplo de requisição:

json
Copiar código
{
  "pacte_nome": "João da Silva",
  "pacte_email": "joao.silva@gmail.com",
  "pacte_telefone": "47999999999",
  "endereco": {
      "logradouro": "Rua das Palmeiras",
      "bairro": "Flores",
      "cep": "12345678",
      "cidade": "Brasília",
      "uf": "DF",
      "numero": "589",
      "complemento": "Casa 1"
  }
}
3. CRUD de Médicos
Criar um Médico:
URL: POST | /medicos
Exemplo de requisição:

json
Copiar código
{
  "nome": "Dr. João",
  "crm": "123456",
  "especialidade": "Cardiologista",
  "telefone": "4799999999",
  "email": "dr.joao@medico.com"
}
Listar Médicos:
URL: GET | /medicos
Atualizar Médico:
URL: PUT | /medicos/{id}
Exemplo de requisição:

json
Copiar código
{
  "nome": "Dr. João Silva",
  "crm": "123456",
  "especialidade": "Cardiologista",
  "telefone": "4799999999",
  "email": "dr.joao@medico.com"
}
Excluir Médico:
URL: DELETE | /medicos/{id}
Front-End (Android)
O front-end foi desenvolvido utilizando Android Studio. O Retrofit foi utilizado para integrar a aplicação Android com a API REST do back-end. O usuário pode interagir com a aplicação para realizar o cadastro de médicos, pacientes e agendamentos, bem como listar e editar essas informações.

Como Rodar a Aplicação
1. Back-End (Spring Boot)
Abra o projeto back-end na IDE IntelliJ ou qualquer outra IDE que suporte Java e Spring.
Execute a aplicação Spring Boot.
A API estará disponível no endereço padrão: http://localhost:8080.
2. Front-End (Android)
Abra o projeto no Android Studio.
Configure o IP da máquina no Retrofit para apontar para o local da API.
Compile e execute o aplicativo Android no seu dispositivo ou emulador.
Vídeo de Demonstração
Para demonstrar a funcionalidade do aplicativo, acesse o link abaixo para visualizar o vídeo:

Link para o vídeo de demonstração

Conclusão
Esse sistema oferece um gerenciamento completo de consultas médicas, com cadastro de médicos, pacientes e agendamento de consultas, tudo conectado a um banco de dados MySQL. O front-end é simples e intuitivo, proporcionando uma boa experiência de usuário. A API foi desenvolvida com Spring Boot e a aplicação Android consome essa API utilizando Retrofit.
