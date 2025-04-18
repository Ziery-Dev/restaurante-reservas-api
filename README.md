# Reservas de Restaurante API 🍽️

API desenvolvida para gerenciamento de reservas em um restaurante, com funcionalidades completas para cadastro de clientes, mesas e reservas.

## 🔧 Tecnologias utilizadas

- **Java 17**
- **Spring Boot**
- **JPA / Hibernate**
- **MySQL**
- **MapStruct**
- **Maven**
- **Postman** (para testes de requisições HTTP)

## 📦 Funcionalidades

- Cadastro, listagem, atualização e exclusão de **clientes**.
- Cadastro, listagem, atualização e exclusão de **mesas**.
- Criação e gerenciamento de **reservas**, relacionando clientes e mesas.
- Validações de campos com mensagens personalizadas.
- Tratamento global de exceções.
- Separação de camadas: `Controller`, `Service`, `Repository`, `DTOs`, `Exception`, `Mapper`, entre outras.

## 📂 Organização do projeto

A estrutura do projeto segue os princípios de boas práticas do Spring Boot, com uso de DTOs para entrada e saída de dados, mapeamento entre entidades com MapStruct, e um sistema de tratamento global de erros.

## ✅ Próximas implementações

- Segurança com autenticação JWT
- Testes automatizados (unitários e de integração)
- Interface frontend para interação com a API
- Documentação com Swagger

