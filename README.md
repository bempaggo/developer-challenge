# Developer-Challenge Estágio

Esse projeto é a minha versão para o desafio de código da [BemPaggo](https://www.bempaggo.com.br/), disponível [aqui](https://github.com/bempaggo/developer-challenge), pleiteando a vaga de estagiário.

O projeto consiste em um jogo de tabuleiro com uma interface gráfica, em que o objetivo é organizar as peças de forma a criar uma matriz sequêncial (mais detalhes [aqui](https://github.com/JuanCampos1300/Jogo-dos-Oito-8-puzzle-)). 

Originalmente, temos todo o código dentro da mesma classe, com métodos ociosos e um acoplamento muito grande entre as competências do código. A abordagem para refatoração, de modo a deixar o código mais limpo, foi de dividir o projeto em diferentes pacotes e classes, seguindo a arquitetura MVC [(Model-View-Controller)](https://awari.com.br/arquitetura-mvc/?utm_source=blog&utm_campaign=projeto+blog&utm_medium=Como%20funciona%20a%20arquitetura%20MVC%20no%20desenvolvimento%20de%20software) e os princípios de [SOLID](https://www.treinaweb.com.br/blog/principios-solid-single-responsability-principle/#:~:text=Os%20princ%C3%ADpios%20SOLID%20s%C3%A3o%20cinco%20princ%C3%ADpios%20de%20design,do%20c%C3%B3digo%20aos%20princ%C3%ADpios%20da%20orienta%C3%A7%C3%A3o%20a%20objetos.), de modo a tornar o código legível, testável e escalável, bem como tornar a manutenção mais simples e todo o código mais fácil de entender.

## Melhorias

_**Work In Progress**_

### **Arquitetura**

A aplicação original foi separada da seguinte forma:

- Pacote .application, responsável por iniciar a aplicação;
- Pacote .model, responsável por regras de negócio e design das classes;
- Pacote .view, responsável pela parte gráfica da aplicação;
- Pacote .controller, responsável por receber inputs e fazer as chamadas necessárias;
- Pacote .util, para armazenar constantes e evitar números mágicos;
- Pacote .exception, com exceções personalizadas para lidar com cenários atípicos;

Com essa arquitetura, é esperado uma divisão clara entre as responsabilidades de cada parte do projeto, o que permite um entendimento rápido e acessível sobre cada funcionalidade, bem como modificações fáceis e modularizadas

A estratégia para movimentação pelo teclado foi:
 - na classe [Grid](jogo-oito/src/main/java/chat/gpt/model/Grid.java) definir uma lista ordenada de inteiros como L = [1, 2, 3, ..., n, n + 0], onde n é o tamanho da lista;
 - na classe [GameService](jogo-oito/src/main/java/chat/gpt/controller/GameService.java) definir os algoritmos de manipulação do elemento inteiro 0 com base na sua posição na lista e na raiz do tamanho da lista (funciona para qualquer lista tal que o tamanho dela tenha uma raiz positiva inteira);
 - na classe [GameController](jogo-oito/src/main/java/chat/gpt/controller/GameController.java), usar um observer para o teclado e, com base na tecla direcional pressionada pelo usuário, chamar o método correspondente na classe Game;
- Um detalhe é que, embora visualmente se movimento um botão numerado para o lugar vazio (representado por 0), a referência é o elemento vazio; portanto, a lógica da movimentação pode parecer espelhada (moveUp "move" o zero para baixo e assim por diante) já que o método é nomeado com base no movimento que o usuário deseja fazer
- É importante citar que a escolha do tipo List<Integer\> serve para reduzir a quantidade de código digitado e permite que eu reutilize a lista para dar nomes aos botões; embora seja perfeitamente possível aplicar a mesma lógica com qualquer coleção<T\> indexável, no contexto eu só preciso de inteiros (e não quero criar instância de coleção imutável para cada input no teclado).

Jogar com mouse carece de implementação
  

### **Testes**

### **Novas Funcionalidades**

- O jogo notifica quando você ganhou;
- O jogo informa quando a tecla pressionada é inválida;
- É possível modificar o jogo para o tabuleiro começar numa posição aleatória. Para isso, é necessário modificar o valor de RANDOM_GRID no arquivo [GridConstants](jogo-oito/src/main/java/chat/gpt/util/GridConstants.java) (coloque "true" para aleatório e "false" para o tabuleiro na ordem, sem aspas)

### **Correção de Bugs**

- Tentar fazer um movimento inválido com o tabuleiro na posição final não ativa mais o evento de finalizar o jogo
- Clicar em um botão não seleciona mais o texto do botão
- Clicar em um botão não impede mais que o usuário continue jogando com o teclado
- O botão reiniciar agora reinicia corretamente o tabuleiro. É importante mencionar que, ao clicar no botão reiniciar com o tabuleiro na posição inicial quando o modo aleatório está desativado, embora aparentemente não atualize visualmente o tabuleiro, a lista que o define é reiniciada

---

### _Ambiente e Ferramentas usadas_

- [Git](https://git-scm.com/)
- [Visual Studio Code](https://code.visualstudio.com/docs)
- [Windows Subsystem for Linux](https://learn.microsoft.com/pt-br/windows/wsl/about)
- [ArchWSL](https://github.com/yuk7/ArchWSL)
- [Windows Terminal](https://github.com/microsoft/terminal)
- [Zsh](https://www.zsh.org/)
- [asdf](https://asdf-vm.com/)
- [OpenJDK-18](https://openjdk.org/)

### _Contato_

- [LinkedIn](https://www.linkedin.com/in/pedro-aredes/)
- [Email](mailto:pedro.aredes@hotmail.com)