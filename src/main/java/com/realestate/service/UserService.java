package com.realestate.service;

import com.realestate.model.User;
import com.realestate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User login(String email, String passwordHash){
        return repo.findByEmailAndPassword(email, passwordHash);
    }

    public boolean register(String name, String email, String passwordHash){
        return repo.createUser(name, email, passwordHash) > 0;
    }
}
