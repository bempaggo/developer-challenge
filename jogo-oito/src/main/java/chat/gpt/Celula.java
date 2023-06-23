package chat.gpt;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Celula extends JButton{
	private int valor;

    public Celula(int valor) {
        this.valor = valor;
        setFont(new Font("Arial", Font.BOLD, 36));
        setText(getValorFormatado());
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implemente a ação de clique na célula aqui
            	Tabuleiro tabuleiro = new Tabuleiro();
            	tabuleiro.reiniciar();
            }
        });
    }

    public void setValor(int valor) {
        this.valor = valor;
        setText(getValorFormatado());
    }

    private String getValorFormatado() {
        if (valor == 0) {
            return "";
        } else {
            return String.valueOf(valor);
        }
    }
    
   

}
