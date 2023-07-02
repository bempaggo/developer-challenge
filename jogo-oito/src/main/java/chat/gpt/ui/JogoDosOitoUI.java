package chat.gpt.ui;

import chat.gpt.infra.util.Util;
import chat.gpt.infra.values.DirecaoMovimento;
import chat.gpt.model.Peca;
import chat.gpt.model.PosicaoPeca;
import chat.gpt.model.Tabuleiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JogoDosOitoUI {

    public static final String STR_VAZIA = "";
    private final Tabuleiro tabuleiro;
    private final List<List<JButton>> botoes;
    private final JFrame jFrame;

    public JogoDosOitoUI(String titulo) {
        this.jFrame = new JFrame(titulo);
        this.tabuleiro = new Tabuleiro();
        this.botoes = new ArrayList<>(Arrays.asList(
                new ArrayList<>(3),
                new ArrayList<>(3),
                new ArrayList<>(3)
        ));
    }

    public void iniciarJogo() {
        this.criaJanela();
        this.criaComponentes();
        this.permiteJogarPeloTeclado();
        this.atualizaTabuleiro();
        this.jFrame.setVisible(true);
    }

    private void criaJanela() {
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setSize(300, 300);
        this.jFrame.setLayout(new GridLayout(4, 3));
        this.jFrame.setFocusable(true);
    }

    private void criaComponentes() {
        this.tabuleiro.executaParaCadaPosicaoTabuleiro((linha, coluna) -> {
            JButton botaoPeca = this.criaBotaoPeca();
            this.botoes.get(linha).add(botaoPeca);
            this.adicionaNoJframe(botaoPeca);
            botaoPeca.addActionListener(this::moverPecaPeloMouse);
        });
        JButton botaoReiniciar = new JButton("Reiniciar");
        botaoReiniciar.addActionListener(e -> this.reiniciaJogo());
        this.adicionaNoJframe(new JLabel(STR_VAZIA), botaoReiniciar, new JLabel(STR_VAZIA));
    }

    private void moverPecaPeloMouse(ActionEvent e) {
        String valorPeca = ((JButton) e.getSource()).getText();
        if (valorPeca.isEmpty()) return;
        DirecaoMovimento direcaoMovimento = this.pegaDirecaoMovimentoMouse(Integer.parseInt(valorPeca));
        this.movimentaPecaEAtualiza(direcaoMovimento);
    }

    private void movimentaPecaPeloTeclado(KeyEvent e) {
        DirecaoMovimento movimento = DirecaoMovimento.pegaPeloTeclado(e.getKeyCode());
        this.movimentaPecaEAtualiza(movimento);
    }


    private DirecaoMovimento pegaDirecaoMovimentoMouse(int numeroPeca) {
        PosicaoPeca pPecaTocada = this.tabuleiro.encontraPosicaoPeca(new Peca(numeroPeca));
        PosicaoPeca pVazia = this.tabuleiro.pegaPosicaoVazia();
        return DirecaoMovimento.pegaPelaPosicaoPecaClicada(pVazia, pPecaTocada);
    }

    private void permiteJogarPeloTeclado() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) this.movimentaPecaPeloTeclado(e);
            return false;
        });
    }

    private void reiniciaJogo() {
        this.tabuleiro.resetaTabuleiro();
        this.atualizaTabuleiro();
    }

    private void movimentaPecaEAtualiza(DirecaoMovimento movimento) {
        if (Util.movimentoEhInvalido(movimento)) return;
        boolean moveu = this.tabuleiro.movePeca(movimento);
        if (moveu) {
            this.atualizaTabuleiro();
            this.verificaJogoConcluido();
        }
    }

    private void verificaJogoConcluido() {
        if (this.tabuleiro.jogoConcluido()) {
            JOptionPane.showMessageDialog(this.jFrame, "Parabéns, você venceu!");
        }
    }

    private void atualizaTabuleiro() {
        this.tabuleiro.executaParaCadaPosicaoTabuleiro((linha, coluna) -> {
            JButton botao = this.botoes.get(linha).get(coluna);
            int pecaValor = this.tabuleiro.pegaPecaPelaPosicao(linha, coluna).getValor();
            botao.setText(pecaValor == 0 ? STR_VAZIA : String.valueOf(pecaValor));
        });
    }

    private JButton criaBotaoPeca() {
        JButton botao = new JButton();
        botao.setFont(new Font("Arial", Font.BOLD, 36));
        return botao;
    }

    private void adicionaNoJframe(Component... components) {
        Arrays.stream(components).forEach(this.jFrame::add);
    }
}
