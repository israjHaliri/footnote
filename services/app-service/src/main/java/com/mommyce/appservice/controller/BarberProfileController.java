package com.mommyce.appservice.controller;

import com.mommyce.appcore.constant.ResponseStatus;
import com.mommyce.appcore.domain.barber.BarberProfile;
import com.mommyce.appcore.domain.barber.BarberTestimonial;
import com.mommyce.appcore.strategy.barber.impl.BarberProfileStrategy;
import com.mommyce.appcore.strategy.barber.impl.BarberTestimonialStrategy;
import com.mommyce.appcore.strategy.common.impl.CommonStrategy;
import com.mommyce.appcore.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
public class BarberProfileController {

    @Autowired
    BarberProfileStrategy barberProfileStrategy;

    @Autowired
    CommonStrategy commonStrategy;

    @RequestMapping("/secret/barber/get/profile")
    public Object getProfile() {
        try{
            BarberProfile barberProfile = barberProfileStrategy.getData();
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS, null, barberProfile);
        } catch (Exception e){
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/barber/update/profile", method = RequestMethod.PUT)
    public Object updateProfile(@RequestBody BarberProfile barberProfile) {
        try {
            barberProfileStrategy.saveOrUpdate(barberProfile);
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            AppUtils.getLogger(this).error("ERROR PROFILE LOG SAVE OR UPDATE: {}", e.getMessage());
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }
}
