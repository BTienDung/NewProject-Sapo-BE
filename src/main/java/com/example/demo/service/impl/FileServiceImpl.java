package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.service.FileService;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@Service
@PropertySource("classpath:application.properties")
public class FileServiceImpl  implements FileService {

    @Value(value = "E:/Sapo/Project-Sapo/src/main/webapp/images/")
    private String pic;
    @Override
    public void savePicture(MultipartFile multipartFile) {
        File uploadedFile = new File(pic, multipartFile.getOriginalFilename());
        try {
            uploadedFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(uploadedFile);
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePicture(Product product) throws IOException {
        ArrayList<String> companyArrayList = product.getPicture();
        for (String pathFile: companyArrayList){
            String picFile = pic+ "/" + product.getPicture();
            try {
                File picture = FileUtils.getFile(pathFile);
                FileUtils.forceDelete(picture);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
