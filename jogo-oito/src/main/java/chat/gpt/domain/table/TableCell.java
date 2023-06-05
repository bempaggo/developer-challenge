package chat.gpt.domain.table;

import javax.swing.JButton;

import chat.gpt.domain.interfaces.Cell;

public class TableCell extends JButton implements Cell<Integer> {
    private Integer cellValue = 0;

    @Override
    public void setValue(Integer value) {
        // wraps setText from JButton, isolating TableCell
        // and doing Table more generic and isolated.
        cellValue = value;
        if (value == 0) {
            setText("");
            return;
        }
        setText(String.valueOf(value));
    }

    @Override
    public Integer getValue() {
        return cellValue;
    }

    @Override
    public Boolean isEmpty() {
        return getValue() == 0;
    }

    @Override
    public boolean equals(Object other){
        if(!other.getClass().equals(this.getClass())){
            return false;
        }
        var cell = (TableCell) other;
        return getValue().equals(cell.getValue());
    }

}