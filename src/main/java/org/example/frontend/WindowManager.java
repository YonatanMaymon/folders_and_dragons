package org.example.frontend;

import java.awt.*;
import java.util.Arrays;

public class WindowManager {
    private final Rectangle usableBounds;

    public WindowManager(){
        usableBounds = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getMaximumWindowBounds();

    }

    public void load_windows(){
        load_gui_windows();
        load_folder_explorer();
    }

    private void load_folder_explorer(){
        String[][] blah = new String[9][16];

        // Fill the array with empty strings
        for (String[] strings : blah) {
            Arrays.fill(strings, "asd");
        }
        FileExplorer fileExplorer = new FileExplorer(blah);
        fileExplorer.load_folder();
        fileExplorer.open_folder();
    }

    private void load_gui_windows(){
        int playerWindowWidth = (int)(usableBounds.getWidth()*0.78 );
        int playerWindowHeight = (int)(usableBounds.getHeight() *0.19);
        int enemyWindowWidth = (int)(usableBounds.getWidth() - playerWindowWidth);
        int enemyWindowHeight = (int)usableBounds.getHeight();

        Window playerWindow = new Window(
                "Player Window",
                playerWindowWidth,
                playerWindowHeight,
                0,
                (int)usableBounds.getHeight()-playerWindowHeight
        );
        Window enemyWindow = new Window(
                "Enemy Window",
                enemyWindowWidth,
                enemyWindowHeight,
                playerWindowWidth ,
                0
        );
    }
}
