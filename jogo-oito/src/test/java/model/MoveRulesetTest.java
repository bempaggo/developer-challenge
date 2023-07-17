package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MoveRulesetTest {

    private MoveRuleset moveRuleset;

    @Mock
    private BoardInterface board;
    private final Integer MIN_BOARD_WIDTH = 3;
    private final Integer MAX_BOARD_WIDTH = 20;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        moveRuleset = new MoveRuleset();
        moveRuleset.setBoard(board);
    }

    @Test
    @RepeatedTest(5)
    void testMoveUp() {
        List<Integer> boardData = generateRandomBoardData();
        int emptySlotIndex = boardData.indexOf(0);
        int boardWidth = (int) Math.sqrt(boardData.size());

        when(board.getEmptySlotIndex()).thenReturn(emptySlotIndex);
        when(board.getBoardWidth()).thenReturn(boardWidth);
        when(board.getBoardData()).thenReturn(boardData);

        moveRuleset.moveUp();

        verify(board, atLeastOnce()).getEmptySlotIndex();
        verify(board).getBoardWidth();
        verify(board, atLeastOnce()).getBoardData();

    }

    @Test
    @RepeatedTest(5)
    void testMoveDown() {
        List<Integer> boardData = generateRandomBoardData();
        int emptySlotIndex = boardData.indexOf(0);
        int boardWidth = (int) Math.sqrt(boardData.size());

        when(board.getEmptySlotIndex()).thenReturn(emptySlotIndex);
        when(board.getBoardWidth()).thenReturn(boardWidth);
        when(board.getBoardData()).thenReturn(boardData);

        moveRuleset.moveDown();

        verify(board,atLeastOnce()).getEmptySlotIndex();
        verify(board).getBoardWidth();
        verify(board,atLeastOnce()).getBoardData();

    }

    @Test
    @RepeatedTest(5)
    void testMoveLeft() {
        List<Integer> boardData = generateRandomBoardData();
        int emptySlotIndex = boardData.indexOf(0);

        when(board.getEmptySlotIndex()).thenReturn(emptySlotIndex);
        when(board.getBoardData()).thenReturn(boardData);

        moveRuleset.moveLeft();

        verify(board, atLeastOnce()).getEmptySlotIndex();
        verify(board, atLeastOnce()).getBoardData();

    }

    @Test
    @RepeatedTest(5)
    void testMoveRight() {
        List<Integer> boardData = generateRandomBoardData();
        int emptySlotIndex = boardData.indexOf(0);

        when(board.getEmptySlotIndex()).thenReturn(emptySlotIndex);
        when(board.getBoardData()).thenReturn(boardData);

        moveRuleset.moveRight();

        verify(board, atLeastOnce()).getEmptySlotIndex();
        verify(board, atLeastOnce()).getBoardData();

    }


    @Test
    @RepeatedTest(5)
    void testMoveInvalidButtonValueIsNotValidatedByThisClass() {
        List<Integer> boardData = generateRandomBoardData();

        when(board.getBoardData()).thenReturn(boardData);

        assertThrows(IndexOutOfBoundsException.class, () -> moveRuleset.move(-1));

        verify(board, atLeastOnce()).getEmptySlotIndex();
        verify(board, atLeastOnce()).getBoardData();

    }

    private List<Integer> generateRandomBoardData() {
        Integer boardSize = getRandomGridWidth(MIN_BOARD_WIDTH, MAX_BOARD_WIDTH);  // Define o tamanho do tabuleiro aleatoriamente
        Integer emptySlotIndex = ThreadLocalRandom.current().nextInt(boardSize);  // Define o índice do espaço vazio aleatoriamente

        // Cria uma lista de inteiros em posições aleatórias que contém o valor zero na posição final
        List<Integer> boardData = ThreadLocalRandom.current()
                .ints(boardSize - 1)
                .boxed()
                .collect(Collectors.toList());
        boardData.add(emptySlotIndex, 0);

        return boardData;
    }

    private Integer getRandomGridWidth(Integer minBoardWidth, Integer maxBoardWidth) {
        return ThreadLocalRandom.current().nextInt(minBoardWidth, maxBoardWidth + 1);
    }
}
