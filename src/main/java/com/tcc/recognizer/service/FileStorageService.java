package com.tcc.recognizer.service;

import com.tcc.recognizer.property.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.tcc.recognizer.commom.CodeImage;


@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(@Autowired ApplicationProperties applicationProperties) {

        this.fileStorageLocation = Paths
                .get(applicationProperties.getUpload())
                .toAbsolutePath()
                .normalize();

        try {

            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(CodeImage.generate(file.getOriginalFilename()));

        try {
            
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            ex.getStackTrace();
            return null;
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                System.out.println("File not found " + fileName);
                return null;
            }
        } catch (MalformedURLException ex) {
            ex.getStackTrace();
            return null;
        }
    }
}