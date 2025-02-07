package org.example.frontend;

import java.util.Arrays;

public class FrontUtil {
    public static String[][] get_game_over_map(){
        String[][] grid = new String[9][16];
        for (String[] strings : grid)
            Arrays.fill(strings, "game over");
        return grid;
    }
}
