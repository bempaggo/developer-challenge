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

### **Testes**

Foram implementados testes unitários usando o framework JUnit 5

### **Novas Funcionalidades**

- O jogo notifica quando um movimento é inválido;
- O jogo informa quando a tecla pressionada é inválida

### **Correção de Bugs**

- Tentar fazer um movimento inválido com o tabuleiro na posião final não ativa mais o evento de finalizar o jogo

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

### _Contato_

- [LinkedIn](https://www.linkedin.com/in/pedro-aredes/)
- [Email](mailto:pedro.aredes@hotmail.com)