package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;
    private Integer MIN_BOARD_SIZE = 3;
    private Integer MAX_BOARD_SIZE = 20;

    @BeforeEach
    void setUp() {
        Integer boardWidth = getRandomGridWidth(MIN_BOARD_SIZE, MAX_BOARD_SIZE);
        Integer boardSize = boardWidth * boardWidth;
        board = new Board(boardSize, boardWidth, true);
    }

    @RepeatedTest(5)
    void testGetGridSize() {
        Integer boardSize = board.getBoardSize();
        Integer boardWidth = board.getBoardWidth();
        assertEquals(boardWidth * boardWidth, boardSize);
    }

    @RepeatedTest(5)
    void testGetBoardWidth() {
        Integer boardSize = board.getBoardSize();
        Integer boardWidth = board.getBoardWidth();
        assertEquals(boardWidth, boardSize / boardWidth);
    }

    @RepeatedTest(5)
    void testGetBoardData() {
        List<Integer> boardData = board.getBoardData();
        assertNotNull(boardData);
        assertEquals(board.getBoardSize(), boardData.size());
        assertTrue(boardData.contains(0));

        Set<Integer> uniqueValues = new HashSet<>(boardData);
        assertEquals(board.getBoardSize(), uniqueValues.size());
    }

    @RepeatedTest(5)
    void testGetEmptySlotIndex() {
        int emptySlotIndex = board.getEmptySlotIndex();
        assertTrue(emptySlotIndex >= 0 && emptySlotIndex < board.getBoardSize());
        assertEquals(0, board.getBoardData().get(emptySlotIndex));
    }

    @Test
    void testSolution() {
        board.solution();
        assertEquals(board.getGameIsCompleteBoardPattern(), board.getBoardData());
    }

    @Test
    void testReset() {
        List<Integer> preResetBoard = Collections.unmodifiableList(new ArrayList<>(board.getBoardData()));
        board.reset();
        List<Integer> postResetBoard = Collections.unmodifiableList(new ArrayList<>(board.getBoardData()));
        assertNotEquals(preResetBoard, postResetBoard);
    }

    private Integer getRandomGridWidth(Integer minBoardWidth, Integer maxBoardWidth) {
        return ThreadLocalRandom.current().nextInt(minBoardWidth, maxBoardWidth + 1);
    }

    
}
