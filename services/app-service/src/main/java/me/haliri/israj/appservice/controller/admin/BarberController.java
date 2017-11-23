package me.haliri.israj.appservice.controller.admin;

import me.haliri.israj.appcore.constant.Response;
import me.haliri.israj.appcore.domain.BarberProfile;
import me.haliri.israj.appcore.domain.ResultMessage;
import me.haliri.israj.appcore.repository.BarberProfileRespository;
import me.haliri.israj.appcore.repository.impl.BarberProfileRepositoryImpl;
import me.haliri.israj.appcore.utils.AppUtils;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
@RequestMapping("/api/barber")
public class BarberController {

    @Autowired
    BarberProfileRespository barberProfileRespository;

    @RequestMapping(value = "/update/profile", method = RequestMethod.PUT)
    public Object updateProfile(@RequestBody BarberProfile barberProfile) {
        try {
            barberProfileRespository.saveOrUpdate(barberProfile);
            ResultMessage.getInstance().setMessage(Response.SUCCESS_SAVE);
            ResultMessage.getInstance().setStatus(HttpStatus.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            ResultMessage.getInstance().setMessage(Response.FAILED_SAVE);
            ResultMessage.getInstance().setError(e.getMessage());
            ResultMessage.getInstance().setStatus(HttpStatus.SC_BAD_REQUEST);
        }
        return ResultMessage.getInstance().getResponse();
    }
}
