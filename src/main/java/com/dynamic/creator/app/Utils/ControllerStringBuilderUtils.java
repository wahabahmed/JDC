package com.dynamic.creator.app.Utils;

import com.dynamic.creator.app.Model.PackageType;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wahaba on 28/05/2018.
 */
public class ControllerStringBuilderUtils {

    private static List<String> listOfRestApis = Arrays.asList("get", "list", "edit", "create", "delete");

    /***
     * This method should add mainPackage and newPackage in the class sourcecode
     *
     * @param sourceCode      StringBuilder in which we need to append the code
     * @param mainPackageName Root package of the project like com.a.b.c
     * @param newPackageToAdd New package name that needs to be used
     */
    public static void importClass(StringBuilder sourceCode, String mainPackageName, String newPackageToAdd, PackageType packageType, String className) {
        String finalPackageName = mainPackageName;

        if (newPackageToAdd != null && !newPackageToAdd.isEmpty()) {
            finalPackageName += "." + newPackageToAdd + "." + packageType.getPackageName() + "." + StringUtils.capitalize(className);
        }

        sourceCode.append("import " + finalPackageName + ";\n");
    }

    /***
     * This method should add mainPackage and newPackage in the class sourcecode
     *
     * @param sourceCode StringBuilder in which we need to append the code
     */
    public static void importRestPackage(StringBuilder sourceCode) {
        sourceCode.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        sourceCode.append("import org.springframework.stereotype.Controller;\n\n");

        sourceCode.append("import java.util.List;\n");
        sourceCode.append("import com.google.common.collect.Lists;\n\n");
    }

    /***
     * @param sourceCode StringBuilder in which we need to append the code
     * @param authorName String name of the builder
     * @param version    version of the project
     */
    public static void createClassJavaDocs(StringBuilder sourceCode, String authorName, String version) {
        sourceCode.append("/***\n" +
                " * @author " + authorName + "\n" +
                " * @since " + version + "\n" +
                " */\n");
    }

    /***
     * @param sourceCode StringBuilder in which we need to append the code
     * @param className  String classnName that we need to create
     */
    public static void createClassStartSyntax(StringBuilder sourceCode, String className) {
        sourceCode.append("@Controller\n");
        sourceCode.append("public class " + className + " {\n");
    }

    /***
     * @param sourceCode
     * @param className
     */

    public static void createVariables(StringBuilder sourceCode, String className, PackageType packageType) {
        String stringToAdd = StringUtils.capitalize(packageType.getPackageName());
        sourceCode.append("    @Autowired \n" +
                "    private " + StringUtils.capitalize(className) + stringToAdd + " " + className + stringToAdd + "; \n\n");
    }

    /***
     * @param sourceCode
     * @param className
     */
    public static void createMethods(StringBuilder sourceCode, String className) {
        String classNameStartWithCapitalLetter = StringUtils.capitalize(className);
        String repoClassNameToAdd = StringUtils.capitalize(PackageType.REPO.getPackageName());

        //Get
        sourceCode.append("    public " + classNameStartWithCapitalLetter + " get" + classNameStartWithCapitalLetter + "(String sessionKey, String id) {\n" +
                "        return " + className + repoClassNameToAdd + ".findOne(id);\n" +
                "    }\n\n");

        //List
        sourceCode.append("    public List<" + classNameStartWithCapitalLetter + "> list" + classNameStartWithCapitalLetter + "(String sessionKey) {\n" +
                "        return Lists.newArrayList(" + className + repoClassNameToAdd + ".findAll());\n" +
                "    }\n\n");

        //Create
        sourceCode.append("    public " + classNameStartWithCapitalLetter + " create" + classNameStartWithCapitalLetter + "(String sessionKey, " + classNameStartWithCapitalLetter + " " + className + ") " +
                "{\n" +
                "        return " + className + repoClassNameToAdd + ".save(" + className + ");\n" +
                "    }\n\n");

        //Edit
        sourceCode.append("    public " + classNameStartWithCapitalLetter + " edit" + classNameStartWithCapitalLetter + "(String sessionKey, " + classNameStartWithCapitalLetter + " " + className + ")" +
                "    {\n" +
                "        return " + className + repoClassNameToAdd + ".save(" + className + ");\n" +
                "    }\n\n");

        //Delete
        sourceCode.append("    public void delete" + classNameStartWithCapitalLetter + "(String sessionKey, " + classNameStartWithCapitalLetter + " " + className + ") " +
                "    {\n" +
                "        " + className + repoClassNameToAdd + ".delete(" + className + ");\n" +
                "    }\n");
    }

    /***
     * @param sourceCode StringBuilder in which we need to append the code
     */
    public static void createClassEndSyntax(StringBuilder sourceCode) {
        sourceCode.append("}");
    }
}
