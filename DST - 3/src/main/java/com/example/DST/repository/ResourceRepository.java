package com.example.DST.repository;


import com.example.DST.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ResourceRepository extends JpaRepository<ResourceEntity, Integer> {
    //@Transactional
    @Modifying
    @Query(name = "softDeletedById", value = "UPDATE ResourceEntity r SET r.deleted = true where r.resourcesId = ?1")
    void softDeletedById(int id);
}
