package com.example.DST.service;


import com.example.DST.entity.ResourceEntity;

import java.util.*;

public interface ResourceService {
    void save(ResourceEntity resource);

    void updateById(int id, ResourceEntity resource);

    void deleteById(int id);

    public Optional<ResourceEntity> findResourcebyId(int id);
}
