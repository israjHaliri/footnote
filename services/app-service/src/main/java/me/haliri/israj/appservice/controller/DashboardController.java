package me.haliri.israj.appservice.controller;

import me.haliri.israj.appcore.utils.AppUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
@RequestMapping("/secret")
public class DashboardController {
    @RequestMapping("/dashboard")
    public String dashboard(){
        return "this is dashboard";
    }
}
