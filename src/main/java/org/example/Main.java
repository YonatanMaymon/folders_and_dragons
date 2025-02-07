package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static Path get_main_path(){
        return Paths.get("src","main","java","org","example");
    }
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
    }
}