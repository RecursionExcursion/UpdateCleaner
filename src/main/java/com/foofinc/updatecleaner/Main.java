package com.foofinc.updatecleaner;

import com.foofinc.updatecleaner.file_management.DirectoryCleaner;
import com.foofinc.updatecleaner.file_management.DirectoryManager;
import com.foofinc.updatecleaner.properties.PropertyManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (DirectoryManager.getUpdaterTempDirectory().exists()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            DirectoryCleaner.clean();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            launchParentProject();
        }
        System.exit(0);
    }

    private static void launchParentProject() {

        String parentExe = PropertyManager.INSTANCE.getProperties().getParentExe();
        File[] files = DirectoryManager.getParentProjectDirectory().listFiles();

        try {
            if (files != null) {
                for (File file : files) {
                    if (file.toPath().endsWith(parentExe)) {
                        Desktop.getDesktop().open(file);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}