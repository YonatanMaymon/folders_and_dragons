package org.example.frontend;

import org.example.backend.Tiles.Player;
import org.example.frontend.windows.CharacterSelectionWindow;
import org.example.frontend.windows.EnemyDataWindow;
import org.example.frontend.windows.PlayerStatWindow;
import org.example.frontend.windows.Window;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class WindowManager {
    private final Rectangle usableBounds;
    public PlayerStatWindow playerWindow;
    public EnemyDataWindow enemyWindow;
    FileExplorer fileExplorer = new FileExplorer();
    public WindowManager(){
        usableBounds = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getMaximumWindowBounds();
    }
    public void load_character_selection(Consumer<Integer> on_input, List<Player> players){
        CharacterSelectionWindow character_selection_window = new CharacterSelectionWindow(
                "Character Selection",
                (int) usableBounds.getWidth()/2,
                (int) usableBounds.getHeight()/2,
                (int) usableBounds.getWidth()/4,
                (int) usableBounds.getHeight()/4,
                players
        );
        character_selection_window.set_on_key_pressed(input ->{
            if (Character.isDigit(input)&&Character.getNumericValue(input)<= players.size()) {
                on_input.accept(Character.getNumericValue(input));
                character_selection_window.dispose();
            }
        });
    }
    public void load_windows(){
        load_gui_windows();
        load_folder_explorer();
    }
    public void update(String[][] tileNames){
        fileExplorer.load_folder(tileNames);
    }

    private void load_folder_explorer(){
        fileExplorer.open_folder();
    }

    private void load_gui_windows(){
        int playerWindowWidth = (int)(usableBounds.getWidth()*0.79 );
        int playerWindowHeight = (int)(usableBounds.getHeight() *0.19);
        int enemyWindowWidth = (int)(usableBounds.getWidth() - playerWindowWidth);
        int enemyWindowHeight = (int)usableBounds.getHeight();

        playerWindow = new PlayerStatWindow(
                playerWindowWidth,
                playerWindowHeight,
                0,
                (int)usableBounds.getHeight()-playerWindowHeight
        );
        enemyWindow = new EnemyDataWindow(
                "Enemy Window",
                enemyWindowWidth,
                enemyWindowHeight,
                playerWindowWidth ,
                0
        );
    }

    public void set_on_key_press(Consumer<Character> on_key_press) {
        playerWindow.set_on_key_pressed(on_key_press);
        enemyWindow.set_on_key_pressed(on_key_press);
    }

}
