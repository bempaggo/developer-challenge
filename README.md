# Developer-Challenge


## Melhorias

_**Work In Progress**_

A aplicação original foi separada da seguinte forma:

Uma classe Main, responsável por iniciar a aplicação;
um pacote .model, que contém design das classes Botao, Tabuleiro e JogoDosOitos, responsável por regras de negócio e design das classes;
um pacote .view, com as classes JogoDosOitoGUI e Constantes (preciso mudar isso), responsável pela parte gráfica da aplicação;
um pacote .controller, com a interface TecladoImputListener, responsável por receber inputs do teclado e vincular a chamadas das classes responsáveis.

---

## To Do List
### Refatorar o código, diminuindo acomplamento e deletando funções inúteis (WIP)
Nessa etapa, foi aplicado princípios de SOLID e ideias de MVC para atingir o máximo de desacoplamento  possível, de forma a preparar o código para receber novas funcionalidades.

Optei por ainda não implementar testes nessa etapa, pois inicialmente eu nem conseguia entender o que o código fazia para testá-lo.

### Criar testes automáticos, usando JUnit como ferramenta  (TBD)

### Implementar novas funcionalidades (TBA)


