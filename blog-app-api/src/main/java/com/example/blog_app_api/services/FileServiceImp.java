package com.example.blog_app_api.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImp implements FileService {
    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();

        String randomID = UUID.randomUUID().toString();
        String fileName = randomID + name;
        String filePath = path + File.separator + fileName;
        File dest = new File(path);
        if (!dest.exists()) {
            dest.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
       String fullPath = path + File.separator + fileName;
       InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
