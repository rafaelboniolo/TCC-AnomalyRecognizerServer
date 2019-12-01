package com.tcc.recognizer.commom;

/**
 * OS
 */
public class OS {

    public static Boolean isWindows(){
        return System
                    .getProperty("os.name")
                    .toLowerCase()
                    .contains("windows");
    }

    public static Boolean isLinux() {
        return System
                    .getProperty("os.name")
                    .toLowerCase()
                    .contains("linux");
        }
    
}