package com.kuku;

import java.net.URL;

/**
 * Created by Maxim.Pantuhin on 18.03.2016.
 */
public class Constants {
    public static long updateTimeout = 600000;

   public String version() {
       Package objPackage = this.getClass().getPackage();
       String manifestVersion = objPackage.getSpecificationVersion();
       String version = (manifestVersion == null)?"0.0":manifestVersion;
       return version;
   }


    public static String getFilePath(String className) {
        if (!className.startsWith("/")) {
            className = "/" + className;
        }

        className = className.replace('.', '/');
        className = className + ".class";

        URL classUrl = new Constants().getClass().getResource(className);
        if (classUrl != null) {
            String temp = classUrl.getFile();
            if (temp.startsWith("file:")) {
                return temp.substring(5);
            }

            return temp;
        } else {
            return "\nClass '" + className +
                    "' not found in \n'" +
                    System.getProperty("java.class.path") + "'";
        }
    }

}
