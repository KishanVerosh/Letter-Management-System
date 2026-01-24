package com.uoc.lms.controller;

import com.uoc.lms.model.Location;
import com.uoc.lms.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/showocation")
    public String listLocations(Model model) {
        model.addAttribute("locations", locationService.getAllLocations());
        return "show-location";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("location", new Location());
        return "add-location";
    }

    @PostMapping("/add")
    public String saveLocation(@ModelAttribute Location location) {
        locationService.saveLocation(location);
        return "redirect:/locations";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("location", locationService.getLocationById(id));
        return "location/edit";
    }

    @PostMapping("/edit")
    public String updateLocation(@ModelAttribute Location location) {
        locationService.saveLocation(location);
        return "redirect:/locations";
    }
}
