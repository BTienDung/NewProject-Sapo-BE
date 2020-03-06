package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    void savePicture(MultipartFile multipartFile);
    void deletePicture(Product product) throws IOException;
}
