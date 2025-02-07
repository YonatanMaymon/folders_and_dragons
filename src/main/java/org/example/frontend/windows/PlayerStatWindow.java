package org.example.frontend.windows;

import java.util.Map;

public class PlayerStatWindow extends Window{
    public PlayerStatWindow( int width, int height, int x, int y) {
        super("Player Stats", width, height, x, y, false);
        text.set_font_size(20);

    }
    public void print_stats(Map<String,Integer> stats){
        text.setText("");
        for (String stat : stats.keySet()){
            text.append(stat + ": " + stats.get(stat) + ", ");
        }
    }
}
