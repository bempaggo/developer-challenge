package chat.gpt.modelos;

public class Jogo {

    public void iniciar() {
        Tabuleiro tabuleiro = new Tabuleiro();
        Regra regra = new RegraImpl();
        InterfaceGrafica interfaceGrafica = new InterfaceGrafica(tabuleiro, regra);
    }

    public void reiniciar(Tabuleiro tabuleiro) {
        tabuleiro = new Tabuleiro();
    }


}
