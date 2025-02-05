package org.example.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Window extends JFrame {

    public Window(String title, int width, int height, int x, int y){
        setTitle(title);
        setSize(width,height);
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Label label = new Label("press any key");
        add(label);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char keyPressed = e.getKeyChar();
                label.setText(String.valueOf(keyPressed));
            }
        });
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
    }

}
