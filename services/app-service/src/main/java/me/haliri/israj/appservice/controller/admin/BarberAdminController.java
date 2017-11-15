package me.haliri.israj.appservice.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
@RequestMapping("/api/barber")
public class BarberAdminController {
    @RequestMapping("/dashboard")
    public String dashboard(){
        return "this is dashboard";
    }
}
