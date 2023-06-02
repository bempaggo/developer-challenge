package chat.gpt;

import javax.swing.JButton;

public class Table {
    private Integer[][] table = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    private JButton[][] botoes;

    Table(JButton[][] botoes){
        this.botoes = botoes;
    }
    
    public JButton[][] getBotoes(){
        return botoes;
    }

    public void setCell(Integer dx, Integer dy, Integer element){
        table[dx][dy] = element;
    }

    public Integer getCell(Integer dx, Integer dy){
        return table[dx][dy];
    }

    public void atualizarTabuleiro() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton botao = botoes[i][j];
				// int valor =  tabuleiro[i][j];
				int valor = getCell(i,j);
				if (valor == 0) {
					botao.setText("");
				} else {
					botao.setText(String.valueOf(valor));
				}
			}
		}
        if(jogoConcluido()){
            // emits event to listner;
        }
	}  

    private boolean jogoConcluido() {
		int count = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (getCell(i, j) != count % 9) {
					return false;
				}
				count++;
			}
		}
		return true;
	}

}
