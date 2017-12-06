package me.haliri.israj.appservice.controller;

import me.haliri.israj.appcore.constant.Response;
import me.haliri.israj.appcore.domain.barber.BarberGuestBook;
import me.haliri.israj.appcore.domain.barber.BarberProfile;
import me.haliri.israj.appcore.domain.barber.BarberTestimonial;
import me.haliri.israj.appcore.domain.common.ResultMessage;
import me.haliri.israj.appcore.strategy.BarberGuestBookDao;
import me.haliri.israj.appcore.strategy.BarberProfileDao;
import me.haliri.israj.appcore.strategy.BarberTestimonialDao;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
@RequestMapping("/service/barber")
public class BarberPublicController {

    @Autowired
    BarberProfileDao barberProfileDao;

    @Autowired
    BarberGuestBookDao barberGuestBookDao;

    @Autowired
    BarberTestimonialDao barberTestimonialDao;

    @RequestMapping("/get/profile")
    public BarberProfile getProfile() {
        return barberProfileDao.getData();
    }

    @RequestMapping("/get/guest_book")
    public List<BarberGuestBook> getGuestBook() {
        return barberGuestBookDao.getListData();
    }

    @RequestMapping(value = "/insert/guest_book", method = RequestMethod.POST)
    public Object insertGuestBook(
            @RequestParam(value = "username") String username
    ) {
        BarberGuestBook barberGuestBook = new BarberGuestBook();
        barberGuestBook.setUsername(username);
        ResultMessage.getInstance().setMessage(Response.SUCCESS_SAVE);
        ResultMessage.getInstance().setStatus(HttpStatus.SC_OK);
        try {
            barberGuestBookDao.saveData(barberGuestBook);
        } catch (Exception e) {
            e.printStackTrace();
            ResultMessage.getInstance().setMessage(Response.FAILED_SAVE);
            ResultMessage.getInstance().setError(e.getMessage());
            ResultMessage.getInstance().setStatus(HttpStatus.SC_METHOD_FAILURE);
        }
        return ResultMessage.getInstance().getResponse();
    }

    @RequestMapping("/get/testimonial")
    public List<BarberTestimonial> getTestimonial() {
        return barberTestimonialDao.getListData();
    }

    @RequestMapping(value = "/insert/testimonial", method = RequestMethod.POST)
    public Object insertTestimonial(
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "age") int age
    ) {
        BarberTestimonial barberTestimonial = new BarberTestimonial();
        barberTestimonial.setSubject(subject);
        barberTestimonial.setDescription(description);
        barberTestimonial.setAge(age);
        ResultMessage.getInstance().setMessage(Response.SUCCESS_SAVE);
        ResultMessage.getInstance().setStatus(HttpStatus.SC_OK);
        try {
            barberTestimonialDao.saveData(barberTestimonial);
        } catch (Exception e) {
            e.printStackTrace();
            ResultMessage.getInstance().setMessage(Response.FAILED_SAVE);
            ResultMessage.getInstance().setError(e.getMessage());
            ResultMessage.getInstance().setStatus(HttpStatus.SC_METHOD_FAILURE);
        }
        return ResultMessage.getInstance().getResponse();
    }
}
