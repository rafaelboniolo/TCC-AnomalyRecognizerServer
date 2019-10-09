package com.tcc.recognize.commom;

import java.util.Date;

/**
 * CodeImage
 */
public class CodeImage {

    public static String generate(String file) {
        StringBuilder code = new StringBuilder();
        code
            .append(new Date().getTime())
            .append(file
                    .replace('-', '0')
                    .replace('(', '0')
                    .replace(')', '0')
                    .replace(' ', '0')
                    );

            return code.toString();
    }
    
}