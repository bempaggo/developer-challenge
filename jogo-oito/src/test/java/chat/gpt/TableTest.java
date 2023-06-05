package chat.gpt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chat.gpt.domain.listeners.NotificationListener;
import chat.gpt.domain.table.Table;

public class TableTest {

    @Test
    @DisplayName("Test constructor should be populating table's cells.")
    void constructor_Shoulds_PopulatesCells() {
        // Arrange
        var notificationMock = mock(NotificationListener.class);
        Table table = new Table(notificationMock);
        Integer expectedSize = 9;
        // Act
        var cells = table.getBotoes();

        // Assert
        Assertions.assertEquals(cells.size(), expectedSize);
    }

    @Test
    @DisplayName("Test swap should be swaping table's cells.")
    void swap_ShouldsBeSwapingCells() {
        var notificationMock = mock(NotificationListener.class);
        Table table = new Table(notificationMock);

        var cells = table.getBotoes();

        var firstDiagonalCell = cells.get(0).getValue();
        var middleDiagonalCell = cells.get(4).getValue();

        table.swap(0, 0, 1, 1);
        assertEquals(middleDiagonalCell, table.getCell(0, 0).getValue());
        assertEquals(firstDiagonalCell, table.getCell(1, 1).getValue());

        var lastDiagonalCell = cells.get(8).getValue();
        firstDiagonalCell = table.getCell(0, 0).getValue();
        table.swap(0, 0, 2, 2);
        assertEquals(firstDiagonalCell, table.getCell(2, 2).getValue());
        assertEquals(lastDiagonalCell, table.getCell(0, 0).getValue());
    }

    @Test
    @Disabled("DISABLED")
    @DisplayName("Test swap shoulds notify GameFinish when table's cells was ordened.")
    void swap_Shoulds_Notify_GameFinish_When_CellsOrdened() {
    }
}
