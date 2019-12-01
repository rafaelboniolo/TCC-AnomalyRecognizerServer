package com.tcc.recognizer.service;

import com.tcc.recognizer.property.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CommandService {


    private StringBuilder command;
    private ApplicationProperties applicationProperties;

    @Autowired
    public CommandService(@Autowired ApplicationProperties applicationProperties) {
        this.command = new StringBuilder();
        this.applicationProperties = applicationProperties;
    }

    public CommandService buildCommandSingleImage(String param) {
        this.command = new StringBuilder();
        command
                .append("python")
                .append(" ")
                .append(applicationProperties.getScript()) // caminho do script e do modelo preditivo
                .append("/")
                .append(applicationProperties.getScriptname()) // nome do script python
                .append(" ")
                .append(applicationProperties.getScript()) // caminho do modelo preditivo para que o python o encontre
                .append("/")
                .append(applicationProperties.getModelname()) // nome do modelo preditivo
                .append(" ")
                .append(applicationProperties.getUpload()) // caminho das imagens recebidas no server
                .append("/") // caminho das imagens recebidas no server
                .append(param); // nome da imagem recebida

        return this;
        
    }
    
//    public Command buildCommandMultipleImage(List<String> params) {
//        command
//            .append("python")
//            .append(" ")
//            .append(new PathBuilder().getPathPyScript())
//            .append(file)
//            .append(" ");
//
//            params = params
//                            .stream()
//                            .map(param -> new PathBuilder().getPathFile(param))
//                            .collect(Collectors.toList());
//
//            params.forEach(command::append);
//
//        return this;
//
//    }
    
    public CommandService resetCommand(){
        this.command = new StringBuilder();
        return this;
    }

    public String execute(){
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
    
}