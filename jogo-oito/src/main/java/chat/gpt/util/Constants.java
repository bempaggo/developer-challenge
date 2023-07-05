package chat.gpt.util;

import java.awt.Font;
import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    public static final int EMPTY = 0;

    public static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 36);
    public static final Font RESTART_BUTTON_FONT = new Font("Arial", Font.BOLD, 12);

    public static final int[][] DEFAULT_MODE = {
        {ONE, TWO, THREE},
        {FOUR, FIVE, SIX},
        {SEVEN, EIGHT, EMPTY}
    };

    public static final List<Integer> GAME_FINISHED = 
        Arrays.asList(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, EMPTY);
    
    public static final int GRID_LENGTH = 3;
    public static final int GRID_WIDTH = 3;
    public static final int GRID_AREA = GRID_LENGTH * GRID_WIDTH;
    
}
