package com.realestate.service;

import com.realestate.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repo;

    public boolean book(int propertyId, int userId, String message){
        return repo.createBooking(propertyId, userId, message);
    }
}

