package com.mommyce.appservice.controller;

import com.mommyce.appcore.constant.ResponseStatus;
import com.mommyce.appcore.constant.Type;
import com.mommyce.appcore.domain.barber.BarberContent;
import com.mommyce.appcore.domain.barber.BarberContent;
import com.mommyce.appcore.strategy.barber.impl.BarberContentStrategy;
import com.mommyce.appcore.strategy.barber.impl.BarberContentStrategy;
import com.mommyce.appcore.strategy.common.impl.CommonStrategy;
import com.mommyce.appcore.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
public class BarberContentController {

    @Autowired
    BarberContentStrategy barberContentStrategy;

    @Autowired
    CommonStrategy commonStrategy;

    @RequestMapping(value = "/public/barber/get/content/{type}", method = RequestMethod.GET)
    public Object getTestimonial(@PathVariable(value = "type") String type) {
        List<BarberContent> result = null;
        try{
            result = barberContentStrategy.getListData(type);
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS, null, result);
        } catch (Exception e){
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/barber/get_by_id/content/{type}/{idContent}", method = RequestMethod.GET)
    public Object getTestimonial(
            @PathVariable(value = "type") String type,
            @PathVariable(value = "idContent") Integer idContent
    ) {
        Map parameter = new HashMap();
        parameter.put("type",type);
        parameter.put("idContent",idContent);
        List<BarberContent> result = null;
        try{
            result = barberContentStrategy.getListDataById(parameter);
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS, null, result);
        } catch (Exception e){
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/barber/get/content", method = RequestMethod.GET)
    public Object getTestimonialPerPage(
            @RequestParam(value = "draw", defaultValue = "0") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "10") int length,
            @RequestParam(value = "columns[0][data]", defaultValue = "") String firstColumn,
            @RequestParam(value = "order[0][column]", defaultValue = "0") int sortIndex,
            @RequestParam(value = "order[0][dir]", defaultValue = "ASC") String sortDir,
            @RequestParam(value = "search[value]", defaultValue = "") String search,
            @RequestParam(value = "type", defaultValue = "") String type
    ) {
        AppUtils.getLogger(this).debug(
                "datatable info = draw : {} , start : {}, length : {}, firstColumn : {}, sortIndex : {}, sortDir : {}, search : {},",
                draw, start, length, firstColumn, sortIndex, sortDir, search
        );
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("start",(start + 1));
        parameters.put("length",length + start);
        parameters.put("search",search);
        parameters.put("type",type);
        List<BarberContent> barberContentList = null;

        Map<String,Object> result = new HashMap();
        result.put("draw", draw);
        result.put("search[value]", search);
        try{
            barberContentList = barberContentStrategy.getListDataPerPage(parameters);
            result.put("data", barberContentList);
            if(barberContentList.size() > 0){
                result.put("recordsTotal", barberContentList.get(0).getTotal_count());
                result.put("recordsFiltered", barberContentList.get(0).getTotal_count());
            }else{
                result.put("recordsTotal", 0);
                result.put("recordsFiltered", 0);
            }

            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS,null,result);
        } catch (Exception e){
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),result);
        }
    }

    @RequestMapping(value = "/secret/barber/insert/content", method = RequestMethod.POST)
    public Object saveTestimonial(@RequestBody BarberContent barberContent) {
        try {
            barberContentStrategy.saveData(barberContent);
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/barber/update/content", method = RequestMethod.PUT)
    public Object updateTestimonial(@RequestBody BarberContent barberContent) {
        try {
            barberContentStrategy.updateData(barberContent);
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/barber/delete/content/{idContent}", method = RequestMethod.DELETE)
    public Object insertTestimonial(
            @PathVariable(value = "idContent") Integer idContent
    ) {
        try {
            barberContentStrategy.deleteData(idContent);
            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return commonStrategy.setResultMessage(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }
}
