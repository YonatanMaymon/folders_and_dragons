package org.example;

import org.example.frontend.Window;
import org.example.frontend.Window;
import org.example.frontend.WindowManager;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static Path get_main_path(){
        return Paths.get("src","main","java","org","example");
    }
    public static void main(String[] args) {
        WindowManager windowManager = new WindowManager();
        windowManager.load_windows();
    }
}