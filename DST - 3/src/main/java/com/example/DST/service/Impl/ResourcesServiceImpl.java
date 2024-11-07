package com.example.DST.service.Impl;

import com.example.DST.entity.ResourceEntity;
import com.example.DST.repository.CategoryRepository;
import com.example.DST.repository.ResourceRepository;
import com.example.DST.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResourcesServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void save(ResourceEntity resource) {
        resourceRepository.save(resource);
    }

    @Override
    public void updateById(int id, ResourceEntity resource) {
        Optional<ResourceEntity> existingResourcesOtp = resourceRepository.findById(id);
        if (existingResourcesOtp.isPresent() && !existingResourcesOtp.get().isDeleted()) {
            ResourceEntity existingResource = existingResourcesOtp.get();
            existingResource.setResourcesName(resource.getResourcesName() != null ? resource.getResourcesName() : existingResource.getResourcesName() );
            existingResource.setResourcesDescription(resource.getResourcesDescription() != null ? resource.getResourcesDescription() : existingResource.getResourcesDescription());
            existingResource.setResourcesPrice(resource.getResourcesPrice() != null ? resource.getResourcesPrice() : existingResource.getResourcesPrice());
            existingResource.setResourcesCapacity(resource.getResourcesCapacity() != null ? resource.getResourcesCapacity() : existingResource.getResourcesCapacity());
            existingResource.setResourcesPhoto(resource.getResourcesPhoto() != null ? resource.getResourcesPhoto() : existingResource.getResourcesPhoto());

            existingResource.setCategoryEntity(categoryRepository.getById(resource.getCategoryEntity().getCategoryId()));

            if (resource.getResourceStatusEnum() != null) {
                existingResource.setResourceStatusEnum(resource.getResourceStatusEnum());
            }
            resourceRepository.save(existingResource);
        }
    }

    @Override
    public void deleteById(int id) {
        resourceRepository.softDeletedById(id);
    }

    @Override
    public Optional<ResourceEntity> findResourcebyId(int id) {
        return resourceRepository.findById(id);
    }
}
