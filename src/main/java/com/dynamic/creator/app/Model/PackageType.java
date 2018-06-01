package com.dynamic.creator.app.Model;

/**
 * Created by wahaba on 28/05/2018.
 */
public enum PackageType {
    MODEL("model"),
    CONTROLLER("controller"),
    REST_CONTROLLER("restController"),
    DAO("dao"),
    REPO("repo"),
    VIEW("view");

    String packageName;

    PackageType(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }
}
