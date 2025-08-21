# 💻 NTT Data - Java e IA

## 📝 Desafio: Criando um aplicativo de controle de transações financeiras com POO

O objetivo do desafio é consolidar conceitos fundamentais da programação orientada a objetos (POO), como herança, encapsulamento, polimorfismo, abstração e reuso de código. A aplicação simula um sistema bancário básico que permite a criação de contas, depósitos, saques, transferências via PIX, criação de investimentos e acompanhamento de histórico de transações.

## 🚀 Como Rodar o Projeto

Para executar este projeto em sua máquina local, siga os passos abaixo.

### Pré-requisitos

Antes de começar, você precisará ter os seguintes softwares instalados:
* **Java Development Kit (JDK)**: Versão 11 ou superior.
* **Git**: Para clonar o repositório.

### Passo a Passo

1.  **Clone o Repositório**

    Abra seu terminal ou prompt de comando e clone o repositório para a sua máquina.
    ```bash
    git clone https://github.com/Ramon-Fernando/NTTData/tree/main/Desafio%2002
    ```

2.  **Navegue até a Pasta do Projeto**

    ```bash
    cd hangman
    ```

3.  **Execute a Aplicação**

    O projeto utiliza o **Gradle Wrapper**, que permite executá-lo sem a necessidade de ter o Gradle instalado globalmente no seu sistema.

    * **No Windows (usando o Prompt de Comando ou PowerShell):**
        ```bash
        .\gradlew.bat run
        ```

    * **No macOS ou Linux (usando o Terminal):**
        ```bash
        ./gradlew run
        ```
    Este comando irá baixar as dependências necessárias, compilar o código-fonte e iniciar a aplicação a partir da classe `Main`.

---

### Alternativa: Gerando e Executando o Arquivo `.jar`

Se preferir, você pode gerar um arquivo `.jar` executável:

1.  **Construa o projeto:**
    * No Windows:
        ```bash
        .\gradlew.bat build
        ```
    * No macOS/Linux:
        ```bash
        ./gradlew build
        ```

2.  **Execute o arquivo `.jar`:**
    Após o processo, o arquivo `.jar` estará localizado na pasta `build/libs/`. Você pode executá-lo com o seguinte comando (lembre-se de substituir `nome-do-arquivo.jar` pelo nome real do arquivo gerado):
    ```bash
    java -jar build/libs/nome-do-arquivo.jar
    ```