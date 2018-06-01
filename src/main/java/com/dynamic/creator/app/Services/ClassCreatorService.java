package com.dynamic.creator.app.Services;

import com.dynamic.creator.app.Model.ClassCreator;
import com.dynamic.creator.app.Model.ClassOptions;
import com.dynamic.creator.app.Model.PackageType;
import com.dynamic.creator.app.Utils.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

@Service
public class ClassCreatorService {

    ClassCreatorService() {
        initCreatorService();
    }

    public void initCreatorService() {
        /*String mainPackageName = "com.dynamic.creator.app";
        String newPackageName = "accounts";
        String authorName = "wahab";
        String version = "1.0";
        String className = "test";
        HashMap<String, String> variables = new HashMap<String, String>();
        variables.put("id", "int");
        variables.put("name", "String");
        variables.put("Description", "String");

        ClassCreator ClassCreator = new ClassCreator(mainPackageName, newPackageName, authorName, version, className, variables);

        ClassOptions classOptions = new ClassOptions(true, true, true, true);
        System.out.println(createClass(ClassCreator, classOptions));*/
    }

    public StringBuilder createClass(ClassCreator classCreator, ClassOptions classOptions) {
        StringBuilder sourceCode = new StringBuilder();

        String mainPackageName = classCreator.getMainPackageName();
        String newPackageName = classCreator.getPackageName();
        String authorName = classCreator.getAuthorName();
        String version = classCreator.getVersion();
        String className = classCreator.getClassName();
        HashMap<String, String> variables = classCreator.getVariables();
        String directoryPath = classCreator.getDirectoryPath();

        //Useful Variables
        String restClassName = className + StringUtils.capitalize(PackageType.REST_CONTROLLER.getPackageName());
        String controllerClassName = className + StringUtils.capitalize(PackageType.CONTROLLER.getPackageName());
        String repoClassName = className + StringUtils.capitalize(PackageType.REPO.getPackageName());
        String viewClassName = className + StringUtils.capitalize(PackageType.VIEW.getPackageName());

        //Entity
        if (classOptions.isCreateModel()) {
            StringBuilderUtils.createPackage(directoryPath, mainPackageName, newPackageName);
            StringBuilderUtils.importPackage(sourceCode, mainPackageName, newPackageName + "." + PackageType.MODEL.getPackageName());
            StringBuilderUtils.importRestPackage(sourceCode);
            StringBuilderUtils.createClassJavaDocs(sourceCode, authorName, version);
            StringBuilderUtils.createClassStartSyntax(sourceCode, className, true);
            StringBuilderUtils.createVariables(sourceCode, variables);
            StringBuilderUtils.createConstructor(sourceCode, className, variables);
            StringBuilderUtils.createMethods(sourceCode, variables);
            StringBuilderUtils.createClassEndSyntax(sourceCode);
            StringBuilderUtils.generateClass(ClassUtils.getFullClassAbsolutePath(directoryPath, mainPackageName, newPackageName, PackageType.MODEL, className), sourceCode);
        }

        if (classOptions.isCreateControllers()) {
            //Rest Controller
            sourceCode = new StringBuilder();
            StringBuilderUtils.createPackage(directoryPath, mainPackageName, newPackageName);
            StringBuilderUtils.importPackage(sourceCode, mainPackageName, newPackageName + "." + PackageType.CONTROLLER.getPackageName());
            RestControllerStringBuilderUtils.importClass(sourceCode, mainPackageName, newPackageName, PackageType.MODEL, className);
            //RestControllerStringBuilderUtils.importClass(sourceCode, mainPackageName, newPackageName, PackageType.CONTROLLER, controllerClassName);
            RestControllerStringBuilderUtils.importRestPackage(sourceCode);
            RestControllerStringBuilderUtils.createClassJavaDocs(sourceCode, authorName, version);
            RestControllerStringBuilderUtils.createClassStartSyntax(sourceCode, StringUtils.capitalize(restClassName));
            RestControllerStringBuilderUtils.createVariables(sourceCode, className, PackageType.CONTROLLER);
            RestControllerStringBuilderUtils.createMethods(sourceCode, className);
            RestControllerStringBuilderUtils.createClassEndSyntax(sourceCode);
            StringBuilderUtils.generateClass(ClassUtils.getFullClassAbsolutePathForOtherClasses(directoryPath, mainPackageName, newPackageName, PackageType.CONTROLLER, restClassName), sourceCode);


            //Controller
            sourceCode = new StringBuilder();
            StringBuilderUtils.createPackage(directoryPath, mainPackageName, newPackageName);
            StringBuilderUtils.importPackage(sourceCode, mainPackageName, newPackageName + "." + PackageType.CONTROLLER.getPackageName());
            ControllerStringBuilderUtils.importClass(sourceCode, mainPackageName, newPackageName, PackageType.MODEL, className);
            ControllerStringBuilderUtils.importClass(sourceCode, mainPackageName, newPackageName, PackageType.DAO, repoClassName);
            ControllerStringBuilderUtils.importRestPackage(sourceCode);
            ControllerStringBuilderUtils.createClassJavaDocs(sourceCode, authorName, version);
            ControllerStringBuilderUtils.createClassStartSyntax(sourceCode, StringUtils.capitalize(controllerClassName));
            ControllerStringBuilderUtils.createVariables(sourceCode, className, PackageType.REPO);
            ControllerStringBuilderUtils.createMethods(sourceCode, className);
            ControllerStringBuilderUtils.createClassEndSyntax(sourceCode);
            StringBuilderUtils.generateClass(ClassUtils.getFullClassAbsolutePathForOtherClasses(directoryPath, mainPackageName, newPackageName, PackageType.CONTROLLER, controllerClassName), sourceCode);
        }

        //REPO
        if (classOptions.isCreateRepository()) {
            sourceCode = new StringBuilder();
            StringBuilderUtils.createPackage(directoryPath, mainPackageName, newPackageName);
            StringBuilderUtils.importPackage(sourceCode, mainPackageName, newPackageName + "." + PackageType.DAO.getPackageName());
            RepositoryStringBuilderUtils.importRestPackage(sourceCode);
            RepositoryStringBuilderUtils.importClass(sourceCode, mainPackageName, newPackageName, PackageType.MODEL, className);
            //RepositoryStringBuilderUtils.importClass(sourceCode, mainPackageName, newPackageName, PackageType.DAO, className + "Repo");
            RepositoryStringBuilderUtils.createClassJavaDocs(sourceCode, authorName, version);
            RepositoryStringBuilderUtils.createClassStartSyntax(sourceCode, StringUtils.capitalize(className));
            //RepositoryStringBuilderUtils.createVariables(sourceCode, className, PackageType.REPO);
            RepositoryStringBuilderUtils.createMethods(sourceCode, className);
            RepositoryStringBuilderUtils.createClassEndSyntax(sourceCode);
            StringBuilderUtils.generateClass(ClassUtils.getFullClassAbsolutePathForOtherClasses(directoryPath, mainPackageName, newPackageName, PackageType.DAO, repoClassName), sourceCode);
        }

        if (classOptions.isCreateView()) {
            //View
            sourceCode = new StringBuilder();
            StringBuilderUtils.createPackage(directoryPath, mainPackageName, newPackageName);
            StringBuilderUtils.importPackage(sourceCode, mainPackageName, newPackageName + "." + PackageType.VIEW.getPackageName());
            StringBuilderUtils.createClassJavaDocs(sourceCode, authorName, version);
            StringBuilderUtils.createClassStartSyntax(sourceCode, viewClassName, false);
            StringBuilderUtils.createClassEndSyntax(sourceCode);
            StringBuilderUtils.generateClass(ClassUtils.getFullClassAbsolutePath(directoryPath, mainPackageName, newPackageName, PackageType.VIEW, viewClassName), sourceCode);
        }

        return sourceCode;
    }

    public static void main(String[] args) {
        new ClassCreatorService();
        System.exit(5);
    }
}
