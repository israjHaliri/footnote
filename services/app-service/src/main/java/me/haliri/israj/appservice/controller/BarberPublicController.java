package me.haliri.israj.appservice.controller;

import me.haliri.israj.appcore.domain.BarberProfile;
import me.haliri.israj.appcore.repository.BarberProfileRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
@RequestMapping("/service/barber")
public class BarberPublicController {

    @Autowired
    BarberProfileRespository barberProfileRespository;

    @RequestMapping("/get/profile")
    public BarberProfile dashboard() {
        return barberProfileRespository.getData();
    }
}
