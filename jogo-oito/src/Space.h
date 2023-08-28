//
// Created by Administrador on 23/08/2023.
//

#ifndef BEMPAGGO_DEVELOPER_CHALLENGE_SPACE_H
#define BEMPAGGO_DEVELOPER_CHALLENGE_SPACE_H

typedef int piece_t;

class Space {
private:
    piece_t _value;
    char _view;
public:
    Space();
    void Set(piece_t value, char view);
    char GetView();
    piece_t GetValue();
};


#endif //BEMPAGGO_DEVELOPER_CHALLENGE_SPACE_H
