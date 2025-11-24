package com.realestate.service;

import com.realestate.model.Property;
import com.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository repo;

    public List<Property> all(){
        return repo.findAll();
    }

    public boolean add(Property p){
        return repo.create(p) > 0;
    }

    public boolean delete(int id){
        return repo.delete(id) > 0;
    }
}
