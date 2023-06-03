package chat.gpt;

import java.util.ArrayList;
import java.util.Random;

public class Table {

	private GameListener listener;
	private static Integer MAX_CELLS = 9;
	private ArrayList<TableCell> cells = new ArrayList<>();

	Table(GameListener listener) {
		this.listener = listener;

		for (Integer i = 1; i < MAX_CELLS; ++i) {
			var cell = new TableCell();
			cell.setValue(i);
			cells.add(cell);
		}
		var cell = new TableCell();
		cell.setValue(0);
		cells.add(cell);
		suffleTable();
	}

	public ArrayList<TableCell> getBotoes() {
		return cells;
	}

	public void suffleTable() {

		Random rand = new Random();

		for (Integer i = cells.size() - 1; i > 1; --i) {
			Integer j = rand.nextInt(0, MAX_CELLS - 1);
			var auxj = cells.get(j);
			var auxi = cells.get(i);

			Integer aux = auxi.getValue();
			auxi.setValue(auxj.getValue());
			auxj.setValue(aux);
		}
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

	private Integer cellIndex(Integer dx, Integer dy) {
		// dx * k + dy => where k is 3, the number of columns:
		return dx * 3 + dy;
	}

	public TableCell getCell(Integer dx, Integer dy) {
		// return table[dx][dy];
		return cells.get(cellIndex(dx, dy));
	}

	private Boolean jogoConcluido() {
		Boolean isGoal = false;

		for (Integer i = 0; i < MAX_CELLS - 1; ++i) {
			if (cells.get(i).getValue() != i + 1) {
				isGoal = false;
				break;
			}
			isGoal = true;
		}

		return isGoal;
	}

}
