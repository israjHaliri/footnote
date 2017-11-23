package me.haliri.israj.appservice.controller;

import me.haliri.israj.appcore.constant.Response;
import me.haliri.israj.appcore.domain.BarberGuestBook;
import me.haliri.israj.appcore.domain.BarberProfile;
import me.haliri.israj.appcore.domain.ResultMessage;
import me.haliri.israj.appcore.repository.BarberGuestBookRespository;
import me.haliri.israj.appcore.repository.BarberProfileRespository;
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
    BarberProfileRespository barberProfileRespository;

    @Autowired
    BarberGuestBookRespository barberGuestBookRespository;

    @RequestMapping("/get/profile")
    public BarberProfile getProfile() {
        return barberProfileRespository.getData();
    }

    @RequestMapping("/get/guest_book")
    public List<BarberGuestBook> getGuestBook() {
        return barberGuestBookRespository.getListData();
    }

    @RequestMapping(value = "/insert/guest_book", method = RequestMethod.POST)
    public Object getGuestBook(
            @RequestParam(value = "username") String username
    ) {
        try {
            BarberGuestBook barberGuestBook = new BarberGuestBook();
            barberGuestBook.setUsername(username);
            barberGuestBookRespository.saveData(barberGuestBook);
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
