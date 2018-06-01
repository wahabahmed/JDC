package com.dynamic.creator.app.Utils;

import com.dynamic.creator.app.Model.PackageType;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wahaba on 28/05/2018.
 */
public class RestControllerStringBuilderUtils {

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
        sourceCode.append("import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.http.HttpStatus;\n" +
                "import org.springframework.web.bind.annotation.*; \n\n");

        sourceCode.append("import java.util.List;\n\n");
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
        sourceCode.append("@RestController\n" +
                "@RequestMapping(\"/1.0/" + className + "\")\n");
        sourceCode.append("public class " + className + " {\n");
    }

    /***
     * @param sourceCode
     * @param className
     */

    public static void createVariables(StringBuilder sourceCode, String className, PackageType packageType) {
        String stringToAdd = StringUtils.capitalize(packageType.getPackageName());
        sourceCode.append("    @Autowired \n" + "    private " + StringUtils.capitalize(className) + stringToAdd + " " + className + stringToAdd + "; \n\n");
    }

    /***
     * @param sourceCode
     * @param className
     */
    public static void createMethods(StringBuilder sourceCode, String className) {
        String classNameStartWithCapitalLetter = StringUtils.capitalize(className);

        //Get
        sourceCode.append(
                "    @RequestMapping(value = \"/get\", method = RequestMethod.GET)\n" +
                        "    @ResponseStatus(value = HttpStatus.OK)\n" +
                        "    @ResponseBody\n" +
                        "    public " + classNameStartWithCapitalLetter + " get" + classNameStartWithCapitalLetter + "(@RequestHeader(name = \"REQUEST_HEADER_NAME\") String sessionKey, " +
                        "@RequestParam(value = \"id\") String id) {\n" +
                        "        return " + className + "Controller.get" + classNameStartWithCapitalLetter + "(sessionKey, id);\n" +
                        "    }\n\n");


        //List
        sourceCode.append(
                "    @RequestMapping(value = \"/list\", method = RequestMethod.GET)\n" +
                        "    @ResponseStatus(value = HttpStatus.OK)\n" +
                        "    @ResponseBody\n" +
                        "    public List<" + classNameStartWithCapitalLetter + "> list" + classNameStartWithCapitalLetter + "(@RequestHeader(name = \"REQUEST_HEADER_NAME\") String sessionKey) {\n" +
                        "        return " + className + "Controller.list" + classNameStartWithCapitalLetter + "(sessionKey);\n" +
                        "    }\n\n");

        //Create
        sourceCode.append(
                "    @RequestMapping(value = \"/create\", method = RequestMethod.POST)\n" +
                        "    @ResponseStatus(value = HttpStatus.CREATED)\n" +
                        "    @ResponseBody\n" +
                        "    public " + classNameStartWithCapitalLetter + " create" + classNameStartWithCapitalLetter + "(@RequestHeader(name = \"REQUEST_HEADER_NAME\") String sessionKey," +
                        " @RequestBody " + classNameStartWithCapitalLetter + " " + className + ") {\n" +
                        "        return " + className + "Controller.create" + classNameStartWithCapitalLetter + "(sessionKey, " + className + ");\n" +
                        "    }\n\n");

        //Edit
        sourceCode.append(
                "    @RequestMapping(value = \"/edit\", method = RequestMethod.POST)\n" +
                        "    @ResponseStatus(value = HttpStatus.ACCEPTED)\n" +
                        "    @ResponseBody\n" +
                        "    public " + classNameStartWithCapitalLetter + " edit" + classNameStartWithCapitalLetter + "(@RequestHeader(name = \"REQUEST_HEADER_NAME\") String sessionKey," +
                        " @RequestBody " + classNameStartWithCapitalLetter + " " + className + ") {\n" +
                        "        return " + className + "Controller.edit" + classNameStartWithCapitalLetter + "(sessionKey, " + className + ");\n" +
                        "    }\n\n");

        //Delete
        sourceCode.append(
                "    @RequestMapping(value = \"/delete\", method = RequestMethod.POST)\n" +
                        "    @ResponseStatus(value = HttpStatus.ACCEPTED)\n" +
                        "    @ResponseBody\n" +
                        "    public void delete" + classNameStartWithCapitalLetter + "(@RequestHeader(name = \"REQUEST_HEADER_NAME\") String sessionKey," +
                        " @RequestBody " + classNameStartWithCapitalLetter + " " + className + ") {\n" +
                        "        " + className + "Controller.delete" + classNameStartWithCapitalLetter + "(sessionKey, " + className + ");\n" +
                        "    }\n");
    }

    /***
     * @param sourceCode StringBuilder in which we need to append the code
     */
    public static void createClassEndSyntax(StringBuilder sourceCode) {
        sourceCode.append("}");
    }
}
