# Nome do Projeto

Jogo da Velha

# Sistema

O jogo da velha é um sistema desenvolvido em Spring Boot, sendo composto por um banco em memória.

## H2

Banco em memória usado para salvar os dados da partida.

## Controller

Controller é a camada expoê os endpoints que recebem as requisições e entra para o a camada de Service.

## Service 

Service é a camada que contém todas as regras de negócio do jogo. Dentro dessas classes estarão todas as regras de negócio e manipulação de objetos para executar as operações requisitadas à API. Essas classes são sufixadas Service.

## Repository

O repository são os objetos de acesso a banco, onde estão feitas as queries para serem executadas ou utilizando as implementações disponíveis pelo framework no caso de persistência e também de consultas. Essas classes são sufixadas Repository.

## DTO

Os DTOs são (ou deveriam ser), a parte da visão das entidades que deve ser transferida para cada funcionalidade. A ideia é que essas classes sejam a mais enxuta possível. Apenas essas entidades são enviadas para fora da aplicação. Essas classes são sufixadas com a palavra DTO.


# Frameworks


## Spring Data JPA, JPA e Hibernate

O projeto foi construído utilizando esse framework do Spring que facilita a persistência e a construção de queries simples dentro do projeto. Além dele, o JPA, mais especificamente o Hibernate são os responsáveis pelo mapeamento, persistência, consulta entre outros pontos relacionados com a integração com o banco de dados. 


## Spring Boot

Spring Boot é um framework para criação de forma rápida de uma aplicação web contendo um servlet embutido.


### Serviço:

O serviço é compilado com o maven

```
clean install
```



