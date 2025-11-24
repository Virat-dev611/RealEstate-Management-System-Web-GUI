package com.realestate.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BookingRepository {

    @Autowired
    private JdbcTemplate jdbc;

    @Transactional
    public boolean createBooking(int propertyId, int userId, String message){
        int ok = jdbc.update(
            "UPDATE properties SET status='BOOKED' WHERE id=? AND status='AVAILABLE'",
            propertyId
        );
        if(ok == 0) return false;

        jdbc.update("INSERT INTO bookings(property_id,user_id,message,status) VALUES (?,?,?,?)",
            propertyId, userId, message, "PENDING");

        return true;
    }
}
