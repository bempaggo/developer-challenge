package chat.gpt.ui;

import chat.gpt.infra.util.Util;
import chat.gpt.infra.values.DirecaoMovimento;
import chat.gpt.model.PosicaoPeca;
import chat.gpt.model.Tabuleiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class JogoDosOitoUI {

    public static final String STR_VAZIA = "";
    private final Tabuleiro tabuleiro;
    private final JButton[][] botoes = new JButton[3][3];
    private final JFrame jFrame;

    public JogoDosOitoUI(String titulo) {
        this.jFrame = new JFrame(titulo);
        this.tabuleiro = new Tabuleiro();
    }

    public void iniciarJogo() {
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(300, 300);
        jFrame.setLayout(new GridLayout(4, 3));

        this.criaComponentes();

        this.permiteJogarPeloTeclado();
        jFrame.setFocusable(true);
        this.atualizaTabuleiro();
        jFrame.setVisible(true);
    }

    private void criaComponentes() {
        this.tabuleiro.executaParaCadaPosicao((linha, coluna) -> {
            JButton botaoPeca = this.criaBotaoPeca();
            botoes[linha][coluna] = botaoPeca;
            this.adicionaComponentesJframe(botaoPeca);
            botaoPeca.addActionListener(this::moverPecaPeloMouse);
        });
        JButton botaoReiniciar = new JButton("Reiniciar");
        botaoReiniciar.addActionListener(e -> this.reiniciaJogo());
        this.adicionaComponentesJframe(new JLabel(STR_VAZIA), botaoReiniciar, new JLabel(STR_VAZIA));
    }

    private void moverPecaPeloMouse(ActionEvent e) {
        String numeroPeca = ((JButton) e.getSource()).getText();
        if (numeroPeca.isEmpty()) return;
        DirecaoMovimento direcaoMovimento = this.pegaDirecaoMovimentoMouse(numeroPeca);
        this.movimentaPecaEAtualiza(direcaoMovimento);
    }

    private void movimentaPecaPeloTeclado(KeyEvent e) {
        DirecaoMovimento movimento = DirecaoMovimento.pegaPeloTeclado(e.getKeyCode());
        this.movimentaPecaEAtualiza(movimento);
    }


    private DirecaoMovimento pegaDirecaoMovimentoMouse(String numeroPeca) {
        PosicaoPeca pPecaTocada = this.tabuleiro.encontraPosicaoPecaPeloNumero(Integer.parseInt(numeroPeca));
        PosicaoPeca pVazia = this.tabuleiro.pegaPosicaoVazia();
        return DirecaoMovimento.pegaPelaPosicaoBotaoTocado(pVazia, pPecaTocada);
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
        this.tabuleiro.executaParaCadaPosicao((linha, coluna) -> {
            JButton botao = this.botoes[linha][coluna];
            int pecaNumero = this.tabuleiro.pegaPecaPelaPosicao(linha, coluna);
            botao.setText(pecaNumero == 0 ? STR_VAZIA : String.valueOf(pecaNumero));
        });
    }

    private JButton criaBotaoPeca() {
        JButton botao = new JButton();
        botao.setFont(new Font("Arial", Font.BOLD, 36));
        return botao;
    }

    private void adicionaComponentesJframe(Component... components) {
        Arrays.stream(components).forEach(this.jFrame::add);
    }
}
