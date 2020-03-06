package com.example.demo.controller;

import com.example.demo.model.Color;
import com.example.demo.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/color")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping
    public ResponseEntity<List<Color>> getAllColor(){
        List<Color> colorList = colorService.findAllColor();
        if (colorList == null){
            return new ResponseEntity("Color null.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Color>>(colorList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Color> createColor(@RequestBody Color color){
        List<Color> colorList = colorService.findAllColor();
        if (colorList.size() <3){
            if (color != null){
                for (Color c: colorList){
                    if (c.getName().equalsIgnoreCase(color.getName())){
                        return new ResponseEntity("Create failed: Color exist in Data!!!", HttpStatus.BAD_REQUEST);
                    }
                }
                colorService.save(color);
                return new ResponseEntity<>(color, HttpStatus.CREATED);
            }else {
                return new ResponseEntity("Color create failed!!!", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("Cannot create color more than 3.", HttpStatus.BAD_REQUEST);


    }
    @PutMapping("/{id}")
    public ResponseEntity<Color> updateColor(@PathVariable("id")Long id,@RequestBody Color color){
        List<Color> colorList = colorService.findAllColor();
        Color colorFind = colorService.findByColorId(id).get();

        if (colorFind == null){
            return new ResponseEntity("Not found!!!", HttpStatus.BAD_REQUEST);
        }
        for (Color c: colorList){
            if (c.getName().equalsIgnoreCase(color.getName())){
                return new ResponseEntity("Update failed: Color exist in Data!!!", HttpStatus.BAD_REQUEST);
            }
        }
        colorFind.setName(color.getName());
        colorService.save(colorFind);
        return new ResponseEntity<Color>(color, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Color> deleteCategory(@PathVariable("id")Long id){
        Color colorFind = colorService.findByColorId(id).get();
        if (colorFind == null){
            return new ResponseEntity("Not found!!!", HttpStatus.BAD_REQUEST);
        }
        colorService.delete(colorFind.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
