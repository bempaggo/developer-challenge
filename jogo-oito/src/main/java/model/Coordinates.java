package model;

import util.GridConstants;

public class Coordinates {
    
    private Integer row;
    private Integer col;

    public Coordinates(Integer value) {
        this.row = value / GridConstants.WIDTH.getMeasure();
        this.col = value % GridConstants.WIDTH.getMeasure();
    }

    Integer getRow() {
        return row;
    }

    Integer getCol() {
        return col;
    }

}