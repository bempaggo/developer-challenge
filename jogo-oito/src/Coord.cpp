//
// Created by Administrador on 23/08/2023.
//

#include <cstdlib>
#include "Coord.h"

Coord::Coord() {
    Set(0,0);
}

void Coord::Set(int i, int j) {
    X = i;
    Y = j;
}

int Coord::GetX() {
    return X;
}

int Coord::GetY() {
    return Y;
}

bool Coord::IsOnSide(Coord coord2) {
    return (abs(coord2.X - X) + abs(coord2.Y - Y) == 1);
}