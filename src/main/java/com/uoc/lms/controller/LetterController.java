package com.uoc.lms.controller;
//import com.uoc.lms.service.AuthService;
import com.uoc.lms.service.LetterService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LetterController {

    private final LetterService letterService;


    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }

    @GetMapping("/addLetter")
    public String letterInPage(HttpSession session, Model model) {

        String username = (String) session.getAttribute("username");
        String userType = (String) session.getAttribute("userType"); // e.g. "admin", "normal"

        if (username == null) {
            return "redirect:/";
        }

        model.addAttribute("username", username);
        model.addAttribute("userType", userType == null ? "normal" : userType);

        return "letter-in";
    }

@PostMapping("/letter-in")
public String submitLetter(@RequestParam String title,
                           @RequestParam String sender_address,
                           @RequestParam String receiver_address,
                           @RequestParam(required = false) String letter_type,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {

    String username = (String) session.getAttribute("username");

    if (username == null) {
        return "redirect:/";
    }

    String status = (letter_type != null) ? letter_type : "Letter In";

    letterService.saveLetter(
            title,
            sender_address,
            receiver_address,
            status,
            username
    );

    redirectAttributes.addFlashAttribute(
            "success",
            "Letter saved successfully!"
    );

    return "redirect:/addLetter";
}

@GetMapping("/letterList")
public String myLettersPage(HttpSession session, Model model) {
    String username = (String) session.getAttribute("username");
    if (username == null) {
        return "redirect:/";
    }

    model.addAttribute("username", username);
    model.addAttribute("letters", letterService.getLettersForUser(username));

    return "letter-list"; // the Thymeleaf view
}

@GetMapping("/updateLetter")
    public String updateLetterPage() {
        return "update-letter";
    }

        @PostMapping("/updateLetter")
    public String updateLetter(
            @RequestParam String reference_number,
            @RequestParam String letter_type,
            RedirectAttributes redirectAttributes
    ) {
        boolean updated = letterService.updateLetter(reference_number, letter_type);

        if (updated) {
            redirectAttributes.addFlashAttribute("success", "Letter updated successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Reference number not found");
        }

        return "redirect:/updateLetter";
    }
}

