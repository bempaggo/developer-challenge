package model;

public interface MovementInterface {

    void move(Integer buttonValue);
    void moveDown();
    void moveUp();
    void moveLeft();
    void moveRight();
    void setGrid(BoardInterface grid);

}