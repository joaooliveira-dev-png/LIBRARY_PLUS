# LibraryPlus

Sistema desktop para gerenciamento de uma biblioteca, desenvolvido em Java com interface gráfica Swing e acesso a banco de dados relacional.

## 🎯 Objetivo

O LibraryPlus tem como objetivo auxiliar no gerenciamento de uma biblioteca, permitindo controlar livros, usuários, funcionários e empréstimos.

O projeto também foi desenvolvido como prática de conceitos de desenvolvimento de software, incluindo programação orientada a objetos, JDBC, arquitetura em camadas, Git e GitHub.

## 🛠️ Tecnologias utilizadas

- Java 21
- Java Swing
- JDBC
- MySQL / MariaDB
- NetBeans
- Git
- GitHub

## 📚 Funcionalidades

## Desenvolvedor
- João Vitor

### Autenticação
- Login de funcionários
- Validação de usuário e senha
- Controle de sessão
- Encerramento de sessão

### Livros
- Cadastro de livros
- Listagem de livros
- Busca por ID
- Atualização de livros
- Exclusão de livros

### Funcionários
- Cadastro de funcionários
- Cadastro de usuário e senha junto ao funcionário
- Listagem de funcionários
- Busca por ID
- Atualização de funcionários
- Exclusão de funcionários

### Usuários
- Listagem de usuários
- Busca por ID
- Autenticação no sistema

### Empréstimos
- Registro de empréstimos
- Associação entre usuário, livro e funcionário
- Controle da quantidade disponível de livros
- Busca de empréstimos por ID
- Listagem de empréstimos
- Atualização de empréstimos

### Relatórios
- Consulta de empréstimos por período

## 🏗️ Arquitetura

O projeto utiliza uma organização em camadas:

```text
src/
├── controller/
│   ├── EmprestimoController.java
│   ├── FuncionarioController.java
│   ├── LivroController.java
│   ├── RelatorioController.java
│   └── UsuarioController.java
│
├── dao/
│   ├── EmprestimoDAO.java
│   ├── FuncionarioDAO.java
│   ├── LivroDAO.java
│   └── UsuarioDAO.java
│
├── model/
│   ├── Emprestimo.java
│   ├── Funcionario.java
│   ├── Livro.java
│   ├── Relatorio.java
│   └── Usuario.java
│
├── util/
│   ├── Conexao.java
│   └── Sessao.java
│
└── view/
    ├── Telas de cadastro
    ├── Telas de listagem
    ├── TelaLogin.java
    ├── TelaMenu.java
    └── TelaRelatorio.java
