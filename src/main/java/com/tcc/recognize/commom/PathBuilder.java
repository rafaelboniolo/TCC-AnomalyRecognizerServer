package com.tcc.recognize.commom;

import java.io.File;

/**
 * PathBuilder
 */
public class PathBuilder {

    public static String getPathPyFile() {
        
        StringBuilder path = new StringBuilder();

        if(OS.isWindows()){
            path
                .append(new File("").getAbsolutePath())
                .append("\\src\\main\\java\\com\\tcc\\recognize\\scripts\\");

            return path.toString();
        }else{
            return "";
        }
    }
    
    public static String getPathFile(String file) {
        
        StringBuilder path = new StringBuilder();

        if(OS.isWindows()){
            path
                .append(new File("").getAbsolutePath())
                .append("\\src\\main\\java\\com\\tcc\\recognize\\data\\")
                .append(file)
                .append(" ");

            return path.toString();
        }else{
            return "";
        }
    }
    
    
}