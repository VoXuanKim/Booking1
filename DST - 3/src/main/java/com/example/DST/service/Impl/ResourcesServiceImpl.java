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
        Optional<ResourceEntity> existingResourceOpt = findById(id);
        if (existingResourceOpt.isPresent() && !existingResourceOpt.get().isDeleted()) {
            ResourceEntity existingResource = existingResourceOpt.get();
            updateResourceFields(existingResource, resource);
            save(existingResource);
        }
    }


    public Optional<ResourceEntity> findById(int id) {
        return resourceRepository.findById(id);
    }

    // Method to handle updating fields of the resource
    private void updateResourceFields(ResourceEntity existingResource, ResourceEntity resource) {
        updateBasicFields(existingResource, resource);
        updateCategory(existingResource, resource);
        updateStatus(existingResource, resource);
    }

    // Sub-method for updating basic fields
    private void updateBasicFields(ResourceEntity existingResource, ResourceEntity resource) {
        existingResource.setResourcesName(resource.getResourcesName() != null ? resource.getResourcesName() : existingResource.getResourcesName());
        existingResource.setResourcesDescription(resource.getResourcesDescription() != null ? resource.getResourcesDescription() : existingResource.getResourcesDescription());
        existingResource.setResourcesPrice(resource.getResourcesPrice() != null ? resource.getResourcesPrice() : existingResource.getResourcesPrice());
        existingResource.setResourcesCapacity(resource.getResourcesCapacity() != null ? resource.getResourcesCapacity() : existingResource.getResourcesCapacity());
        existingResource.setResourcesPhoto(resource.getResourcesPhoto() != null ? resource.getResourcesPhoto() : existingResource.getResourcesPhoto());
    }
    // Method for updating the category entity if present
    private void updateCategory(ResourceEntity existingResource, ResourceEntity resource) {
        if (resource.getCategoryEntity() != null) {
            existingResource.setCategoryEntity(
                    categoryRepository.getById(resource.getCategoryEntity().getCategoryId())
            );
        }
    }


    // Method for updating the resource status if present
    private void updateStatus(ResourceEntity existingResource, ResourceEntity resource) {
        if (resource.getResourceStatusEnum() != null) {
            existingResource.setResourceStatusEnum(resource.getResourceStatusEnum());
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
