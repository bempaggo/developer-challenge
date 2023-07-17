package model;

import util.BoardConstants;

public class Coordinates {
    
    private Integer row;
    private Integer col;

    public Coordinates(Integer value) {
        this.row = value / BoardConstants.WIDTH.getMeasure();
        this.col = value % BoardConstants.WIDTH.getMeasure();
    }

    public Integer getRow() {
        return row;
    }

    public Integer getCol() {
        return col;
    }

}