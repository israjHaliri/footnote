package com.mommyce.appservice.controller;

import com.mommyce.appcore.constant.ResponseStatus;
import com.mommyce.appcore.domain.barber.BarberGuestBook;
import com.mommyce.appcore.strategy.barber.impl.BarberGuestBookStrategy;
import com.mommyce.appcore.strategy.common.impl.CommonStrategy;
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
public class BarberGuestBookController {
    @Autowired
    BarberGuestBookStrategy barberGuestBookStrategy;

    @Autowired
    CommonStrategy commonStrategy;

    @RequestMapping("/secret/barber/get/guest_book")
    public Object getGuestBook() {
        List<BarberGuestBook> barberGuestBookList = null;
        try{
            barberGuestBookList = (List<BarberGuestBook>) barberGuestBookStrategy.getListData();
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS, null, barberGuestBookList);
        } catch (Exception e){
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/public/barber/insert/guest_book", method = RequestMethod.POST)
    public Object insertGuestBook(
            @RequestParam(value = "username") String username
    ) {
        BarberGuestBook barberGuestBook = new BarberGuestBook();
        barberGuestBook.setUsername(username);
        try {
            barberGuestBookStrategy.saveData(barberGuestBook);
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return commonStrategy.setResultMessage(ResponseStatus.FAILED, e.getMessage(), null);
        }
    }
}
