package model;

import util.GridConstants;

public class Coordinates {
    
    private Integer row;
    private Integer col;

    public Coordinates(Integer value) {
        this.row = value / GridConstants.GRID_WIDTH.getMeasure();
        this.col = value % GridConstants.GRID_WIDTH.getMeasure();
    }

    Integer getRow() {
        return row;
    }

    Integer getCol() {
        return col;
    }

}