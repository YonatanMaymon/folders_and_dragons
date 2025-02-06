package org.example.frontend;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class FileExplorer {
    public void load_folder() {
        File folder = new File("C:\\"); // Change to your desired directory (e.g., "/home/user" on Linux/Mac)

        // Check if Desktop is supported on the current platform
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                // This will open the folder in the default file explorer
                desktop.open(folder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Desktop is not supported on your system.");
        }
    }
}

