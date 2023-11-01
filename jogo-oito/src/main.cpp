#include "game.h"
#include <windows.h>
#include <cstdio>
#include <commctrl.h>

#define tableBT_0 400
#define menuBT_0 500

Game Jogo_8 = Game();

const char g_szClassName[] = "myWindowClass";

void CreateTable(HWND hwnd, int positionX, int positionY, int buttonCode) {
    int table[3][3];
    for (int i=0;i<=2;i++) {
        for (int j=0;j<=2;j++){
            char buffer[2] = {Jogo_8.GetPiece(i,j), '\0'};
            HWND hwndtableButton = CreateWindow(
                    "BUTTON",                                               // Predefined class; Unicode assumed
                    buffer,                                                 // Button text
                    WS_TABSTOP | WS_VISIBLE | WS_CHILD | BS_DEFPUSHBUTTON,  // Styles
                    (positionX + (100 * j)),                                // x position
                    (positionY +(100 * i)),                                 // y position
                    100,                                                    // Button width
                    100,                                                    // Button height
                    hwnd,                                                   // Parent window
                    (HMENU)(buttonCode + (i*10) + j),                       // No menu.
                    (HINSTANCE)GetWindowLongPtr(hwnd, GWLP_HINSTANCE),
                    NULL);                                                  // Pointer not needed.
        }
    }
}

void UpdateTable(HWND hwnd) {
    int table[3][3];
    for (int i=0;i<=2;i++) {
        for (int j=0;j<=2;j++){
            char buffer[2] = {Jogo_8.GetPiece(i,j), '\0'};
            SetDlgItemText(hwnd, (tableBT_0 + (i*10) + j), buffer);
        }
    }
}

void CreateMenu(HWND hwnd, int positionX, int positionY, int buttonCode) {
    HWND hwndMenuButton = CreateWindow(
            "BUTTON",                                               // Predefined class; Unicode assumed
            "INICIAR",                                              // Button text
            WS_TABSTOP | WS_VISIBLE | WS_CHILD | BS_DEFPUSHBUTTON,  // Styles
            (positionX),                                            // x position
            (positionY),                                            // y position
            110,                                                     // Button width
            30,                                                     // Button height
            hwnd,                                                   // Parent window
            (HMENU)(buttonCode),                                    // No menu.
            (HINSTANCE)GetWindowLongPtr(hwnd, GWLP_HINSTANCE),
            NULL);                                                  // Pointer not needed.
}


LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam)
{
    switch(msg)
    {
        case WM_COMMAND:
            if (wParam == menuBT_0) {
                if (!Jogo_8._isGameON) {
                    SetDlgItemText(hwnd, menuBT_0, "EMBARALHAR");
                    Jogo_8._isGameON = true;
                }
                Jogo_8.Shuffle();
                UpdateTable(hwnd);
            }else {
                if (Jogo_8._isGameON){
                    Coord input;
                    input.Set((wParam - 400)/10 , (wParam-400)%10);
                    Jogo_8.Move(input);
                    UpdateTable(hwnd);
                    if (Jogo_8.IsWinPositon()) {
                        MessageBoxA(hwnd,"PARABENS! VOCE GANHOU!", "VITORIA",MB_OK|MB_ICONEXCLAMATION);
                        Jogo_8._isGameON = false;
                        SetDlgItemText(hwnd, menuBT_0, "REINICIAR");
                        UpdateTable(hwnd);
                    }
                }
            }
            break;
        case WM_CLOSE:
            DestroyWindow(hwnd);
            break;
        case WM_DESTROY:
            PostQuitMessage(0);
            break;
        default:
            return DefWindowProc(hwnd, msg, wParam, lParam);
    }
    return 0;
}

int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow) {
    WNDCLASSEX wc;
    HWND hwnd;
    MSG Msg;
    wc.cbSize        = sizeof(WNDCLASSEX);
    wc.style         = 0;
    wc.lpfnWndProc   = WndProc;
    wc.cbClsExtra    = 0;
    wc.cbWndExtra    = 0;
    wc.hInstance     = hInstance;
    wc.hIcon         = LoadIcon(NULL, IDI_APPLICATION);
    wc.hCursor       = LoadCursor(NULL, IDC_ARROW);
    wc.hbrBackground = (HBRUSH)(COLOR_WINDOW+1);
    wc.lpszMenuName  = NULL;
    wc.lpszClassName = g_szClassName;
    wc.hIconSm       = LoadIcon(NULL, IDI_APPLICATION);
    if(!RegisterClassEx(&wc)) {
        MessageBox(NULL, "Window Registration Failed!", "Error!",
                   MB_ICONEXCLAMATION | MB_OK);
        return 0;
    }
    hwnd = CreateWindowEx(
            WS_EX_CLIENTEDGE,
            g_szClassName,
            "8 PUZZLE",
            WS_OVERLAPPEDWINDOW,
            CW_USEDEFAULT, CW_USEDEFAULT, 600, 400,
            NULL, NULL, hInstance, NULL);
    CreateTable(hwnd, 40, 30, tableBT_0);
    CreateMenu(hwnd, 420, 30, menuBT_0);
    if(hwnd == NULL) {
        MessageBox(NULL, "Falha ao criar janela!", "Erro!",MB_ICONEXCLAMATION | MB_OK);
        return 0;
    }
    ShowWindow(GetConsoleWindow(), SW_HIDE);
    ShowWindow(hwnd, nCmdShow);
    UpdateWindow(hwnd);
    MessageBox(hwnd, "retorne o tabuleiro para o estado inicial", "COMO JOGAR", MB_ICONQUESTION |MB_OK);
    while(GetMessage(&Msg, NULL, 0, 0) > 0) {
        TranslateMessage(&Msg);
        DispatchMessage(&Msg);
    }
    return Msg.wParam;
}