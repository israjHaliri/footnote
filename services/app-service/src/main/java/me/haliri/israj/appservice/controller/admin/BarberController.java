package me.haliri.israj.appservice.controller.admin;

import me.haliri.israj.appcore.repository.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
@RequestMapping("/api/barber_service")
public class BarberController {

    @Autowired
    BarberRepository barberRepository;

    @RequestMapping("/get/profile")
    public List dashboard(){
        return barberRepository.getProfile();
    }
}
