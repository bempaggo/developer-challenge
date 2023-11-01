//
// Created by Administrador on 23/08/2023.
//

#include "Space.h"

Space::Space() {
    _view = ' ';
    _value = 0;
}

void Space::Set (piece_t value, char view) {
    _value = value;
    _view = view;
};

piece_t Space::GetValue() {
    return _value;
}

char Space::GetView() {
    return _view;
}