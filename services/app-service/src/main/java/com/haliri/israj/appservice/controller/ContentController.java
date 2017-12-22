package com.haliri.israj.appservice.controller;

import com.haliri.israj.appcore.domain.content.Item;
import com.haliri.israj.appcore.strategy.content.impl.ContentStrategy;
import com.haliri.israj.appcore.handler.impl.ResponseHandlerImpl;
import com.haliri.israj.appcore.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
public class ContentController {

    @Autowired
    ContentStrategy contentStrategy;

    @Autowired
    ResponseHandlerImpl responseHandlerImpl;

    @RequestMapping(value = "/public/barber/get/content/{type}", method = RequestMethod.GET)
    public Object getTestimonial(@PathVariable(value = "type") String type) {
        List<Item> result = null;
        try{
            result = contentStrategy.getListData(type);
            return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS, null, result);
        } catch (Exception e){
            return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
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
        List<Item> result = null;
        try{
            result = contentStrategy.getListDataById(parameter);
            return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS, null, result);
        } catch (Exception e){
            return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
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
        List<Item> itemList = null;

        Map<String,Object> result = new HashMap();
        result.put("draw", draw);
        result.put("search[value]", search);
        try{
            itemList = contentStrategy.getListDataPerPage(parameters);
            result.put("data", itemList);
            if(itemList.size() > 0){
                result.put("recordsTotal", itemList.get(0).getTotal_count());
                result.put("recordsFiltered", itemList.get(0).getTotal_count());
            }else{
                result.put("recordsTotal", 0);
                result.put("recordsFiltered", 0);
            }

            return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS,null,result);
        } catch (Exception e){
            return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),result);
        }
    }

    @RequestMapping(value = "/secret/barber/insert/content", method = RequestMethod.POST)
    public Object saveTestimonial(@RequestBody Item Item) {
        try {
            contentStrategy.saveData(Item);
            return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/barber/update/content", method = RequestMethod.PUT)
    public Object updateTestimonial(@RequestBody Item Item) {
        try {
            contentStrategy.updateData(Item);
            return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/barber/delete/content/{idContent}", method = RequestMethod.DELETE)
    public Object insertTestimonial(
            @PathVariable(value = "idContent") Integer idContent
    ) {
        try {
            contentStrategy.deleteData(idContent);
            return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
        }
    }
}
