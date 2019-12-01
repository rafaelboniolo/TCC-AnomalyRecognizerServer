package com.tcc.recognizer.commom;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;

/**
 * PathBuilder
 */
@Deprecated
public class PathBuilder {

    public String getPathPyScript() {
        return "";
    }
    
    public String getPathFile(String file) {
        
        StringBuilder path = new StringBuilder();

        path
            .append("")
            .append(file)
            .append(" ");

            return path.toString();
    }

}