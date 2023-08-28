//
// Created by Administrador on 23/08/2023.
//

#ifndef BEMPAGGO_DEVELOPER_CHALLENGE_TABLE_H
#define BEMPAGGO_DEVELOPER_CHALLENGE_TABLE_H

#include "Space.h"

class Table {
private:
    static const int TABLE_SIZE = 3;
    Space _board[TABLE_SIZE][TABLE_SIZE];
public:
    Table();
    void Swap(int x, int y, int i, int j);
    Space GetSpace(int x, int y);
    int GetSize();
    void Place(int x, int y, piece_t Value, char View);
};


#endif //BEMPAGGO_DEVELOPER_CHALLENGE_TABLE_H
