package com.mommyce.appservice.controller;

import com.mommyce.appcore.constant.ResponseStatus;
import com.mommyce.appcore.domain.barber.BarberGuestBook;
import com.mommyce.appcore.strategy.barber.impl.BarberGuestBookStrategy;
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
public class BarberGuestBookController {
    @Autowired
    BarberGuestBookStrategy barberGuestBookStrategy;

    @Autowired
    CommonStrategy commonStrategy;

    @RequestMapping(value = "/public/barber/get/guest_book", method = RequestMethod.GET)
    public Object getGuestBook() {
        List<BarberGuestBook> barberGuestBookList = null;
        try{
            barberGuestBookList = barberGuestBookStrategy.getListData();
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS, null, barberGuestBookList);
        } catch (Exception e){
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/barber/get/guest_book_one_month", method = RequestMethod.GET)
    public Object getGuestBookOneMonth() {
        List<BarberGuestBook> barberGuestBookList = null;
        try{
            barberGuestBookList = barberGuestBookStrategy.getOneMonthListData();
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS, null, barberGuestBookList);
        } catch (Exception e){
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/barber/get_by_id/guest_book/{idGuestBook}", method = RequestMethod.GET)
    public Object getGuestBookById(@PathVariable(value = "idGuestBook") Integer idGuestBook) {
        List<BarberGuestBook> barberGuestBookList = null;
        try{
            barberGuestBookList = barberGuestBookStrategy.getListDataById(idGuestBook);
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS, null, barberGuestBookList);
        } catch (Exception e){
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/barber/get/guest_book", method = RequestMethod.GET)
    public Object getGuestBookPerPage(@RequestParam(value = "draw", defaultValue = "0") int draw,
                               @RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "length", defaultValue = "10") int length,
                               @RequestParam(value = "columns[0][data]", defaultValue = "") String firstColumn,
                               @RequestParam(value = "order[0][column]", defaultValue = "0") int sortIndex,
                               @RequestParam(value = "order[0][dir]", defaultValue = "ASC") String sortDir,
                               @RequestParam(value = "search[value]", defaultValue = "") String search
    ) {
        AppUtils.getLogger(this).debug(
                "datatable info = draw : {} , start : {}, length : {}, firstColumn : {}, sortIndex : {}, sortDir : {}, search : {},",
                draw, start, length, firstColumn, sortIndex, sortDir, search
        );
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("start",(start + 1));
        parameters.put("length",length + start);
        parameters.put("search",search);
        List<BarberGuestBook> barberGuestBookList = null;

        Map<String,Object> result = new HashMap();
        result.put("draw", draw);
        result.put("search[value]", search);
        try{
            barberGuestBookList = barberGuestBookStrategy.getListDataPerPage(parameters);
            result.put("data", barberGuestBookList);
            if(barberGuestBookList.size() > 0){
                result.put("recordsTotal", barberGuestBookList.get(0).getTotal_count());
                result.put("recordsFiltered", barberGuestBookList.get(0).getTotal_count());
            }else{
                result.put("recordsTotal", 0);
                result.put("recordsFiltered", 0);
            }

            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS,null,result);
        } catch (Exception e){
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),result);
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

    @RequestMapping(value = "/secret/barber/delete/guest_book/{idGuestBook}", method = RequestMethod.DELETE)
    public Object deleteGuestBook(
            @PathVariable(value = "idGuestBook") Integer idGuestBook
    ) {
        try {
            barberGuestBookStrategy.deleteData(idGuestBook);
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }
}
