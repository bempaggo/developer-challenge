package chat.gpt;

import javax.swing.JButton;

public interface Cell<T> {
    void setValue(T value);
    T getValue();
    Boolean isEmpty();
}


class TableCell extends JButton implements Cell<Integer>{
    private Integer cellValue = 0;

    @Override
    public void setValue(Integer value) {
        // wraps setText from JButton, isolating TableCell
        // and doing Table more generic and isolated.
        cellValue = value;
        if(value == 0){
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
    

}