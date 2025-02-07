package org.example.frontend.guiComponants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StyledText extends JTextArea {
    public StyledText(){
        setEditable(false);
        setFocusable(false);
        set_font_size(12);
        setForeground(new Color(255, 215, 0)); // Golden text
        setBackground(new Color(40, 40, 40)); // Dark theme like a terminal
    }
    public void add_dividing_line(){
        append("-------------------------------------------------------------------------------------------------\n");
    }
    public void set_font_size(int size){
        setFont(new Font("Serif", Font.PLAIN, size));
    }
    public void add_message( String message) {
        append( "   " +message + "\n");
    }
    public void add_dashed_message(String message){
        add_message("---" + message + "---");
    }

}
