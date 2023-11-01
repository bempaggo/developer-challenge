cmake_minimum_required(VERSION 3.22)
project(bempaggo_developer_challenge)

set(CMAKE_CXX_STANDARD 17)

set(CMAKE_EXE_LINKER_FLAGS "-static")

add_executable(bempaggo_developer_challenge src/main.cpp src/game.cpp src/game.h src/Coord.cpp src/Coord.h src/Space.cpp src/Space.h src/Table.cpp src/Table.h)
