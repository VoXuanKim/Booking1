package com.example.DST.controller;

import com.example.DST.entity.ResourceEntity;
import com.example.DST.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/resources")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @PostMapping("/create")
    public ResponseEntity<String> createResources(@RequestBody ResourceEntity resourceEntity) {
        resourceService.save(resourceEntity);
        return ResponseEntity.ok("Resource added successfully.");
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<String> deleteResources(@PathVariable int id) {
        resourceService.deleteById(id);
        return ResponseEntity.ok("Resource Deleted successfully.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateResources(@PathVariable int id, @RequestBody ResourceEntity resourceEntity) {
        resourceService.updateById(id, resourceEntity);
        return ResponseEntity.ok("Resource updated successfully.");
    }



}
