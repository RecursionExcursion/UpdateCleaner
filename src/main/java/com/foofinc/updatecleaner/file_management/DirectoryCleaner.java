package com.foofinc.updatecleaner.file_management;


import com.foofinc.updatecleaner.properties.PropertyManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectoryCleaner {

    private static final String[] ignoreFiles = PropertyManager.INSTANCE.getProperties().getIgnoreFiles();
    private static final String tempDirectoryName = PropertyManager.INSTANCE.getProperties().getTempDirectoryName();


    public static void clean() {
        clearParentDir();
        moveFilesToParentDir();
        deleteTempDir();
    }

    private static void clearParentDir() {

        File parentProjectDir = DirectoryManager.getParentProjectDirectory();

        File[] files = parentProjectDir.listFiles();

        if (files != null) {
            for (File file : files) {
                /*
                TODO DANGER!!!!
                   Uncomment out when deploying,
                   commented out protects workspace dir while in dev mode
                   DO NOT RUN PROJECT IN IDE WITH THIS CODE!!!!!
                 */
                if (fileIsNotIgnored(file)) {
                    try {
                        deleteFile(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private static void moveFilesToParentDir() {

        File[] repoFiles = getTempDir().listFiles();

        List<File> filesToMove = new ArrayList<>();

        if (repoFiles != null) {
            for (File repoFile : repoFiles) {
                File[] files = repoFile.listFiles();

                if (files != null) {
                    filesToMove.addAll(Arrays.asList(files));
                }
            }
        }

        if (!filesToMove.isEmpty()) {

            File parentProjectDir = DirectoryManager.getParentProjectDirectory();

            for (File file : filesToMove) {
                try {
                    if (fileIsNotIgnored(file)) {
                        String fileName = file.getName();
                        Files.move(file.toPath(), Paths.get(String.format("%s/%s", parentProjectDir, fileName)));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static boolean fileIsNotIgnored(File file) {
        for (String fileToIgnore : ignoreFiles) {
            if (file.toPath().endsWith(fileToIgnore)) {
                return false;
            }
        }
        return true;
    }

    private static void deleteTempDir() {
        try {
            deleteFile(getTempDir());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteFile(File file) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteFile(f);
                }
            }
        }
        Files.delete(file.toPath());
    }

    private static File getTempDir() {
        File[] files = DirectoryManager.getParentProjectDirectory().listFiles();

        try {
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory() && file.toPath().endsWith(tempDirectoryName)) {
                        return file;
                    }
                }
            }
            throw new FileNotFoundException("Temp Directory not found");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
