package com.tcc.recognize.resources;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.tcc.recognize.commom.Command;
import com.tcc.recognize.property.FileStorageProperties;
import com.tcc.recognize.service.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/classifier")
public class ImageResource {

    @Autowired
    FileStorageService fileStorageService;
    
    @PostMapping("/single")
    public Boolean processSingleImage(@RequestParam("file") MultipartFile file) {

        String fileName = fileStorageService.storeFile(file);

        Command command = new Command();
        return command
                    .buildCommandSingleImage(fileName)
                    .execute();
    }

    @PostMapping("/multiple")
    public Boolean processMultipleImage(@RequestParam("files") MultipartFile[] files) {

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