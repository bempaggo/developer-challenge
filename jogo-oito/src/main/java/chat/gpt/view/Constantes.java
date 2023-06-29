package chat.gpt.view;

import java.awt.Font;

public class Constantes {

    public final static int UM = 1;
    public final static int DOIS = 2;
    public final static int TRES = 3;
    public final static int QUATRO = 4;
    public final static int CINCO = 5;
    public final static int SEIS = 6;
    public final static int SETE = 7;
    public final static int OITO = 8;
    public final static int VAZIO = 0;

    public final static int LINHA_UM = 0;
    public final static int LINHA_DOIS = 1;
    public final static int LINHA_TRES = 2;

    public final static int COLUNA_UM = 0;
    public final static int COLUNA_DOIS = 1;
    public final static int COLUNA_TRES = 2;

    public static final int[] MOVE_UP = {1, 0};
    public static final int[] MOVE_DOWN = {-1, 0};
    public static final int[] MOVE_LEFT = {0, 1};
    public static final int[] MOVE_RIGHT = {0, -1};
    public static final int[] MOVE_INVALIDO = {-1, -1};

    public static final Font FONTE_PADRAO = new Font("Arial", Font.BOLD, 36);

    public static final int[][] DIFICULDADE_PADRAO = {
        {UM, DOIS, TRES},
        {QUATRO, CINCO, SEIS},
        {SETE, OITO, VAZIO}
    };

    public static final int[][] JOGO_CONCLUIDO = {
        {UM, DOIS, TRES},
        {QUATRO, CINCO, SEIS},
        {SETE, OITO, VAZIO}
    };
    
    public static final int boardLength = 3;
    public static final int boardWidth = 3;
    public static final int boardArea = boardLength * boardWidth;
    
}
