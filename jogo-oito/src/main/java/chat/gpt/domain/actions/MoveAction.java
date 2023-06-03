package chat.gpt.domain.actions;

import chat.gpt.domain.interfaces.ActionEventDelegate;
import chat.gpt.domain.table.Table;


public class MoveAction implements ActionEventDelegate<Integer> {
    private Integer dx;
    private Integer dy;
    private Table table;

    public MoveAction(Table table, Integer dx, Integer dy) {
        this.dx = dx;
        this.dy = dy;
        this.table = table;
    }

    // dataEvent is a keyCodeEvent
    @Override
    public void doAction(Integer dataEvent) {
        String out = dataEvent + " - (" + dx + "," + dy + ")";
        System.out.println(out);

        Integer linhaVazia = -1;
        Integer colunaVazia = -1;
        for (Integer i = 0; i < 3; i++) {
            for (Integer j = 0; j < 3; j++) {
                if (table.getCell(i, j).isEmpty()) {
                    linhaVazia = i;
                    colunaVazia = j;
                }
            }
        }
        Integer novaLinha = linhaVazia + dx;
        Integer novaColuna = colunaVazia + dy;
        if (novaLinha < 0 || novaLinha > 2 || novaColuna < 0 || novaColuna > 2) {
            // movimento inválido
            return;
        }

        table.swap(linhaVazia, colunaVazia, novaLinha, novaColuna);
    }
}