package com.dynamic.creator.app.Utils;

import com.dynamic.creator.app.Model.PackageType;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wahaba on 28/05/2018.
 */
public class RepositoryStringBuilderUtils {

    private static List<String> listOfRestApis = Arrays.asList("get", "list", "edit", "create", "delete");

    /***
     * This method should import classes required in the class sourcecode
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

        sourceCode.append("import " + finalPackageName + ";\n\n");
    }

    /***
     * This method should import rest classes in the class sourcecode
     *
     * @param sourceCode StringBuilder in which we need to append the code
     */
    public static void importRestPackage(StringBuilder sourceCode) {
        //sourceCode.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        //sourceCode.append("import org.springframework.data.jpa.repository.Query;\n");
        sourceCode.append("import org.springframework.data.repository.CrudRepository;\n");
        sourceCode.append("import org.springframework.stereotype.Repository;\n\n");

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
        sourceCode.append("@Repository\n");
        sourceCode.append("public interface " + className + "Repo extends CrudRepository<" + className + ", String> {\n");
    }

    /***
     * @param sourceCode
     * @param className
     */
    public static void createMethods(StringBuilder sourceCode, String className) {
        String classNameStartWithCapitalLetter = StringUtils.capitalize(className);
        String repoClassNameToAdd = StringUtils.capitalize(PackageType.REPO.getPackageName());


    }

    /***
     * @param sourceCode StringBuilder in which we need to append the code
     */
    public static void createClassEndSyntax(StringBuilder sourceCode) {
        sourceCode.append("}\n");
    }
}
