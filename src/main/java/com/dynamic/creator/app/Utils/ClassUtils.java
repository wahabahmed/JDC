package com.dynamic.creator.app.Utils;

import com.dynamic.creator.app.Model.PackageType;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wahaba on 25/05/2018.
 */
public class ClassUtils {

    private static List<String> listOfPackagesToCreate = Arrays.asList("model", "controller", "view", "dao");

    public static String getMainPackageAbsolutePath(String mainPackageName) {
        //Create Main Path to generate the .java file in project
        String userDirectory = System.getProperty("user.dir");
        String javaDirectory = userDirectory + "/src/main/java/";
        String mainPackageDirectory = javaDirectory + mainPackageName.replace('.', '/');

        return mainPackageDirectory;
    }

    public static String getFullClassAbsolutePath(String mainPackageName, String packageName, PackageType packageType, String className) {
        String mainPackageDirectory = getMainPackageAbsolutePath(mainPackageName);
        String packageNameToAdd = "";

        if (packageName != null && !packageName.isEmpty()) {
            packageNameToAdd = "/" + packageName + "/" + packageType.getPackageName() + "/";
        }

        String classPathToGenerate = mainPackageDirectory + packageNameToAdd + StringUtils.capitalize(className) + ".java";
        return classPathToGenerate;
    }

    public static String getFullClassAbsolutePathForOtherClasses(String mainPackageName, String packageName, PackageType packageType, String className) {
        String mainPackageDirectory = getMainPackageAbsolutePath(mainPackageName);
        String packageNameToAdd = "";

        if (packageName != null && !packageName.isEmpty()) {
            packageNameToAdd = "/" + packageName + "/" + packageType.getPackageName() + "/";
        }

        String classPathToGenerate = mainPackageDirectory + packageNameToAdd + StringUtils.capitalize(className) + ".java";
        return classPathToGenerate;
    }

    public static Boolean checkPackageExistsOtherwiseCreateIt(String mainPackageName, String packageName) {
        Boolean packageExist = false;
        String mainPackageDirectory = getMainPackageAbsolutePath(mainPackageName);
        String finalPackagePath = getMainPackageAbsolutePath(mainPackageName) + "/" + packageName + "/";
        if (packageName != null && !packageName.isEmpty()) {
            File file = new File(finalPackagePath);
            //Create Main Package
            if (!file.exists()) {
                file.mkdir();
            }

            //Create Sub Packages
            for (String subPackageToCreate : listOfPackagesToCreate) {
                System.out.println("Sub Directories = " + finalPackagePath + subPackageToCreate);
                File packageFolder = new File(finalPackagePath + subPackageToCreate);
                packageFolder.mkdir();
            }

            return file.exists();
        }


        //MODEL Package
        //DTO Package
        //Controller Package
        //View Package

        return false;
    }


}
