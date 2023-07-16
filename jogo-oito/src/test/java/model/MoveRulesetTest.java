package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MoveRulesetTest {

    private MoveRuleset moveRuleset;

    @Mock
    private BoardInterface grid;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        moveRuleset = new MoveRuleset();
        moveRuleset.setGrid(grid);
    }

    // setado pro null estar no 4 para sempre ser possível movimentar
    @Test
    void testMoveUp() {
        when(grid.getEmptySlotIndex()).thenReturn(4);
        when(grid.getBoardWidth()).thenReturn(3);
        when(grid.getBoardData()).thenReturn(Arrays.asList(1, 2, 3, 4, 0, 5, 6, 7, 8));

        moveRuleset.moveUp();

        verify(grid,times(3)).getEmptySlotIndex();
        verify(grid).getBoardWidth();
        verify(grid,times(3)).getBoardData();
    }

    @Test
    void testMoveDown() {
        when(grid.getEmptySlotIndex()).thenReturn(4);
        when(grid.getBoardWidth()).thenReturn(3);
        when(grid.getBoardData()).thenReturn(Arrays.asList(1, 2, 3, 4, 0, 5, 6, 7, 8));

        moveRuleset.moveDown();

        verify(grid, times(3)).getEmptySlotIndex();
        verify(grid).getBoardWidth();
        verify(grid,times(3)).getBoardData();
    }

    @Test
    void testMoveLeft() {
        when(grid.getEmptySlotIndex()).thenReturn(4);
        when(grid.getBoardData()).thenReturn(Arrays.asList(1, 2, 3, 4, 0, 5, 6, 7, 8));

        moveRuleset.moveLeft();

        verify(grid, times(3)).getEmptySlotIndex();
        verify(grid, times(3)).getBoardData();
    }

    @Test
    void testMoveRight() {
        when(grid.getEmptySlotIndex()).thenReturn(4);
        when(grid.getBoardData()).thenReturn(Arrays.asList(1, 2, 3, 0, 4, 5, 6, 7, 8));

        moveRuleset.moveRight();

        verify(grid, times(3)).getEmptySlotIndex();
        verify(grid, times(3)).getBoardData();
    }

    @Test
    void testMoveInvalidButtonValue() {
        when(grid.getBoardData()).thenReturn(Arrays.asList(1, 2, 0));

        assertThrows(IndexOutOfBoundsException.class,
                () -> moveRuleset.move(10));
    }
}
