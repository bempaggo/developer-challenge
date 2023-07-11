# Developer-Challenge Estágio

Esse projeto é a minha versão para o desafio de código da [BemPaggo](https://www.bempaggo.com.br/), disponível [aqui](https://github.com/bempaggo/developer-challenge), pleiteando a vaga de estagiário.

O projeto consiste em um jogo de tabuleiro com uma interface gráfica, em que o objetivo é organizar as peças de forma a criar uma matriz sequêncial (mais detalhes [aqui](https://github.com/JuanCampos1300/Jogo-dos-Oito-8-puzzle-)). 

Originalmente, temos todo o código dentro da mesma classe, com métodos ociosos e um acoplamento muito grande entre as competências do código. A abordagem para refatoração, de modo a deixar o código mais limpo, foi de dividir o projeto em diferentes pacotes e classes, seguindo a arquitetura MVC [(Model-View-Controller)](https://awari.com.br/arquitetura-mvc/?utm_source=blog&utm_campaign=projeto+blog&utm_medium=Como%20funciona%20a%20arquitetura%20MVC%20no%20desenvolvimento%20de%20software) e os princípios de [SOLID](https://www.treinaweb.com.br/blog/principios-solid-single-responsability-principle/#:~:text=Os%20princ%C3%ADpios%20SOLID%20s%C3%A3o%20cinco%20princ%C3%ADpios%20de%20design,do%20c%C3%B3digo%20aos%20princ%C3%ADpios%20da%20orienta%C3%A7%C3%A3o%20a%20objetos.), de modo a tornar o código legível, testável e escalável, bem como tornar a manutenção mais simples e todo o código mais fácil de entender.

## Melhorias

_**Work In Progress**_

### **Arquitetura**

A aplicação original foi separada da seguinte forma:

- Pacote .application, responsável por iniciar a aplicação;
- Pacote .builder, responsável por construir a aplicação criando as instâncias e injetando dependências
- Pacote .model, responsável por regras de negócio e design das classes;
- Pacote .view, responsável pela parte gráfica da aplicação;
- Pacote .controller, responsável por receber inputs e fazer as chamadas necessárias;
- Pacote .util, para armazenar constantes e classes utilitárias;
- Pacote .exception, com exceções personalizadas para lidar com cenários atípicos;

Com essa arquitetura, é esperado uma divisão clara entre as responsabilidades de cada parte do projeto, o que permite um entendimento rápido e acessível sobre cada funcionalidade, bem como modificações fáceis e modularizadas

A estratégia para movimentação pelo teclado foi definir uma lista L = [1,2,3,...,f. L + 0], sendo f a quantidade de elementos da lista, então definir as trocas de posição simulando coordenadas cartesianas com base no índice de 0, na raiz de n e no input do usuário de forma a emular a movimentação num tabuleiro; é plenamente funcional para tabuleiros n por n sendo n maior ou igual a 3, testado até 10. É de bom tom citar que o botão reiniciar vai ficar deslocado do centro em qualquer configuração diferente de 3x3. 

A complexidade assintótica de qualquer troca é linear.

Jogar com o mouse foi implementado; para tanto basta clicar em um botão adjacente ao botão vazio e ele ocupará o lugar do botão vazio.

### **Testes**

Usei JUnit 5 e Mockito para implementar os testes de unidade. Como fui para uma estratégia de implementar interfaces para tudo para fazer a injeção de dependências sem uso de framework, fica um pouco complicado de testar sem basicamente criar um mock com classe anônima, por isso optei pelo Mockito.

Há testes bem simples para validar comportamentos básicos dos métodos das camadas mais baixas. Não houve nenhum teste indireto específico para métodos privados.

### **Novas Funcionalidades**

- Agora é possível jogar com o mouse ou teclado;
- O jogo notifica quando você ganhou;
- O jogo informa quando a tecla pressionada é inválida;
- É possível modificar o jogo para o tabuleiro começar numa posição aleatória. Para isso, é necessário modificar o valor de RANDOM_GRID no arquivo [GridConstants](jogo-oito/src/main/java/util/GridConstants.java) (coloque "true" para aleatório e "false" para o tabuleiro na ordem, sem aspas);
- é possível configurar tamanhos personalizados para o tabuleiro. Para isso, é necessário modificar o valor de GRID_WIDTH no arquivo [GridConstants](jogo-oito/src/main/java/util/GridConstants.java) (coloque qualquer valor inteiro igual ou maior que 3);

### **Correção de Bugs**

- Tentar fazer um movimento inválido com o tabuleiro na posição final não ativa mais o evento de finalizar o jogo
- Clicar em um botão não seleciona mais o texto do botão
- Clicar em um botão não impede mais que o usuário continue jogando com o teclado
- O botão reiniciar agora reinicia corretamente o tabuleiro. É importante mencionar que, ao clicar no botão reiniciar com o tabuleiro na posição inicial quando o modo aleatório está desativado, embora aparentemente não atualize visualmente o tabuleiro, a lista que o define é reiniciada; isso pode ser verificado no modo aleatório.

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
- [JUnit 5](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)

### _Contato_

- [LinkedIn](https://www.linkedin.com/in/pedro-aredes/)
- [Email](mailto:pedro.aredes@hotmail.com)