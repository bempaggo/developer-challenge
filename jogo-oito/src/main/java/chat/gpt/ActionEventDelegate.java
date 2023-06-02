package chat.gpt;

public interface ActionEventDelegate {    
    void doAction(Integer keyCodeEvent);
}

class MoveAction implements ActionEventDelegate {
    private Integer dx;
    private Integer dy;
    private Table table;

    public MoveAction(Table table, Integer dx, Integer dy){
        this.dx = dx;
        this.dy = dy;
        this.table = table;
    }

    @Override
    public void doAction(Integer keyCodeEvent) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'doAction'");
        String out = keyCodeEvent + " - (" + dx + "," + dy + ")" ;
        System.out.println(out);


        int linhaVazia = -1;
		int colunaVazia = -1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (table.getCell(i, j) == 0) {
					linhaVazia = i;
					colunaVazia = j;
				}
			}
		}
		int novaLinha = linhaVazia + dx;
		int novaColuna = colunaVazia + dy;
		if (novaLinha < 0 || novaLinha > 2 || novaColuna < 0 || novaColuna > 2) {
			// movimento inválido
			return;
		}

        var element = table.getCell(novaLinha, novaColuna);
        table.setCell(linhaVazia, colunaVazia, element);
		table.setCell(novaLinha, novaColuna, 0);
        table.atualizarTabuleiro();
    }
}