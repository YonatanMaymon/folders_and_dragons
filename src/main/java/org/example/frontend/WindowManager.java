package org.example.frontend;

import java.awt.*;

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
        FileExplorer fileExplorer = new FileExplorer();
        fileExplorer.load_folder();
    }

    private void load_gui_windows(){
        int playerWindowWidth = (int)(usableBounds.getWidth()*0.7 );
        int playerWindowHeight = (int)(usableBounds.getHeight() *0.3);
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
