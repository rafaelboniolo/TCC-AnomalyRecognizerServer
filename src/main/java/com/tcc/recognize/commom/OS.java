package com.tcc.recognize.commom;

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
        //TODO: Verificar possibilidades de nome
        return System
                    .getProperty("os.name")
                    .toLowerCase()
                    .contains("linux");
        }
    
}