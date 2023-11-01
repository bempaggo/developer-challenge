package chat.gpt.models.observer;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import chat.gpt.models.Botao;

public class TabuleiroGUI extends JFrame {

    private Tabuleiro tabuleiro;

    public TabuleiroGUI(Tabuleiro tabuleiro) {
        super("Jogo dos Oito");

		this.tabuleiro = tabuleiro;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLayout(new GridLayout(4, 3));

		criarTabuleiro();

		setFocusable(true);
		setVisible(true);
    }

    private void criarTabuleiro() {
        // Cria as 9 peças de 0 até 8
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
                int valor = tabuleiro.getTabuleiro()[i][j];
				Botao botao = new Botao(String.valueOf(valor));
				tabuleiro.getBotoes()[i][j] = botao;
				add(botao);
			}
		}
        
        // Cria o botão de reiniciar com a fonte padrão
		tabuleiro.setBotaoReiniciar(new Botao("Reiniciar"));
        tabuleiro.getBotaoReiniciar().setFont(getFont());
		tabuleiro.getBotaoReiniciar().addActionListener(e -> tabuleiro.reiniciarJogo());
		add(new JLabel(""));
		add(tabuleiro.getBotaoReiniciar());
		add(new JLabel(""));
	}

	//Atualiza a posição de todos os botões na tela (visão do usuário)
    protected static void atualizarVisaoTabuleiroGUI(Tabuleiro tabuleiro) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Botao botao = tabuleiro.getBotoes()[i][j];
				int valor = tabuleiro.getTabuleiro()[i][j];
				botao.setValor(String.valueOf(valor));
			}
		}
	}

	protected static void mensagemVitoria() {
		JOptionPane.showMessageDialog(null, "Parabéns, você venceu!");
	}

}
