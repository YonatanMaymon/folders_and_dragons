package org.example.frontend.windows;

import org.example.frontend.guiComponants.StyledText;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class Window extends JFrame {
    StyledText text = new StyledText();

    Consumer<Character> on_key_pressed;
    public Window(String title, int width, int height, int x, int y, boolean scrollable){
        setTitle(title);
        setSize(width,height);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(scrollable) {
            JScrollPane scrollPane = new JScrollPane(text);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane);
        }
        else
            add(text);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                on_key_pressed.accept(e.getKeyChar());
            }
        });
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
    }

    public void set_on_key_pressed(Consumer<Character> on_key_pressed) {
        this.on_key_pressed = on_key_pressed;
    }
}
