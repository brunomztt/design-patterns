# Análise da classe UsuarioPrinter

## Análise crítica

O método `print` original funcionava, mas concentrava muitas decisões no mesmo bloco de código: validação da lista, escolha de tema, formatação dos campos, montagem da tabela e impressão no console.

Essa concentração deixava o método difícil de ler, testar e alterar. Uma mudança simples, como modificar a regra de CPF ou adicionar um novo tema, exigia entrar no meio do fluxo principal da impressão.

## Code Smells encontrados

- Método longo: o método `print` tinha muitas etapas diferentes no mesmo lugar.
- Muitas responsabilidades: a classe cuidava ao mesmo tempo de dados, formatação, validação e saída no console.
- Números e textos mágicos: tamanho da borda, espaçamento, formatos e mensagens estavam fixos no meio do código.
- Condicionais extensas: as regras de tema, nome, e-mail e CPF estavam misturadas no fluxo principal.
- Baixa testabilidade: a lógica de formatação estava acoplada diretamente ao `System.out`.
- Dependência de implementação: o método recebia `ArrayList`, quando poderia trabalhar com `List`.

## Princípios violados

- SRP: o método tinha mais de um motivo para mudar.
- OCP: novos formatos ou temas exigiriam alterar diretamente o método principal.
- DIP: o código dependia de uma implementação concreta de lista.
- Clean Code: faltavam métodos pequenos com nomes claros explicando cada etapa.

## Refatorações aplicadas

- Extração de métodos para formatar `id`, `nome`, `email` e `cpf`.
- Extração de métodos para montar cabeçalho, bordas, linha de usuário e impressão.
- Criação de constantes para remover números e textos mágicos.
- Uso de retorno antecipado para tratar lista nula ou vazia.
- Alteração do parâmetro de `ArrayList<Usuario>` para `List<Usuario>`.
- Uso de `String.repeat`, compatível com Java 17.

A saída gerada no console foi mantida com os mesmos dados, textos, bordas, máscara de CPF e alinhamento usados no exemplo original.
