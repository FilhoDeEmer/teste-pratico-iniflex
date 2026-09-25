# Teste Prático - Iniflex

Projeto Java desenvolvido para o teste prático da Iniflex, com o objetivo de aplicar conceitos de orientação a objetos, herança, coleções e Streams para gerenciar dados de funcionários de uma indústria.

## Tecnologias utilizadas

- Java 25
- Maven
- Streams API
- BigDecimal (para valores monetários)

## Estrutura do projeto
src/main/java/br/com/iniflex/testepratico/
├── Pessoa.java # Classe base: nome e data de nascimento
├── Funcionario.java # Estende Pessoa: adiciona salário e função
└── Principal.java # Classe executora com todas as regras de negócio


## Funcionalidades implementadas

- [x] Inserção de todos os funcionários em uma lista
- [x] Remoção do funcionário "João" da lista
- [x] Impressão de todos os funcionários, com data no formato `dd/MM/aaaa` e valores monetários no formato brasileiro (ex: `9.836,14`)
- [x] Aumento de 10% no salário de todos os funcionários
- [x] Agrupamento dos funcionários por função em um `Map<String, List<Funcionario>>`
- [x] Impressão dos funcionários agrupados por função
- [x] Impressão dos funcionários aniversariantes nos meses 10 e 12
- [x] Identificação do funcionário com maior idade (nome e idade)
- [x] Impressão da lista de funcionários em ordem alfabética
- [x] Cálculo do total dos salários de todos os funcionários
- [x] Cálculo de quantos salários mínimos cada funcionário recebe (salário mínimo considerado: R$ 1.212,00)

## Como executar

Certifique-se de ter o **JDK 25+** e o **Maven** instalados.

Na raiz do projeto, execute:

```bash
mvn compile exec:java -Dexec.mainClass="br.com.iniflex.testepratico.Principal"
```

A saída de cada etapa é impressa no console.
## Decisões técnicas

- **BigDecimal** foi utilizado em vez de `double` para representar valores monetários, evitando problemas de precisão de ponto flutuante.
- **Streams** foram utilizadas para as operações de filtragem, agrupamento, ordenação e soma, priorizando um código mais declarativo e legível.
- A formatação de valores segue o padrão brasileiro (`NumberFormat` com `Locale pt-BR`), com ponto como separador de milhar e vírgula como separador decimal.
