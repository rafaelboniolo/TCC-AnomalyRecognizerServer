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

        return this;
        
    }
    
    public Command resetCommand(){
        this.command = new StringBuilder();
        return this;
    }

    public String execute(){
        if(OS.isWindows()){
            try{
                Process process = Runtime.getRuntime().exec(command.toString());
                
                Scanner scanner = new Scanner(process.getInputStream(), "UTF-8");
                String response = scanner.nextLine();
                scanner.close();
                return response;
            }catch(Throwable th){
                th.getStackTrace();
                return th.getStackTrace().toString();
            }
        }
        return "Seu sistema operacional não possui comando registrado no serviço";
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