package com.example.DST.entity;


import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
@Setter
@Getter
@MappedSuperclass
public class Base {
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private String createdBy;
    private String updatedBy;
    private boolean deleted = Boolean.FALSE;;
}
