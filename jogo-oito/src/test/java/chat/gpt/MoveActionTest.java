package chat.gpt;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chat.gpt.domain.actions.MoveAction;
import chat.gpt.domain.table.Table;
import chat.gpt.domain.table.TableCell;

public class MoveActionTest {
    
    @Test
    @DisplayName("Test move action delegate when valid move swaps cells.")
    void testMoveActionDelegate_ValidMove(){
        // Arrange
        Table tableMock = mock(Table.class);

        TableCell tableCellMock =mock(TableCell.class);

        when(tableCellMock.isEmpty())
        .thenReturn(true)
        .thenReturn(false);
        
        when(tableMock.getCell(anyInt(), anyInt())).thenReturn(tableCellMock);

        doNothing().when(tableMock).swap(anyInt(), anyInt(),anyInt(),anyInt());

        //Action
        MoveAction move = new MoveAction(tableMock, 0,1);
        move.doAction(0);

        //Asserts
        verify(tableCellMock, atLeastOnce()).isEmpty();
        verify(tableMock, atLeastOnce()).swap(anyInt(), anyInt(),anyInt(),anyInt());
    }

    @Test
    @DisplayName("Test move action delegate when invalid move shoulds do nothing.")
    void testMoveActionDelegate_InValidMove(){
        // Arrange
        Table tableMock = mock(Table.class);

        TableCell tableCellMock = mock(TableCell.class);

        when(tableCellMock.isEmpty())
        .thenReturn(true)
        .thenReturn(false);
        
        when(tableMock.getCell(anyInt(), anyInt())).thenReturn(tableCellMock);

        doNothing().when(tableMock).swap(anyInt(), anyInt(),anyInt(),anyInt());

        // Action
        MoveAction move = new MoveAction(tableMock, 999,999);
        move.doAction(0);

        //Asserts

        verify(tableCellMock, atLeastOnce()).isEmpty();
        verify(tableMock, never()).swap(anyInt(), anyInt(),anyInt(),anyInt());
    }
}
