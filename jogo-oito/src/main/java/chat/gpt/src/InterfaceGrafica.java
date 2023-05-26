package chat.gpt.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class InterfaceGrafica extends JFrame {

    private final Tabuleiro tabuleiro;
    private final Regra regra;
    private final Map<JButton, Posicao> posicaoBotaoMap = new HashMap<>();

    public InterfaceGrafica(Tabuleiro tabuleiro, Regra regra) {
        super("Jogo dos oito");

        this.tabuleiro = tabuleiro;
        this.regra = regra;

        inicializarInterfaceGrafica();
    }

    private void inicializarInterfaceGrafica() {
        criarJanela();
        criarBotoes();
        ativarInterfaceGrafica();
    }

    private void criarBotoesDePecas() {
        for (int i = 0; i < tabuleiro.getDimensao(); i++) {
            for (int j = 0; j < tabuleiro.getDimensao(); j++) {
                posicaoBotaoMap.put(criarBotao(), new Posicao(i, j));
            }
        }
    }

    private JButton criarBotao() {
        JButton botao = new JButton();
        botao.setFont(new Font("Arial", Font.BOLD, 36));
        botao.addMouseListener(getMouseListener());
        add(botao);
        return botao;
    }


    private void criarBotoes() {
        criarBotoesDePecas();
        criarBotaoEmbaralhar();
        criarBotaoReinicar();
    }

    private void criarBotaoReinicar() {
        JButton botaoReiniciar = new JButton("Reiniciar");
        botaoReiniciar.addActionListener(e -> reiniciarJogo());
        add(botaoReiniciar);
        add(new JLabel(""));
    }

    private void criarBotaoEmbaralhar() {
        JButton botaoEmbaralhar = new JButton("Embaralhar");
        botaoEmbaralhar.addActionListener(e -> embaralharJogo());
        add(botaoEmbaralhar);

    }

    private void ativarInterfaceGrafica() {
        setFocusable(true);
        atualizarInterfaceTabuleiro();
        setVisible(true);
    }

    private void embaralharJogo() {
        regra.embaralharPecas(tabuleiro);
        atualizarInterfaceTabuleiro();
    }

    private void reiniciarJogo() {
        regra.reiniciarJogo(tabuleiro);
        atualizarInterfaceTabuleiro();
    }

    private void criarJanela() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 300);
        setLayout(new GridLayout(this.tabuleiro.getDimensao() + 1, this.tabuleiro.getDimensao()));
    }

    private void atualizarInterfaceTabuleiro() {
        posicaoBotaoMap.forEach((botao, posicao) -> botao.setText(textoBotao(tabuleiro.getPeca(posicao))));
    }

    private String textoBotao(Peca peca) {
        return peca.getValor() == tabuleiro.getValorPecaVazia() ? "" : String.valueOf(peca.getValor());
    }

    private MouseAdapter getMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JButton botaoClicado = (JButton) e.getSource();
                Posicao posicaoClicada = posicaoBotaoMap.get(botaoClicado);

                if (regra.fazerMovimento(tabuleiro, posicaoClicada)) {
                    atualizarInterfaceTabuleiro();
                    if (regra.verificarVitoria(tabuleiro)) {
                        JOptionPane.showMessageDialog(null, "Parabéns! Você venceu o jogo!");
                    }

                }
            }
        };
    }
}
