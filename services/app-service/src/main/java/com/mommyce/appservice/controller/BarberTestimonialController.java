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
public class BarberTestimonialController {

    @Autowired
    BarberTestimonialStrategy barberTestimonialStrategy;

    @Autowired
    CommonStrategy commonStrategy;

    @RequestMapping(value = "/public/barber/get/testimonial", method = RequestMethod.GET)
    public Object getTestimonial() {
        List<BarberTestimonial> result = null;
        try{
            result = barberTestimonialStrategy.getListData();
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS, null, result);
        } catch (Exception e){
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/barber/get/testimonial", method = RequestMethod.GET)
    public Object getTestimonial(
            @RequestParam(value = "draw", defaultValue = "0") int draw,
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
        List<BarberTestimonial> barberTestimonialList = null;

        Map<String,Object> result = new HashMap();
        result.put("draw", draw);
        result.put("search[value]", search);
        try{
            barberTestimonialList = barberTestimonialStrategy.getListDataPerPage(parameters);
            result.put("data", barberTestimonialList);
            if(barberTestimonialList.size() > 0){
                result.put("recordsTotal", barberTestimonialList.get(0).getTotal_count());
                result.put("recordsFiltered", barberTestimonialList.get(0).getTotal_count());
            }else{
                result.put("recordsTotal", 0);
                result.put("recordsFiltered", 0);
            }

            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS,null,result);
        } catch (Exception e){
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),result);
        }
    }

    @RequestMapping(value = "/secret/barber/insert/testimonial", method = RequestMethod.POST)
    public Object saveTestimonial(
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "age") int age
    ) {
        BarberTestimonial barberTestimonial = new BarberTestimonial();
        barberTestimonial.setSubject(subject);
        barberTestimonial.setDescription(description);
        barberTestimonial.setAge(age);
        try {
            barberTestimonialStrategy.saveData(barberTestimonial);
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/barber/update/testimonial", method = RequestMethod.PUT)
    public Object updateTestimonial(
            @RequestParam(value = "idTestimonial") int idTestimonial,
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "age") int age
    ) {
        BarberTestimonial barberTestimonial = new BarberTestimonial();
        barberTestimonial.setSubject(subject);
        barberTestimonial.setDescription(description);
        barberTestimonial.setAge(age);
        barberTestimonial.setIdTestimonial(idTestimonial);
        try {
            barberTestimonialStrategy.updateData(barberTestimonial);
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/barber/delete/testimonial/{idTestimonial}", method = RequestMethod.DELETE)
    public Object insertTestimonial(
            @PathVariable(value = "idTestimonial") Integer idTestimonial
    ) {
        try {
            barberTestimonialStrategy.deleteData(idTestimonial);
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }
}
