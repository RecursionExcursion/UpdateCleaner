package com.foofinc.updatecleaner.properties;

public class Properties {

    private double version;

    private String[] ignoreFiles;

    private String tempDirectoryName;

    private String parentExe;

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public String[] getIgnoreFiles() {
        return ignoreFiles;
    }

    public void setIgnoreFiles(String[] ignoreFiles) {
        this.ignoreFiles = ignoreFiles;
    }

    public String getTempDirectoryName() {
        return tempDirectoryName;
    }

    public void setTempDirectoryName(String tempFileName) {
        this.tempDirectoryName = tempFileName;
    }

    public String getParentExe() {
        return parentExe;
    }

    public void setParentExe(String parentExe) {
        this.parentExe = parentExe;
    }
}
