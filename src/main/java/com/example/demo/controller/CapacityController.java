package com.example.demo.controller;

import com.example.demo.model.Capacity;
import com.example.demo.service.CapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/capacity")
public class CapacityController {
    @Autowired
    private CapacityService capacityService;
    @GetMapping
    public ResponseEntity<List<Capacity>> getAllCapacity(){
        List<Capacity> capacityList = capacityService.findAllCapacity();
        if (capacityList == null){
            return new ResponseEntity("Capacity null.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Capacity>>(capacityList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createCapacity(@RequestBody Capacity capacity){
        List<Capacity> capacityList = capacityService.findAllCapacity();
        if (capacityList.size() <3){
            if (capacity != null){
                for (Capacity c: capacityList){
                    if (c.getName().equalsIgnoreCase(capacity.getName())){
                        return new ResponseEntity("Create failed: Capacity exist in Data!!!", HttpStatus.BAD_REQUEST);
                    }
                }
                capacityService.save(capacity);
                return new ResponseEntity("Create capacity success!!!", HttpStatus.CREATED);
            }else {
                return new ResponseEntity("Capacity create failed!!!", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("Cannot create capacity more than 3", HttpStatus.BAD_REQUEST);


    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateColor(@PathVariable("id")Long id,@RequestBody Capacity capacity){
        List<Capacity> capacityList = capacityService.findAllCapacity();
        Capacity capacityFind = capacityService.findByCapacityId(id).get();

        if (capacityFind == null){
            return new ResponseEntity("Not found!!!", HttpStatus.BAD_REQUEST);
        }
        for (Capacity c: capacityList){
            if (c.getName().equalsIgnoreCase(capacity.getName())){
                return new ResponseEntity("Update failed: Capacity exist in Data!!!", HttpStatus.BAD_REQUEST);
            }
        }
        capacityFind.setName(capacity.getName());
        capacityService.save(capacityFind);
        return new ResponseEntity("Update capacity success!!!", HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id")Long id){
        Capacity capacityFind = capacityService.findByCapacityId(id).get();
        if (capacityFind == null){
            return new ResponseEntity("Not found!!!", HttpStatus.BAD_REQUEST);
        }
        capacityService.delete(id);
        return new ResponseEntity("Delete capacity success!!!", HttpStatus.OK);
    }
}
