package com.kuku;

/**
 * Created by Maxim.Pantuhin on 18.03.2016.
 */
public class Constants {
    public static long updateTimeout = 600000;

   String version() {
       Package objPackage = this.getClass().getPackage();
       String manifestVersion = objPackage.getSpecificationVersion();
       String version = (manifestVersion == null)?"0.0":manifestVersion;
       return version;
   }
}
