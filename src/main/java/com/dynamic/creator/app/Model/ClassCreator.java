package com.dynamic.creator.app.Model;

import java.util.HashMap;

public class ClassCreator {
    private String mainPackageName;
    private String packageName;
    private String authorName;
    private String version;
    private String className;
    private HashMap<String, String> variables;
    private String directoryPath;

    public ClassCreator(String mainPackageName, String packageName, String authorName, String version, String className, HashMap<String, String> variables, String directoryPath) {
        this.mainPackageName = mainPackageName;
        this.packageName = packageName;
        this.authorName = authorName;
        this.version = version;
        this.className = className;
        this.variables = variables;
        this.directoryPath = directoryPath;
    }

    public String getMainPackageName() {
        return mainPackageName;
    }

    public void setMainPackageName(String mainPackageName) {
        this.mainPackageName = mainPackageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public HashMap<String, String> getVariables() {
        return variables;
    }

    public void setVariables(HashMap<String, String> variables) {
        this.variables = variables;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }
}

