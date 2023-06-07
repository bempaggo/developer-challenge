package chat.gpt.src.view;

import chat.gpt.src.exception.JogoException;
import chat.gpt.src.servico.Jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class View extends JFrame {

    private final Jogo jogo;
    private final List<Botao> botaoPecas;

    public View(Jogo jogo) {
        super("Jogo dos oito");
        this.jogo = jogo;
        this.botaoPecas = new ArrayList<>(9);
        inicializarInterfaceGrafica();
    }

    private void inicializarInterfaceGrafica() {
        criarJanela();
        criarBotoes();
        ativarInterfaceGrafica();
    }

    private void criarJanela() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 300);
        setLayout(new GridLayout(4, 3));
    }

    private void criarBotoes() {
        inicializarBotoesPecas();
        criarBotaoEmbaralhar();
        criarBotaoReinicar();
    }

    private void inicializarBotoesPecas() {
        IntStream.range(0, 9).forEach(i -> botaoPecas.add(criarBotao(i)));
    }

    private Botao criarBotao(Integer valor) {
        return new Botao(criarJButton(), valor);
    }

    private JButton criarJButton() {
        JButton botao = new JButton();
        botao.setFont(new Font("Arial", Font.BOLD, 36));
        botao.addMouseListener(tentarMoverBotao());
        add(botao);
        return botao;
    }

    private MouseAdapter tentarMoverBotao() {
        JFrame view = this;
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JButton jButtonClicado = (JButton) e.getSource();
                Botao botaoClicado = getBotaoClicado(jButtonClicado);
                jogo.fazerMovimento(botaoClicado.getValor());
                atualizarInterfaceTabuleiro();
                verificarVitoria(view);
            }
        };
    }

    private Botao getBotaoClicado(JButton jButtonClicado) {
        return botaoPecas.stream().filter(botao -> botao.getjButton().equals(jButtonClicado))
                .findFirst().orElseThrow(() -> new JogoException("Botao clicado nao foi encontrado!"));
    }

    private void verificarVitoria(JFrame view) {
        Boolean vitoria = jogo.verificarVitoria();
        if (vitoria) {
            JOptionPane.showMessageDialog(view, "Parabéns! Você venceu o jogo!");
        }
    }

    private void criarBotaoEmbaralhar() {
        JButton botaoEmbaralhar = new JButton("Embaralhar");
        botaoEmbaralhar.addActionListener(e -> embaralharJogo());
        add(botaoEmbaralhar);
    }

    private void embaralharJogo() {
        jogo.embaralharPecas();
        atualizarInterfaceTabuleiro();
    }

    private void criarBotaoReinicar() {
        JButton botaoReiniciar = new JButton("Reiniciar");
        botaoReiniciar.addActionListener(e -> reiniciarJogo());
        add(botaoReiniciar);
    }

    private void reiniciarJogo() {
        jogo.reiniciar();
        atualizarInterfaceTabuleiro();
    }

    private void ativarInterfaceGrafica() {
        setFocusable(true);
        atualizarInterfaceTabuleiro();
        setVisible(true);
    }

    private void atualizarInterfaceTabuleiro() {
        IntStream.range(0, 9).forEach(i -> {
                    botaoPecas.get(i).getjButton().setText(jogo.getTabuleiro().getPecaPorPosicao(i).getTextoValor());
                    botaoPecas.get(i).setValor(jogo.getTabuleiro().getPecaPorPosicao(i).getValor());
                }
        );
    }

    public List<Botao> getBotaoPecas() {
        return botaoPecas;
    }

}
