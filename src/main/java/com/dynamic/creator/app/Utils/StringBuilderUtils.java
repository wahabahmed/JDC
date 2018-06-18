package com.dynamic.creator.app.Utils;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StringBuilderUtils {

    public static void createPackage(String directoryPath, String mainPackageName, String packageName) {
        Boolean result = ClassUtils.checkPackageExistsOtherwiseCreateIt(directoryPath, mainPackageName, packageName);
        System.out.println("PackageExistsOrCreated? " + result);
    }

    /***
     * This method should add mainPackage and newPackage in the class sourcecode
     *
     * @param sourceCode      StringBuilder in which we need to append the code
     * @param mainPackageName Root package of the project like com.a.b.c
     * @param newPackageToAdd New package name that needs to be used
     */
    public static void importPackage(StringBuilder sourceCode, String mainPackageName, String newPackageToAdd) {
        String finalPackageName = mainPackageName;

        if (newPackageToAdd != null && !newPackageToAdd.isEmpty()) {
            finalPackageName += "." + newPackageToAdd;
        }

        sourceCode.append("package " + finalPackageName + ";\n\n");
    }

    /***
     * This method should import rest classes in the class sourcecode
     *
     * @param sourceCode StringBuilder in which we need to append the code
     */
    public static void importRestClasses(StringBuilder sourceCode, HashMap<String, String> variables) {
        importDataClasses(sourceCode, variables);

        sourceCode.append("import javax.persistence.Column;\n" +
                "import javax.persistence.Entity;\n" +
                "import javax.persistence.Id;\n" +
                "import javax.persistence.Table;\n");
        sourceCode.append("import javax.validation.constraints.NotNull;\n\n");
    }

    public static void importDataClasses(StringBuilder sourceCode, HashMap<String, String> variables) {
        boolean dateClassAdded = false;
        boolean listClassAdded = false;

        for (String key : variables.keySet()) {
            String value = variables.get(key);

            if ((value.contains("Date")) && !dateClassAdded) {
                sourceCode.append("import java.util.Date;\n");
                dateClassAdded = true;
            }

            if ((value.contains("List")) && !listClassAdded) {
                sourceCode.append("import java.util.List;\n\n");
                listClassAdded = true;
            }
        }
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
    public static void createClassStartSyntax(StringBuilder sourceCode, String className, boolean isEntity) {
        String stringToAdd = "";

        if (isEntity) {
            stringToAdd = "@Entity\n" +
                    "@Table(name = \"db_" + className.toLowerCase() + "\")\n";
        }

        sourceCode.append(stringToAdd + "public class " + StringUtils.capitalize(className) + " {\n\n");
    }

    /***
     * @param sourceCode
     * @param variables
     */
    public static void createVariables(StringBuilder sourceCode, HashMap<String, String> variables) {
        List<String> listOfVariables = new ArrayList<String>(variables.keySet());
        Boolean idAlreadySet = false;
        String stringToAdd = "";
        Boolean unique = false;
        String length = "";


        for (String variableName : listOfVariables) {
            String variableType = variables.get(variableName);
            if (variableName.contains("id") || variables.get(variableName).contains("Date")) {
                stringToAdd = "\t@Id\n";
                unique = true;
            } else {
                stringToAdd = "";
                unique = false;
                length = "length = 255, ";
            }


            sourceCode.append(stringToAdd +
                    "\t@Column(" + length + "unique = " + unique + ", nullable = false)\n" +
                    "\t@NotNull\n" +
                    "\tprivate " + variableType + " " + variableName + ";\n");
        }

        sourceCode.append("\n\n");
    }

    public static void createConstructor(StringBuilder sourceCode, String className, HashMap<String, String> variables) {
        List<String> listOfVariables = new ArrayList<String>(variables.keySet());

        sourceCode.append("\t" + StringUtils.capitalize(className) + "(");

        for (int i = 0; i < listOfVariables.size(); i++) {
            String variableName = listOfVariables.get(i);
            String variableType = variables.get(variableName);
            sourceCode.append(variableType + " " + variableName);

            if (i != variables.size() - 1) {
                sourceCode.append(", ");
            }
        }

        sourceCode.append(") {\n");

        for (String variableName : listOfVariables) {
            String variableType = variables.get(variableName);
            sourceCode.append("\t\tthis." + variableName + " = " + variableName + ";\n");
        }

        sourceCode.append("\t}\n\n");
    }

    /***
     * @param sourceCode
     * @param variables
     */
    public static void createMethods(StringBuilder sourceCode, HashMap<String, String> variables) {
        List<String> listOfVariablesToCreate = new ArrayList<String>(variables.keySet());

        for (String variableName : listOfVariablesToCreate) {
            String variableType = variables.get(variableName);

            sourceCode.append("\tpublic " + variableType + " get" + StringUtils.capitalize(variableName) + "() {\n");
            sourceCode.append("\t\treturn this." + variableName + ";\n");
            sourceCode.append("\t}\n\n");

            sourceCode.append("\tpublic void set" + StringUtils.capitalize(variableName) + "(" + variableType + " " + variableName + ") {\n");
            sourceCode.append("\t\tthis." + variableName + " = " + variableName + ";\n");
            sourceCode.append("\t}\n\n");
        }
    }

    /***
     * @param sourceCode StringBuilder in which we need to append the code
     */
    public static void createClassEndSyntax(StringBuilder sourceCode) {
        sourceCode.append("}");
    }

    public static void generateClass(String classPathToGenerate, StringBuilder sourceCode) {
        try {
            System.out.println("writting file at? " + classPathToGenerate);
            Files.write(Paths.get(classPathToGenerate), sourceCode.toString().getBytes(), StandardOpenOption.CREATE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
