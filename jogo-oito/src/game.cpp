#include <iostream>
#include <conio.h>
#include "game.h"

Game::Game() {
    _board = Table();
    _objective = Table();
    _isGameON = false;
    Reset();
    _freeSpace = Coord();
    _freeSpace.Set(1,1);
}

void Game::Place(int x, int y, int Value) {
    _board.Place(x, y, Value,('0' + Value));
    _objective.Place(x, y, Value,('0' + Value));
}

void Game::SetOuter() {
    for (int i = 0 ; i < 3 ; i++) {
        Place(0, i, (i + 1));
        Place(1, (2 - i), (2 * (2 + i)));
        Place(2, (2 - i), (5 + i));
    }
}

void Game::SetInner() {
    _board.Place(1, 1, 0, ' ');
    _objective.Place(1, 1, 0, ' ');
}

void Game::Reset() {
    SetOuter();
    SetInner();
}

char Game::GetPiece(int x, int y) {
    return _board.GetSpace(x, y).GetView();
}

int Game::GetValue(int x, int y) {
    return _board.GetSpace(x, y).GetValue();;
}

bool Game::Move(Coord input) {
    if (input.IsOnSide(_freeSpace)) {
        _board.Swap(input.GetX(), input.GetY(), _freeSpace.GetX(), _freeSpace.GetY());
        _freeSpace.Set( input.GetX(), input.GetY());
        return true;
    }else {
        return false;
    }
}

void Game::Shuffle() {
    srand(time(NULL));
    for (int i=0; i < 1000; i++){
        Coord input;
        input.Set(rand() % _board.GetSize(), rand() % _board.GetSize());
        Move(input);
    }
}

bool Game::IsWinPositon() {
    if (!_isGameON) return false;
    for (int i = 0; i < _board.GetSize(); i++) {
        for (int j = 0; j < _board.GetSize(); j++) {

            if (GetValue(i,j) != _objective.GetSpace(i,j).GetValue()) return false;
        }
    }
    _isGameON = false;
    return true;
}


//
// Created by Administrador on 24/07/2023.
//

#include "game.h"
