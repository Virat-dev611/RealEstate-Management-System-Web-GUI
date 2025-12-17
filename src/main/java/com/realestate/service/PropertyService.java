package com.realestate.service;

import com.realestate.model.Property;
import com.realestate.model.User;
import com.realestate.repository.PropertyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepo;

    public PropertyService(PropertyRepository propertyRepo) {
        this.propertyRepo = propertyRepo;
    }

    @Transactional
    public Property addProperty(Property property, User owner) {
        property.setOwner(owner);
        property.setAvailable(true);
        return propertyRepo.save(property);
    }

    public Page<Property> listAvailable(int page, int size) {
        return propertyRepo.findAllByAvailableTrue(PageRequest.of(page, size));
    }

    public Property findById(Long id) {
        return propertyRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));
    }

    @Transactional
    public Property updateProperty(Long id, Property updated, User requester) {
        Property p = findById(id);

        boolean isOwner = p.getOwner().getId().equals(requester.getId());
        boolean isAdmin = "ROLE_ADMIN".equals(requester.getRole());

        if (!isOwner && !isAdmin) {
            throw new SecurityException("Not authorized to update property");
        }

        p.setTitle(updated.getTitle());
        p.setDescription(updated.getDescription());
        p.setPrice(updated.getPrice());
        p.setLocation(updated.getLocation());

        return propertyRepo.save(p);
    }

    public long countAllProperties() {
        return propertyRepo.count();
    }

    public long countAvailableProperties() {
        return propertyRepo.countByAvailableTrue();
    }
}
