package com.example.DST.service.Impl;

import com.example.DST.enums.CategoryNameEnum;
import com.example.DST.entity.CategoryEntity;
import com.example.DST.repository.CategoryRepository;
import com.example.DST.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService, CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            for (CategoryNameEnum categoryName : CategoryNameEnum.values()) {
                CategoryEntity categoryEntity = new CategoryEntity(categoryName);
                categoryRepository.save(categoryEntity);
            }
            System.out.println("Categories initialized successfully!");
        } else {
            System.out.println("Categories already exist, skipping initialization.");
        }
    }

    @Override
    public List<CategoryEntity> getAll() {
        return categoryRepository.findAll();
    }
}
