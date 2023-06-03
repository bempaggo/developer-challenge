package chat.gpt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Table {

	private GameListener listener;
	private static Integer MAX_CELLS = 9;
	private ArrayList<TableCell> cells = new ArrayList<>();

	Table(GameListener listener) {
		this.listener = listener;

		for (int i = 1; i < MAX_CELLS; ++i) {
			var cell = new TableCell();
			cell.setValue(i);
			cells.add(cell);
		}
		var cell = new TableCell();
		cell.setValue(0);
		cells.add(cell);
		// sortTable();

		// Collections.shuffle(cells);
	}

	public ArrayList<TableCell> getBotoes() {
		return cells;
	}

	public void suffleTable() {
		
		Random rand = new Random();

		for(Integer i = cells.size() - 1; i > 1; --i){
			Integer j = rand.nextInt(0, MAX_CELLS - 1);
			var auxj = cells.get(j);
			var auxi = cells.get(i);

			Integer aux = auxi.getValue();
			auxi.setValue(auxj.getValue());
			auxj.setValue(aux);
		}
		
		//Fisher–Yates shuffle
		// for i from n−1 down to 1 do
		// 	j ← random integer such that 0 ≤ j ≤ i
		// 	exchange a[j] and a[i]

		// rand.nextInt(0, MAX_CELLS -1);

		//     int randomNum = rand.nextInt((max - min) + 1) + min;
		// Collections.shuffle(cells);
	}

	public void swap(Integer dx, Integer dy, Integer dw, Integer dz) {
		var emptyCell = getCell(dx, dy);
		var newCell = getCell(dw, dz);

		Integer oldValue = newCell.getValue();
		Integer emptyValue = emptyCell.getValue();

		newCell.setValue(emptyValue);
		emptyCell.setValue(oldValue);


		if (jogoConcluido()) {
			// emits event to listner;
			listener.notify("jogoConcluido", "Parabéns, você venceu!");
		}
	}

	private void setCell(Integer dx, Integer dy, TableCell element) {
		// table[dx][dy] = element;
		cells.set(cellIndex(dx, dy), element);
	}

	private Integer cellIndex(Integer dx, Integer dy) {
		// dx * k + dy => where k is 3, the number of columns:
		return dx * 3 + dy;
	}

	public TableCell getCell(Integer dx, Integer dy) {
		// return table[dx][dy];
		return cells.get(cellIndex(dx, dy));
	}

	// public void atualizarTabuleiro() {
	// 	for (int i = 0; i < 3; i++) {
	// 		for (int j = 0; j < 3; j++) {
	// 			// JButton botao = botoes[i][j];
	// 			// // int valor = tabuleiro[i][j];
	// 			// int valor = getCell(i,j);
	// 			// if (valor == 0) {
	// 			// botao.setText("");
	// 			// } else {
	// 			// botao.setText(String.valueOf(valor));
	// 			// }

	// 			// JButton botao = botoes[i][j];
	// 			// // int valor = tabuleiro[i][j];
	// 			// var cell = getCell(i,j);
	// 			// if (cell.isEmpty() == 0) {
	// 			// cell
	// 			// // botao.setText("");
	// 			// } else {
	// 			// botao.setText(String.valueOf(valor));
	// 			// }
	// 		}
	// 	}
	// 	if (jogoConcluido()) {
	// 		// emits event to listner;
	// 		listener.notify("jogoConcluido", "Parabéns, você venceu!");
	// 	}
	// }

	private boolean jogoConcluido() {
		boolean isGoal = false;

		for(Integer i = 0; i< MAX_CELLS - 1; ++i){
			if(cells.get(i).getValue() != i + 1){
				isGoal = false;
				break;
			}
			isGoal=true;
		}

		return isGoal;
	}

}
