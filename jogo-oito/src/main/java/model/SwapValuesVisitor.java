package model;

import interfaces.Vertex;
import interfaces.VertexVisitor;

public class SwapValuesVisitor implements VertexVisitor {

    private final Vertex otherCell;

    public SwapValuesVisitor(Vertex otherCell) {
        this.otherCell = otherCell;
    }

    @Override
    public void visit(Vertex cell) {
        Integer currentValue = cell.getValue();
        Integer otherValue = otherCell.getValue();

        cell.setValue(otherValue);
        otherCell.setValue(currentValue);
    }

}
