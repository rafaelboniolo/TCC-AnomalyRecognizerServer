package com.tcc.recognizer.resources;


import com.tcc.recognizer.service.CommandService;
import com.tcc.recognizer.commom.ResponseReplacer;
import com.tcc.recognizer.model.ResultModel;
import com.tcc.recognizer.service.FileStorageService;

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

    @Autowired
    CommandService command;
    
    @PostMapping("/single")
    public ResultModel processSingleImage(@RequestParam("file") MultipartFile file) {

        String fileName = fileStorageService.storeFile(file);

        String response = command
                    .buildCommandSingleImage(fileName)
                    .execute();

        return ResponseReplacer.singleReplace(response);
    }

//    @PostMapping("/multiple")
//    public String processMultipleImage(@RequestParam("files") MultipartFile[] files) {
//
//        List<String> filesName;
//
//        filesName = Arrays
//                        .asList(files)
//                        .stream()
//                        .map(file -> fileStorageService.storeFile(file))
//                        .collect(Collectors.toList());
//
//        Command command = new Command();
//        return command
//                    .buildCommandMultipleImage(filesName)
//                    .execute();
//    }
    
}