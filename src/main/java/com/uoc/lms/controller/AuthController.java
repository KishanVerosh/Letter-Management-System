package com.uoc.lms.controller;

import com.uoc.lms.service.AuthService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String loginPage(@RequestParam(value="error", required=false) String error,
                            Model model) {
        if (error != null) model.addAttribute("error", true);
        return "login";
    }

@PostMapping("/login")
public String login(@RequestParam String username,
                    @RequestParam String password,
                    HttpSession session) {

    if (authService.validate(username, password)) {
        session.setAttribute("username", username); // ✅ store username

        if(authService.userRole(username) == "Admin"){
            return "redirect:/admin";
        }
        return "redirect:/dashboard";
    }



    return "redirect:/?error=true";
}


    // Registration action
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model) {

        boolean success = authService.register(username, password);

        if (!success) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }

        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

@GetMapping("/dashboard")
public String dashboard(HttpSession session, Model model) {

    String username = (String) session.getAttribute("username");

    if (username == null) {
        return "redirect:/"; // 🔒 not logged in
    }

    model.addAttribute("username", username);
    model.addAttribute("userType", "User");     // optional
    model.addAttribute("location", "Sri Lanka");// optional

    return "dashboard";
}

@GetMapping("/admin")
public String userList(HttpSession session, Model model) {
    String username = (String) session.getAttribute("username");
    if (username == null) return "redirect:/"; // 🔒 only logged-in users

    model.addAttribute("users", authService.getAllUsers());
    return "admin"; // this is the template we will create
}


@PostMapping("/logout")
public String logout(HttpSession session) {
    session.invalidate(); // ❌ clear session
    return "redirect:/";
}


}

