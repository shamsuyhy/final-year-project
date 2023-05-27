package com.backend.app.controller;

import com.backend.app.model.DeliveryAgency;
import com.backend.app.service.DeliveryAgencyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/agencies")
public class DeliveryAgencyController {
    DeliveryAgencyService deliveryAgencyService;

    public DeliveryAgencyController(DeliveryAgencyService deliveryAgencyService) {
        this.deliveryAgencyService = deliveryAgencyService;
    }

    @PostMapping("addAgency")
    public DeliveryAgency addAgency(@RequestBody DeliveryAgency deliveryAgency){
        return deliveryAgencyService.addAgency(deliveryAgency);
    }
    @GetMapping
    public List<DeliveryAgency> getAllAgencies(){
        return deliveryAgencyService.getAllAgencies();
    }
}
