package chat.gpt;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import chat.gpt.domain.actions.MouseMoveAction;
import chat.gpt.domain.table.Table;
import chat.gpt.domain.table.TableCell;

public class MouseMoveActionTest {

    @Test
    public void mouseMove_Shoulds_notAllowDiagonalMove() {
        // Arrange
        // Arrange
        var table = mock(Table.class);
        var cell = mock(TableCell.class);

        ArrayList<TableCell> cells = new ArrayList<>();

        for (Integer i = 0; i < 9; ++i) {
            var aux = new TableCell();
            aux.setValue(i);
            cells.add(aux);
        }

        when(table.getCells()).thenReturn(cells);
        when(cell.getValue()).thenReturn(8);
        var mouse = new MouseMoveAction(table);

        // Act
        mouse.doAction(cell);

        // Asserts
        verify(table, times(0)).swap(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    public void mouseMove_Shoulds_AllowsVerticallMove() {
        // Arrange
        var table = mock(Table.class);
        var cell = mock(TableCell.class);

        ArrayList<TableCell> cells = new ArrayList<>();

        for (Integer i = 0; i < 9; ++i) {
            var aux = new TableCell();
            aux.setValue(i);
            cells.add(aux);
        }

        when(table.getCells()).thenReturn(cells);
        when(cell.getValue()).thenReturn(3);
        var mouse = new MouseMoveAction(table);

        // Act
        mouse.doAction(cell);

        // Asserts
        verify(table, times(1)).swap(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    public void mouseMove_Shoulds_AllowsHorizontalMove() {
        // Arrange
        var table = mock(Table.class);
        var cell = mock(TableCell.class);

        ArrayList<TableCell> cells = new ArrayList<>();

        for (Integer i = 0; i < 9; ++i) {
            var aux = new TableCell();
            aux.setValue(i);
            cells.add(aux);
        }

        when(table.getCells()).thenReturn(cells);
        when(cell.getValue()).thenReturn(1);
        var mouse = new MouseMoveAction(table);

        // Act
        mouse.doAction(cell);

        // Asserts
        verify(table, times(1)).swap(anyInt(), anyInt(), anyInt(), anyInt());
    }
}
