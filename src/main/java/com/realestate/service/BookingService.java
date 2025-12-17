package com.realestate.service;

import com.realestate.model.Booking;
import com.realestate.model.Property;
import com.realestate.model.User;
import com.realestate.repository.BookingRepository;
import com.realestate.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepo;
    private final PropertyRepository propertyRepo;

    public BookingService(BookingRepository bookingRepo, PropertyRepository propertyRepo) {
        this.bookingRepo = bookingRepo;
        this.propertyRepo = propertyRepo;
    }

    @Transactional
    public Booking createBooking(User user, Long propertyId) {
        Property property = propertyRepo.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));

        if (!property.isAvailable()) {
            throw new IllegalStateException("Property not available");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setProperty(property);
        booking.setStatus("PENDING");

        return bookingRepo.save(booking);
    }

    public List<Booking> getBookingsForUser(Long userId) {
        return bookingRepo.findByUserId(userId);
    }

    @Transactional
    public Booking approveBooking(Long bookingId) {
        Booking b = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        b.setStatus("APPROVED");

        Property p = b.getProperty();
        p.setAvailable(false);
        propertyRepo.save(p);

        return bookingRepo.save(b);
    }

    // ✅ REQUIRED BY ADMIN CONTROLLER
    public long countAllBookings() {
        return bookingRepo.count();
    }

    // ✅ REQUIRED BY ADMIN CONTROLLER
    public List<Booking> findRecentBookings(int limit) {
        return bookingRepo.findTop10ByOrderByCreatedAtDesc();
    }
}
