package org.example.frontend;
import org.example.Main;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileExplorer {
    File folder = new File(Main.get_main_path().toString(),"game");
    File image_folder = new File(Main.get_main_path().toString(), "images");
    public FileExplorer(){
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (!created) {
                System.err.println("Failed to create destination folder.");
            }
        }
    }

    private String get_new_name(int x, int y){
        return x + "_" + y + ".jpg";
    }
    Path get_image_path(String fileName){
        return new File(image_folder, fileName+ ".jpg").toPath();
    }

    public void load_folder(String[][] tileMap) {
        for(int i= 0; i< tileMap.length;i++){
            for (int j = 0; j<tileMap[i].length; j++){
                Path sourcePath = get_image_path(tileMap[i][j]);
                if (!Files.exists(sourcePath))
                    sourcePath = get_image_path("floor");
                Path destPath = new File(folder,get_new_name(i,j)).toPath();
                try {
                    Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void open_folder(){
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(folder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Desktop is not supported on your system.");
        }
    }
}

