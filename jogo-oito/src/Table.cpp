//
// Created by Administrador on 23/08/2023.
//

#include "Table.h"

Table::Table() {
    for (int i = 0;i < TABLE_SIZE;i++) {
        for (int j = 0; j < TABLE_SIZE;j++) {
            _board[i][j] = Space();
        }
    }
}

int Table::GetSize() {
    return TABLE_SIZE;
}

void Table::Swap(int x, int y, int i, int j) {
    Space temp = _board[i][j];
    _board[i][j] = _board[x][y];
    _board[x][y] = temp;
}

void Table::Place(int x, int y, piece_t Value, char View) {
    _board[x][y].Set(Value, View);
}

Space Table::GetSpace(int x, int y) {
    return _board[x][y];
}
