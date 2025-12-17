package com.realestate.controller;

import com.realestate.model.Booking;
import com.realestate.model.User;
import com.realestate.service.BookingService;
import com.realestate.service.PropertyService;
import com.realestate.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BookingService bookingService;
    private final PropertyService propertyService;
    private final UserRepository userRepo;

    public AdminController(BookingService bookingService, PropertyService propertyService, UserRepository userRepo) {
        this.bookingService = bookingService;
        this.propertyService = propertyService;
        this.userRepo = userRepo;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User current = (User) session.getAttribute("user");
        if (current == null || !"ROLE_ADMIN".equals(current.getRole())) {
            model.addAttribute("error", "Access denied");
            return "access-denied";
        }

        long totalProperties = propertyService.countAllProperties(); // implement in service
        long totalBookings = bookingService.countAllBookings();      // implement in service
        long totalUsers = userRepo.count();

        List<Booking> recentBookings = bookingService.findRecentBookings(10); // implement in service
        model.addAttribute("totalProperties", totalProperties);
        model.addAttribute("totalBookings", totalBookings);
        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("recentBookings", recentBookings);
        return "adminDashboard";
    }

    @GetMapping("/bookings/approve/{id}")
    public String approveBooking(@PathVariable Long id, HttpSession session, Model model) {
        User current = (User) session.getAttribute("user");
        if (current == null || !"ROLE_ADMIN".equals(current.getRole())) {
            model.addAttribute("error", "Access denied");
            return "access-denied";
        }
        bookingService.approveBooking(id);
        model.addAttribute("success", "Booking approved");
        return "redirect:/admin/dashboard";
    }
}
