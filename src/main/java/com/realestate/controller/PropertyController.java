package com.realestate.controller;

import com.realestate.model.Property;
import com.realestate.model.User;
import com.realestate.service.PropertyService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // ============================
    // LIST PROPERTIES
    // ============================
    @GetMapping("/list")
    public String listProperties(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 Model model) {

        Page<Property> propertyPage = propertyService.listAvailable(page, size);
        model.addAttribute("properties", propertyPage.getContent());

        return "listProperties";
    }

    // ============================
    // SHOW ADD PROPERTY FORM
    // ============================
    @GetMapping("/add")
    public String addForm(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null)
            return "redirect:/login";

        // ROLE CHECK
        if (!user.getRole().equals("ROLE_OWNER") && !user.getRole().equals("ROLE_ADMIN")) {
            return "access-denied";  // this page must exist in templates
        }

        model.addAttribute("property", new Property());
        return "addProperty";
    }

    // ============================
    // PROCESS ADD PROPERTY FORM
    // ============================
    @PostMapping("/add")
    public String addProperty(@Valid @ModelAttribute Property property,
                              BindingResult br,
                              HttpSession session,
                              Model model) {

        User user = (User) session.getAttribute("user");

        if (user == null)
            return "redirect:/login";

        // ROLE CHECK
        if (!user.getRole().equals("ROLE_OWNER") && !user.getRole().equals("ROLE_ADMIN")) {
            return "access-denied";
        }

        if (br.hasErrors()) {
            return "addProperty";
        }

        propertyService.addProperty(property, user);
        return "redirect:/properties/list";
    }

    // ============================
    // VIEW PROPERTY DETAILS
    // ============================
    @GetMapping("/{id}")
    public String viewDetails(@PathVariable Long id, Model model) {
        Property property = propertyService.findById(id);
        model.addAttribute("property", property);
        return "viewProperty";
    }
}
