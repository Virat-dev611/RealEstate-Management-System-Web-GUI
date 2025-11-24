package com.realestate.controller;

import com.realestate.model.Property;
import com.realestate.model.User;
import com.realestate.service.BookingService;
import com.realestate.service.PropertyService;
import com.realestate.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private BookingService bookingService;

    @GetMapping({"/", "/index"})
    public String home(Model model){
        model.addAttribute("properties", propertyService.all());
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session){
        String hash = DigestUtils.sha256Hex(password);
        User u = userService.login(email, hash);

        if(u == null)
            return "redirect:/login?error=true";

        session.setAttribute("user", u);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session){
        User u = (User) session.getAttribute("user");

        if(u == null)
            return "redirect:/login";

        model.addAttribute("user", u);
        model.addAttribute("properties", propertyService.all());
        return "dashboard";
    }

    @PostMapping("/property/add")
    public String add(@ModelAttribute Property p, HttpSession session){
        User u = (User) session.getAttribute("user");
        if(u == null)
            return "redirect:/login";

        p.setOwnerId(u.getId());
        p.setStatus("AVAILABLE");
        propertyService.add(p);

        return "redirect:/dashboard";
    }

    @PostMapping("/property/delete")
    public String delete(@RequestParam int id, HttpSession session){
        if(session.getAttribute("user") == null)
            return "redirect:/login";

        propertyService.delete(id);
        return "redirect:/dashboard";
    }

    @PostMapping("/booking")
    public String booking(@RequestParam int propertyId,
                          @RequestParam String message,
                          HttpSession session){
        User u = (User) session.getAttribute("user");

        if(u == null)
            return "redirect:/login";

        bookingService.book(propertyId, u.getId(), message);
        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
