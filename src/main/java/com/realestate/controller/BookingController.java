package com.realestate.controller;

import com.realestate.model.Booking;
import com.realestate.model.User;
import com.realestate.service.BookingService;
import com.realestate.service.PropertyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;
    private final PropertyService propertyService;

    public BookingController(BookingService bookingService, PropertyService propertyService) {
        this.bookingService = bookingService;
        this.propertyService = propertyService;
    }

    @PostMapping("/create/{propertyId}")
    public String create(@PathVariable Long propertyId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        try {
            Booking b = bookingService.createBooking(user, propertyId);
            model.addAttribute("message", "Booking created (PENDING)");
            return "redirect:/properties/list";
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            return "viewProperty";
        }
    }

    @GetMapping("/my")
    public String myBookings(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        model.addAttribute("bookings", bookingService.getBookingsForUser(user.getId()));
        return "myBookings";
    }
}
