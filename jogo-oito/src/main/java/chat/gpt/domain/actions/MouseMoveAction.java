package chat.gpt.domain.actions;

import chat.gpt.domain.interfaces.ActionEventDelegate;
import chat.gpt.domain.table.Table;
import chat.gpt.domain.table.TableCell;

public class MouseMoveAction implements ActionEventDelegate<TableCell> {

    private Table table;

    public MouseMoveAction(Table table) {
        this.table = table;
    }

    @Override
    public void doAction(TableCell dataEvent) {
        Integer i = 0;
        Integer j = 0;

        for (TableCell aux : table.getBotoes()) {
            if (aux.isEmpty()) {
                break;
            }
            ++i;
        }

        for (TableCell aux : table.getBotoes()) {
            if (aux.equals(dataEvent)) {
                break;
            }
            ++j;
        }

        Integer idx = i / 3;
        Integer idy = i % 3;

        Integer jdx = j / 3;
        Integer jdy = j % 3;

        // not allowed (1, 1) vector movement:
        if (Math.abs((idx - jdx)) >= 1 && Math.abs((idy - jdy)) >= 1) {
            return;
        }

        table.swap(idx, idy, jdx, jdy);
    }

}
