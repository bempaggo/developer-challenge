//
// Created by Administrador on 23/08/2023.
//

#ifndef BEMPAGGO_DEVELOPER_CHALLENGE_COORD_H
#define BEMPAGGO_DEVELOPER_CHALLENGE_COORD_H


class Coord {
private:
    int X;
    int Y;
public:
    Coord();
    void Set(int i, int j);
    int GetX();
    int GetY();
    bool IsOnSide(Coord coord2);
};


#endif //BEMPAGGO_DEVELOPER_CHALLENGE_COORD_H
