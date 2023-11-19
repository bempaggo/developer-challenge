package service;

import java.util.List;

public interface Graph {

    void feedback();
    
    void setting();

    void swap(Integer keyCode);

    void click(Integer cellValue);

    Boolean checkGameOver();

	List<Vertex> getCells();
}
