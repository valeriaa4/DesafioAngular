# Desafio Microservices 🐾

O sistema consiste em três microsserviços que se comunicam de modos síncrono e assíncrono permitindo o **cadastro de pets**, o **agendamento de cuidados** e o **envio de notificações via email** para o tutor do pet, a respeito dos agendamentos realizados.

## Sumário 🔗
1. [Estrutura dos Microsserviços](#estrutura-dos-microsserviços)
2. [Cadastro de Pets](#cadastro-de-pets-)
3. [Agendamento de Cuidados](#agendamento-de-cuidados-)
4. [Notificações](#notificações-)
5. [Como Executar o Projeto](#como-executar-o-projeto-)
6. [Ferramentas](#ferramentas-)
7. [Autenticação](#autenticação-)

## Estrutura dos Microsserviços 📂
* Eureka Server = Servidor
* Microsserviço de Cadastro de Pets = Cliente Eureka
* Microsserviço de Agendamento de Cuidados = Cliente Eureka
* Microsserviço de Notificações = Cliente Eureka
* Gateway = API Gateway

## Cadastro de Pets ✨

* Permite o cadastro de Pets (Cachorro/Gato), integrando com dados da *The Dog/Cat API**
* Estabelece comunicação síncrona e assíncrona com o Microsserviço de Agendamento.

***Métodos da API***

- POST - Cadastrar pet ⇒ `localhost:8080/pets`

- GET - Listar todos os pets ⇒ `localhost:8080/pets`

- GET - Listar pet pelo ID ⇒ `localhost:8080/pets/{id}`

- PUT - Atualizar dados de um pet ⇒ `localhost:8080/pets`

- DELETE - Excluir um pet ⇒ `localhost:8080/pets/{id}`

➕ Filtros Possíveis:

- GET - Filtrar pets por raça ⇒ `localhost:8080/pets/filtro-raca?raca={raca}`

- GET - Filtrar pets por especie ⇒ `localhost:8080/pets/filtro-especie?especie={Especie}`

***The Dog API*** 🐶

- GET - Listar todas as raças disponíveis ⇒ `localhost:8080/pets/dog-api-racas`

- GET - Retorna a imagem e dados da raça selecionada ⇒ `localhost:8080/pets/dog-api-imagens`

  Parâmetros da Pesquisa de Imagens:

    * ID da raça que deseja (encontrado na lista de raças disponíveis)
    * Chave de API para autenticação.

***The Cat API*** 😺

- GET - Listar todas as raças disponíveis ⇒ `localhost:8080/pets/cat-api-racas`

- GET - Retorna a imagem e dados da raça selecionada ⇒ `localhost:8080/pets/cat-api-imagens`

  Parâmetros da Pesquisa de Imagens:

    * ID da raça que deseja (encontrado na lista de raças disponíveis)
    * Chave de API para autenticação.

## Agendamento de Cuidados ✨

* Responsável por gerenciar todos os agendamentos dos Pets.

***Agendamento Automático*** 💻

* Realiza o agendamento de um cuidado ao receber o cadastro de um novo Pet.
* Estabelece comunicação assíncrona com o Microsserviço Cadastro de Pets, para efetuar o agendamento.
* Estabelece comunicação assíncrona com o Microsserviço de Notificações, a fim de enviar uma notificação via email confirmando o agendamento criado automaticamente.

***Agendamento Manual*** 📆

* Permite o agendamento de um cuidado específico de modo manual.
* Estabelece comunicação síncrona com o Microsserviço Cadastro de Pets, a fim de realizar a confirmação de dados antes de efetuar o agendamento.
* Estabelece comunicação assíncrona com o Microsserviço de Notificações, a fim de enviar uma notificação via email confirmando o agendamento solicitado.

***Métodos da API***

Agendamento Automático:

- GET - Listar todos os agendamentos ⇒ `localhost:8080/agendamento/automatico`

Agendamento Manual:

- POST - Cadastrar agendamento ⇒ `localhost:8080/agendamento`

- GET - Listar todos os agendamentos ⇒ `localhost:8080/agendamento/manual`

- PUT - Atualizar dados de um agendamento ⇒ `localhost:8080/agendamento`

- DELETE - Cancelar um agendamento ⇒ `localhost:8080/agendamento/{id}`

## Notificações ✨

* Responsável por enviar notificações via email quando um agendamento é criado.
* Estabelece comunicação assíncrona com o Microsserviço de Agendamento de Cuidados.

## Como Executar o Projeto ▶️

* Clone o projeto;
* Abra na IDE desejada;
*  Com o docker instalado em sua máquina, abra-o na pasta do projeto e realize os seguintes comandos:

    `cd docker`

    — Após entrar na pasta docker, vamos rodar o RabbitMQ através da docker compose configurada, com o seguinte comando:

    `docker compose up`

        Obs.: Desative a VPN para que não ocorra problemas com o Docker.

    — Com o RabbitMQ rodando, acesse-o através do seguinte link, com as seguintes credenciais:

    `http://localhost:15672`

    `Username: guest`

    `Password: guest`

- Rode os demais Microsserviços, sendo Eureka Server primeiro e Gateway por último.

    — Para acessar o Eureka Server utilize o seguinte link, com as seguintes credenciais:

  `http://localhost:8761`

  `Username: eurekaadmin`

  `Password: eurekaadmin`

## Ferramentas 🛠️

Linguagem de Programação: **Java**

Framework: **Spring Boot**

Comunicação Síncrona com **OpenFeign**

Comunicação Assíncrona com **RabbitMQ**

Envio de Email com **SMTP Gmail**

Integração com Banco de Dados **MySQL**

Configuração de servidor e clientes com **Eureka Server**

Documentação da API via **Swagger**:
* MS Cadastro de Pets: localhost:8081/swagger-ui.html
* MS Agendamento de Cuidados: localhost:8082/swagger-ui.html

Documentação da API via **Postman**:

* Arquivo anexado ao projeto, nomeado como: *"DesafioMicrosserviços.postman_collection.json"*

## Autenticação 🔒

***API keys:***
* ***Dog:*** live_2TOzjmriwyVn2JTUGp9w54BgN25kxZ1bjGBzV8rqtn3NTXGwkHkR4neFuJLbrsoC
* ***Cat:*** live_KvCSlFz8kQoJyhOwZnKwD1CBvsz63flQAyKsZOsVV247EC56aEWgBsZQSC8APrA4

