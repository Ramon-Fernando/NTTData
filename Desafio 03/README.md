# Desafio Técnico: Arquitetura de Microsserviços com Spring Boot

Este projeto implementa uma arquitetura de microsserviços para um sistema simples de catálogo de produtos e pedidos, utilizando Spring Boot e Spring Cloud. O objetivo é demonstrar conceitos de arquitetura moderna, como Service Discovery, API Gateway, comunicação entre serviços e persistência de dados.

![desafioTecnico](.desafioTecnico.png)

## Arquitetura Proposta

O sistema é composto por quatro serviços principais que trabalham de forma coesa para fornecer a funcionalidade completa da aplicação:

1.  **Service Discovery (Eureka Server)**: Atua como o "catálogo telefônico" da nossa arquitetura. Todos os outros serviços se registram aqui ao iniciar, permitindo que se encontrem dinamicamente na rede sem a necessidade de hardcoding de endereços IP e portas.
2.  **API Gateway**: É o ponto de entrada único para todas as requisições externas. Ele é responsável por rotear as chamadas para o microsserviço correto, além de centralizar responsabilidades transversais como segurança e autenticação.
3.  **Product Service**: Microsserviço responsável por gerenciar o catálogo de produtos. Ele possui seu próprio banco de dados e expõe uma API REST para operações CRUD de produtos.
4.  **Order Service**: Microsserviço que gerencia os pedidos. Ele se comunica com o Product Service para validar produtos e obter preços, e persiste os pedidos em seu próprio banco de dados, garantindo a autonomia e o desacoplamento dos serviços.

## Tecnologias Utilizadas

-   **Java 21**: Versão mais recente da linguagem Java.
-   **Spring Boot 3.5.4**: Framework principal para a criação dos microsserviços.
-   **Spring Cloud 2025.0.0**: Para integração e gerenciamento dos componentes da arquitetura.
    -   **Spring Cloud Netflix Eureka**: Para Service Discovery.
    -   **Spring Cloud Gateway**: Para implementação do API Gateway.
    -   **Spring Cloud OpenFeign**: Para comunicação declarativa e resiliente entre os microsserviços.
-   **Spring Data JPA**: Para persistência de dados.
-   **H2 Database**: Banco de dados em memória para fins de desenvolvimento e teste.
-   **Maven**: Para gerenciamento de dependências e build do projeto.
-   **Postman**: Para testes da API.

## Como Executar o Projeto

Para executar o projeto localmente, certifique-se de ter o Java 21 e o Maven instalados. Ao iniciar, os bancos de dados de ambos os serviços (`product-service` e `order-service`) serão populados automaticamente via scripts `data.sql`.

### 1. Ordem de Inicialização

A ordem em que os serviços são iniciados é **crucial** para o funcionamento da arquitetura. Siga a sequência abaixo:

1.  `service-discovery`
2.  `product-service`
3.  `order-service`
4.  `api-gateway`

Execute a classe principal `...Application.java` de cada módulo na sua IDE ou via linha de comando.

### 2. Verificando o Status

Após iniciar todos os serviços, você pode acessar o dashboard do Eureka para verificar se todos os serviços (`API-GATEWAY`, `PRODUCT-SERVICE`, `ORDER-SERVICE`) estão registrados:

-   **URL do Eureka Dashboard**: `http://localhost:8761/`

## Documentação da API

Todas as requisições devem ser feitas através do **API Gateway** e devem conter o header de autenticação.

-   **URL Base**: `http://localhost:8765`
-   **Header de Autenticação**:
    -   **Key**: `Authorization`
    -   **Value**: `Bearer super-secret-token`

### Microsserviço de Produtos (`/produtos`)

#### `POST /produtos`

Cria um novo produto no catálogo.

-   **Exemplo de Request Body (JSON):**
    ```json
    {
      "nome": "Happi Coat Oficial do AKB48 (Rosa)",
      "descricao": "Happi coat tradicional japonês com o logotipo do AKB48. Perfeito para eventos e festivais.",
      "preco": 55.00
    }
    ```

-   **Resposta de Sucesso (201 Created):**
    ```json
    {
        "id": 4,
        "nome": "Happi Coat Oficial do AKB48 (Rosa)",
        "descricao": "Happi coat tradicional japonês com o logotipo do AKB48. Perfeito para eventos e festivais.",
        "preco": 55.0
    }
    ```

#### `GET /produtos`

Lista todos os produtos cadastrados (populados via `data.sql`).

-   **Resposta de Sucesso (200 OK):**
    ```json
    [
        {
            "id": 1,
            "nome": "AKB48 Official Lightstick Neo 7th Generation",
            "descricao": "O mais recente lightstick oficial do AKB48, com 16 cores de LED e um design ergonômico. Essencial para qualquer show.",
            "preco": 45.0
        },
        {
            "id": 2,
            "nome": "Photocard Set - Heavy Rotation (3-pack)",
            "descricao": "Conjunto de 3 photocards aleatórios dos membros da seleção do single icônico \"Heavy Rotation\".",
            "preco": 8.5
        },
        {
            "id": 3,
            "nome": "Poster Oficial - 64th Single \"Hisashiburi no Lip Gloss\"",
            "descricao": "Poster oficial do 64º single do grupo, \"Hisashiburi no Lip Gloss\". Tamanho B2.",
            "preco": 12.0
        }
    ]
    ```

#### `GET /produtos/{id}`

Busca um produto específico pelo seu ID.

-   **Exemplo de URL:** `http://localhost:8765/produtos/2`

-   **Resposta de Sucesso (200 OK):**
    ```json
    {
        "id": 2,
        "nome": "Photocard Set - Heavy Rotation (3-pack)",
        "descricao": "Conjunto de 3 photocards aleatórios dos membros da seleção do single icônico \"Heavy Rotation\".",
        "preco": 8.5
    }
    ```

---

### Microsserviço de Pedidos (`/pedidos`)

#### `POST /pedidos`

Cria um novo pedido. O serviço irá buscar os preços atuais dos produtos no `product-service` para calcular o valor total.

-   **Exemplo de Request Body (JSON):**
    ```json
    {
      "items": [
        {
          "productId": 1,
          "quantity": 1
        },
        {
          "productId": 3,
          "quantity": 2
        }
      ]
    }
    ```

-   **Resposta de Sucesso (201 Created):**
    ```json
    {
        "orderId": 3,
        "moment": "2025-08-24T02:15:30.123456Z",
        "productNames": [
            "AKB48 Official Lightstick Neo 7th Generation",
            "Poster Oficial - 64th Single \"Hisashiburi no Lip Gloss\""
        ],
        "totalPrice": 69.0
    }
    ```

#### `GET /pedidos`

Lista todos os pedidos já realizados (populados via `data.sql`).

-   **Resposta de Sucesso (200 OK):**
    ```json
    [
        {
            "orderId": 1,
            "moment": "2025-08-24T02:10:00.123456Z",
            "productNames": [
                "AKB48 Official Lightstick Neo 7th Generation",
                "Photocard Set - Heavy Rotation (3-pack)"
            ],
            "totalPrice": 100.5
        },
        {
            "orderId": 2,
            "moment": "2025-08-24T02:12:00.123456Z",
            "productNames": [
                "Poster Oficial - 64th Single \"Hisashiburi no Lip Gloss\""
            ],
            "totalPrice": 36.0
        }
    ]
    