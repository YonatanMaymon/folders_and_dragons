package org.example.frontend.windows;

import org.example.backend.Tiles.Player;

import java.util.List;

public class CharacterSelectionWindow extends Window{
    public CharacterSelectionWindow(String title, int width, int height, int x, int y, List<Player> playerList) {
        super(title, width, height, x, y,false);
        text.set_font_size(24);
        int i = 0;
        text.add_message("press the number of the character you want:");
        for (Player player : playerList){
            String sb = i + " - "+player.get_name() + " - Health: " +
                    player.health.get_resource_pool() +
                    ", Attack: " +
                    player.get_attackPoints() +
                    ", Defence: " +
                    player.get_defencePoints();
            i++;
            text.add_message(sb);
        }
    }

}
