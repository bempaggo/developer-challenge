package chat.gpt.modelos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class InterfaceGrafica extends JFrame {

    private Tabuleiro tabuleiro;
    private Regra regra;
    private Map<JButton, Posicao> posicaoBotao = new HashMap<>();

    public InterfaceGrafica(Tabuleiro tabuleiro, Regra regra) {
        super("Jogo dos oito");
        this.tabuleiro = tabuleiro;
        this.regra = regra;
        criarJanela();
        criarBotoes();
    }

    private void criarBotaoDePeca() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                posicaoBotao.put(criarBotao(), new Posicao(i, j));
            }
        }
    }

    private JButton criarBotao() {
        JButton botao = new JButton();
        botao.setFont(new Font("Arial", Font.BOLD, 36));
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JButton botaoClicado = (JButton) e.getSource();
                Posicao posicaoClicada = posicaoBotao.get(botaoClicado);

                if (regra.fazerMovimento(tabuleiro, posicaoClicada)) {
                    System.out.println("movimento feito!");
                    atualizarTabuleiro();
                    if(regra.verificarVitoria(tabuleiro)){
                        System.out.println("Jogo finalizado!!");
                    };
                }
            }
        });
        add(botao);
        return botao;
    }


    private void criarBotoes() {
        criarBotaoDePeca();
        JButton botaoReiniciar = new JButton("Reiniciar");
        //botaoReiniciar.addActionListener(e -> reiniciarJogo());
        add(new JLabel(""));
        add(botaoReiniciar);
        add(new JLabel(""));
        setFocusable(true);
        atualizarTabuleiro();
        setVisible(true);

    }


    private void criarJanela() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));
    }

    private void atualizarTabuleiro() {
        posicaoBotao.forEach((botao, posicao) -> botao.setText(textoBotao(tabuleiro.getPeca(posicao))));
    }

    private String textoBotao(Peca peca) {
        return peca.getValor() == 9 ? "" : String.valueOf(peca.getValor());
    }


}
