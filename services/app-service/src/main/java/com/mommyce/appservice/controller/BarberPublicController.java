package com.mommyce.appservice.controller;

import com.mommyce.appcore.constant.Response;
import com.mommyce.appcore.constant.ResponseStatus;
import com.mommyce.appcore.domain.barber.BarberGuestBook;
import com.mommyce.appcore.domain.barber.BarberProfile;
import com.mommyce.appcore.domain.barber.BarberTestimonial;
import com.mommyce.appcore.domain.common.ResultMessage;
import com.mommyce.appcore.strategy.barber.impl.BarberGuestBookStrategy;
import com.mommyce.appcore.strategy.barber.impl.BarberProfileStrategy;
import com.mommyce.appcore.strategy.barber.impl.BarberTestimonialStrategy;
import com.mommyce.appcore.strategy.common.impl.CommonStrategy;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
@RequestMapping("/service/barber")
public class BarberPublicController {

    @Autowired
    BarberProfileStrategy barberProfileDao;

    @Autowired
    BarberGuestBookStrategy barberGuestBookDao;

    @Autowired
    BarberTestimonialStrategy barberTestimonialDao;

    @Autowired
    CommonStrategy commonStrategy;

    @RequestMapping("/get/profile")
    public BarberProfile getProfile() {
        return barberProfileDao.getData();
    }

    @RequestMapping("/get/guest_book")
    public Object getGuestBook() {
        List<BarberGuestBook> barberGuestBookList = null;
        try{
            barberGuestBookList = (List<BarberGuestBook>) barberGuestBookDao.getListData();
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS, null, barberGuestBookList);
        } catch (Exception e){
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/insert/guest_book", method = RequestMethod.POST)
    public Object insertGuestBook(
            @RequestParam(value = "username") String username
    ) {
        BarberGuestBook barberGuestBook = new BarberGuestBook();
        barberGuestBook.setUsername(username);
        try {
            barberGuestBookDao.saveData(barberGuestBook);
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return commonStrategy.setResultMessage(ResponseStatus.FAILED, e.getMessage(), null);
        }
    }

    @RequestMapping("/get/testimonial")
    public Object getTestimonial() {
        List<BarberTestimonial> result = null;
        try{
            result = barberTestimonialDao.getListData();
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS, null, result);
        } catch (Exception e){
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
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
        try {
            barberTestimonialDao.saveData(barberTestimonial);
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }
}
