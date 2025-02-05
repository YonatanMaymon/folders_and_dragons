package org.example;

import org.example.frontend.Window;
import org.example.frontend.Window;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Rectangle usableBounds = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getMaximumWindowBounds();

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