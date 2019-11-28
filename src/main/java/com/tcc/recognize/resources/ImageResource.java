package com.tcc.recognize.resources;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.tcc.recognize.commom.Command;
import com.tcc.recognize.commom.ResponseReplacer;
import com.tcc.recognize.model.ResultModel;
import com.tcc.recognize.service.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/classifier")
public class ImageResource {

    @Autowired
    FileStorageService fileStorageService;
    
    @PostMapping("/single")
    public ResultModel processSingleImage(@RequestParam("file") MultipartFile file) {

        String fileName = fileStorageService.storeFile(file);

       
        String response = new Command()
                    .buildCommandSingleImage(fileName)
                    .execute();

        return ResponseReplacer.singleReplace(response);
    }

    @PostMapping("/multiple")
    public String processMultipleImage(@RequestParam("files") MultipartFile[] files) {

        List<String> filesName;

        filesName = Arrays
                        .asList(files)
                        .stream()
                        .map(file -> fileStorageService.storeFile(file))
                        .collect(Collectors.toList());

        Command command = new Command();
        return command
                    .buildCommandMultipleImage(filesName)
                    .execute();
    }
    
}