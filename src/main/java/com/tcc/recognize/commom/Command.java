package com.tcc.recognize.commom;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Command {

    private static final String FILE = "recognize.py";

    private StringBuilder command;

    public Command() {
        this.command = new StringBuilder();
    }

    public Command buildCommandSingleImage(String param) {
               
        command
            .append("python")
            .append(" ")
            .append(PathBuilder.getPathPyFile())
            .append(FILE)
            .append(" ")
            .append(PathBuilder.getPathFile(param));

        System.out.println(command.toString());

        return this;
        
    }
    
    public Command buildCommandMultipleImage(List<String> params) {
        command
            .append("python")
            .append(" ")
            .append(PathBuilder.getPathPyFile())
            .append(FILE)
            .append(" ");
            
            params = params
                            .stream()
                            .map(param -> PathBuilder.getPathFile(param))
                            .collect(Collectors.toList());

            params.forEach(command::append);

            System.out.println(command.toString());

        return this;
        
    }
    
    public Command resetCommand(){
        this.command = new StringBuilder();
        return this;
    }

    public Boolean execute(){
        if(OS.isWindows()){
            try{
                Process process = Runtime.getRuntime().exec(command.toString());
                logOutput(process.getInputStream(), "");
                logOutput(process.getErrorStream(), "Error: ");
                process.waitFor();
                return true;
            }catch(Throwable th){
                th.getStackTrace();
                return false;
            }
        }
        return false;
    }

    private void logOutput(InputStream inputStream, String prefix) {
        new Thread(() -> {
            Scanner scanner = new Scanner(inputStream, "UTF-8");
            while (scanner.hasNextLine()) {
                synchronized (this) {
                    log(prefix + scanner.nextLine());
                }
            }
            scanner.close();
        }).start();
    }

    private synchronized void log(String message) {
        System.out.println(message);
    }

    
    
}