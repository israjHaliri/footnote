package me.haliri.israj.appservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
    @RequestMapping("/public")
public class HomeController {

    @RequestMapping("/home")
    public String hometest(){
       return "this is home";
    }
}
