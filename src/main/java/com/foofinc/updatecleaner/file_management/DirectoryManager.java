package com.foofinc.updatecleaner.file_management;

import java.io.File;

public class DirectoryManager {


    /**
     * @return Directory that the .exe exists in
     */
    public static File getMainAutoUpdaterDirectory() {
        return new File(System.getProperty("user.dir"));
    }

    /**
     * @return Directory inside the Directory that the .exe exists in
     * i.e.- auto_updater/temp
     */
    public static File getUpdaterTempDirectory() {
        return new File(String.format("%s/temp", getMainAutoUpdaterDirectory()));
    }

    /**
     * @return Parent Directory that the update_cleaner project exists in
     */
    public static File getParentProjectDirectory() {
        return getMainAutoUpdaterDirectory().getParentFile();
    }
}
