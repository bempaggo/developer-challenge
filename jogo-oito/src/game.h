//
// Created by Administrador on 24/07/2023.
//

#ifndef BEMPAGGO_DEVELOPER_CHALLENGE_GAME_H
#define BEMPAGGO_DEVELOPER_CHALLENGE_GAME_H

#include "Coord.h"
#include "Table.h"

class Game {
private:
    Coord _freeSpace;
    Table _board;
    Table _objective;
    void Reset();
    void Place(int x, int y, int Value);
    void SetOuter();
    void SetInner();
public:
    bool _isGameON;
    Game();
    char GetPiece(int x, int y);
    int GetValue(int x, int y);
    void Shuffle();
    bool IsWinPositon();
    bool Move(Coord input);
};

#endif //BEMPAGGO_DEVELOPER_CHALLENGE_GAME_H
