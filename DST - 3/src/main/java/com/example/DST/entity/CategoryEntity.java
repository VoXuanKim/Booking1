package com.example.DST.entity;

import com.example.DST.enums.CategoryNameEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String pictureUrl;
    @Enumerated(EnumType.STRING)
    private CategoryNameEnum categoryName;

    public CategoryEntity(CategoryNameEnum categoryName) {
        this.categoryName = categoryName;
        this.pictureUrl = categoryName.getPictureUrl();  // Lấy URL từ Enum
    }

    @OneToMany(mappedBy = "categoryEntity", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ResourceEntity> resources;
}
