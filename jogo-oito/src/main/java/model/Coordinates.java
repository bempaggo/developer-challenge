package model;

import util.GridConstants;

public class Coordinates {
    
    private Integer row;
    private Integer col;

    public Coordinates(Integer value) {
        this.row = value / GridConstants.WIDTH.getMeasure();
        this.col = value % GridConstants.WIDTH.getMeasure();
    }

    public Integer getRow() {
        return row;
    }

    public Integer getCol() {
        return col;
    }

}