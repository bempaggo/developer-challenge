package chat.gpt.src;

public class Jogo {

    public void iniciar() {
        Tabuleiro tabuleiro = new Tabuleiro(3);
        Regra regra = new RegraImpl();
        new InterfaceGrafica(tabuleiro, regra);
    }
}
