package chat.gpt.domain.table;

import java.util.ArrayList;
import java.util.Random;

import chat.gpt.domain.listeners.NotificationListener;

public class Table implements Cloneable {

	private NotificationListener listener;
	private static Integer MAX_CELLS = 9;
	private ArrayList<TableCell> cells = new ArrayList<>();

	public Table(NotificationListener listener) {
		this.listener = listener;

		for (Integer i = 0; i < MAX_CELLS; ++i) {
			var cell = new TableCell();
			cell.setValue(i);
			cells.add(cell);
		}

		// cells.get(0).setValue(5);
		// cells.get(1).setValue(2);
		// cells.get(2).setValue(8);
		// cells.get(3).setValue(4);
		// cells.get(4).setValue(1);
		// cells.get(5).setValue(7);
		// cells.get(6).setValue(0);
		// cells.get(7).setValue(3);
		// cells.get(8).setValue(6);
		this.randomizeTable();
	}

	public Table(Table table) {
		this.listener = table.listener;

		table.cells.stream().forEach(x -> {
			var cell = new TableCell();
			cell.setValue(x.getValue());
			this.cells.add(cell);
		});
		// this.cells = new ArrayList<>(table.getCells());
	}

	public ArrayList<TableCell> getCells() {
		return cells;
	}

	public void randomizeTable() {

		Random rand = new Random();

		do {
			for (Integer i = cells.size() - 1; i > 1; --i) {
				Integer j = rand.nextInt(0, MAX_CELLS - 1);
				var auxj = cells.get(j);
				var auxi = cells.get(i);

				Integer aux = auxi.getValue();
				auxi.setValue(auxj.getValue());
				auxj.setValue(aux);
			}
		} while (!isSolvable());

		System.out.println("OUT SUFFLE TABLE");
	}

	private Boolean isSolvable() {
		// 5 2 8
		// 4 1 7
		// 0 3 6
		Integer sum = 0;
		for (Integer i = 0; i < cells.size(); ++i) {
			Integer value = cells.get(i).getValue();

			for (Integer j = i + 1; j < cells.size(); ++j) {
				var auxValue = cells.get(j).getValue();
				if (0 < auxValue && auxValue < value) {
					sum += 1;
				}
			}
		}

		return sum % 2 == 0;
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

	public Boolean jogoConcluido() {
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
