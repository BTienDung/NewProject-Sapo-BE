package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> categoryList = categoryService.findAllCategory();
        if (categoryList == null){
            return new ResponseEntity("Category null.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        List<Category> categoryListDb = categoryService.findAllCategory();

        if (categoryListDb.size() <3){
            if (category != null){
                for (Category c: categoryListDb){
                    if (c.getName().equalsIgnoreCase(category.getName())){
                        return new ResponseEntity("Create failed: Category exist in Data!!!", HttpStatus.BAD_REQUEST);
                    }
                }
                categoryService.save(category);
                return new ResponseEntity<>(category, HttpStatus.CREATED);
            }else {
                return new ResponseEntity("Category create failed!!!", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("Cannot create category more than 3.", HttpStatus.BAD_REQUEST);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id")Long id,@RequestBody Category category){
        List<Category> categoryListDb = categoryService.findAllCategory();
        Category categoryFind = categoryService.findCategoryById(id).get();

        if (categoryFind == null){
            return new ResponseEntity("Not found!!!", HttpStatus.BAD_REQUEST);
        }
        for (Category c: categoryListDb){
            if (c.getName().equalsIgnoreCase(category.getName())){
                return new ResponseEntity("Update failed: Category exist in Data!!!", HttpStatus.BAD_REQUEST);
            }
        }
        categoryFind.setName(category.getName());
        categoryService.save(categoryFind);
        return new ResponseEntity<Category>(category, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id")Long id){
        Category categoryFind = categoryService.findCategoryById(id).get();
        if (categoryFind == null){
            return new ResponseEntity("Not found!!!", HttpStatus.BAD_REQUEST);
        }
        categoryService.delete(categoryFind.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id")Long id){
        Category categoryFind = categoryService.findCategoryById(id).get();
        if (categoryFind == null){
            return new ResponseEntity("Not found!!!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Category>(categoryFind, HttpStatus.OK);
    }
}
