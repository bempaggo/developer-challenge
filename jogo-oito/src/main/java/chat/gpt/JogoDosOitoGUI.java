package chat.gpt;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JogoDosOitoGUI implements MouseListener {

	private JogoDosOito jogo;
	private JFrame janela;
	private JButton[][] botoes = new JButton[3][3];
	private JButton botaoReiniciar;

	public JogoDosOitoGUI(JogoDosOito jogo) {
		this.jogo = jogo;
	}

	public void criarJanela() {
		janela = new JFrame("Jogo dos Oito");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(300, 300);
		janela.setLayout(new GridLayout(4, 3));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				botoes[i][j] = new JButton(Integer.toString(jogo.getValor(i, j)));
				botoes[i][j].setFont(new Font("Arial", Font.BOLD, 36));
				//botoes[i][j].addActionListener(new BotaoListener(i, j));
				botoes[i][j].addMouseListener(this);
				janela.add(botoes[i][j]);
			}
		}
		
		botaoReiniciar = new JButton("Reiniciar");
		botaoReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jogo = new JogoDosOito();
				atualizarTabuleiro();
			}
		});

		
		janela.add(new JLabel(""));
		janela.add(botaoReiniciar);
		janela.add(new JLabel(""));
		
		janela.addMouseListener(this);
		janela.setFocusable(true);
		atualizarTabuleiro();
		janela.setVisible(true);
	}

	private void atualizarTabuleiro() {
		jogo.atualizarTabuleiro(botoes);
	}

//	private class BotaoListener implements ActionListener {
//
//		private int linha;
//		private int coluna;
//
//		public BotaoListener(int linha, int coluna) {
//			this.linha = linha;
//			this.coluna = coluna;
//		}
//
//		public void actionPerformed(ActionEvent e) {
//			jogo.mover(linha - 1, coluna - 1);
//			atualizarTabuleiro();
//			if (jogo.jogoConcluido()) {
//				janela.setTitle("Parabéns, você ganhou!");
//				jogo.reiniciarJogo(botoes);
//			}
//		}
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int linha = -1;
        int coluna = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (botoes[i][j] == e.getSource()) {
                    linha = i;
                    coluna = j;
                }
            }
        }
        
        System.out.println("Linha: " + linha + "\nColuna: " + coluna);
        jogo.mover(linha, coluna);
        atualizarTabuleiro();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}