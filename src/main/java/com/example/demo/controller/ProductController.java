package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.FileService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;
    @PostMapping
    private ResponseEntity<Product> createProduct(@RequestBody Product product){
        List<Product> productListDB = productService.findAllProductByStatus();
        if (productListDB != null){
            if (product.getName() != null){
                for (Product p: productListDB){
//                    if (    (p.getName().equalsIgnoreCase(product.getName()))
//                            && (p.getCategory().getName().equalsIgnoreCase(product.getCategory().getName()))
//                            && (p.getColor().getName().equalsIgnoreCase(product.getColor().getName()))
//                            && (p.getCapacity().getName().equalsIgnoreCase(product.getCapacity().getName()))
                    if(p.getName().equalsIgnoreCase(product.getName())
                            && (p.getCategory().getId()==(product.getCategory().getId()))
                            && (p.getColor().getId()==(product.getColor().getId()))
                            && (p.getCapacity().getId()==(product.getCapacity().getId())))
                    {
                        return new ResponseEntity("Product exist!!!",HttpStatus.BAD_REQUEST);
                    }
                }
                    Calendar cal = Calendar.getInstance();
                    Date date = cal.getTime();
                    product.setCraeteDate(date);
                    product.setStatus(true);
                    productService.save(product);
                    return new ResponseEntity<Product>(product,HttpStatus.CREATED);
            }else {
                return new ResponseEntity("Product can't null!!!",HttpStatus.BAD_REQUEST);
            }
        }
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        product.setCraeteDate(date);
        product.setStatus(true);
        productService.save(product);
        return new ResponseEntity<>(product,HttpStatus.CREATED);

    }




    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        List<Product> productListDB = productService.findAllProductByStatus();
        Product productOld = productService.findProductById(id).get();

        if(productOld!=null){
            if (product != null){
                for(Product p: productListDB){
                    if(p.getName().equalsIgnoreCase(product.getName())
                            && (p.getCategory().getId()==(product.getCategory().getId()))
                            && (p.getColor().getId()==(product.getColor().getId()))
                            && (p.getCapacity().getId()==(product.getCapacity().getId())))
                    {
                        return new ResponseEntity("Product exist!!!",HttpStatus.BAD_REQUEST);
                    }
                }

                productOld.setName(product.getName());
                productOld.setCategory(product.getCategory());
                productOld.setColor(product.getColor());
                productOld.setCapacity(product.getCapacity());

                Calendar cal = Calendar.getInstance();
                Date date = cal.getTime();

                productOld.setModifiedDate(date);
                productService.save(productOld);
                return new ResponseEntity<Product>(product, HttpStatus.OK);
            }else {
                return new ResponseEntity("Update product failed!!!", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("Product not found!!!", HttpStatus.NOT_FOUND);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product productOld = productService.findProductById(id).get();
        if (productOld == null){
            return new ResponseEntity("Product not found!!!", HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Product>(productOld, HttpStatus.OK);

    }
    @GetMapping
    private ResponseEntity<List<Product>> getAllProduct(){
        List<Product> products = productService.findAllProductByStatus();
        if (products != null){
            return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
        }else {
            return new ResponseEntity("Don't hava product",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/{id}")
    private ResponseEntity<Product> uploadPictures(@PathVariable("id")Long id, @RequestPart("pictures")MultipartFile[] file) throws IOException{
        ArrayList<String> fileName = new ArrayList<>();
        //khoi tao mang luu file
        ArrayList<File> saveFiles = new ArrayList<>();

        Optional<Product> productOptional = productService.findProductById(id);
        Product product = productOptional.get();
        if (product != null){
            if (product.getPicture() != null){
                fileService.deletePicture(product);
            }else
                //lưu tên ảnh
                for (MultipartFile multipartFile: file){
                    fileName.add(multipartFile.getOriginalFilename());
                }
            // lưu file ảnh lên serve
            for (MultipartFile multipartFile: file){
                fileService.savePicture(multipartFile);
            }
            product.setPicture(fileName);
            productService.save(product);
            return new ResponseEntity<Product>(product,HttpStatus.OK);
        }else {
            for (MultipartFile multipartFile: file){
                fileName.add(multipartFile.getOriginalFilename());
            }
            for (MultipartFile multipartFile: file){
                fileService.savePicture(multipartFile);
            }
            product.setPicture(fileName);
            productService.save(product);
            return new ResponseEntity<Product>(product,HttpStatus.OK);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id")Long id){
        Product productFind = productService.findProductById(id).get();
        if (productFind == null){
            return new ResponseEntity("Don't find product!!!", HttpStatus.BAD_REQUEST);
        }
        productFind.setStatus(false);
        productService.save(productFind);
        return new ResponseEntity("Delete product success!!!", HttpStatus.OK);
    }
}
