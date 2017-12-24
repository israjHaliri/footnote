package com.haliri.israj.appservice.controller;

import com.haliri.israj.appcore.domain.content.Testimonial;
import com.haliri.israj.appcore.handler.ResponseHandler;
import com.haliri.israj.appcore.strategy.content.impl.TestimonialStrategy;
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
public class TestimonialController {

    @Autowired
    TestimonialStrategy testimonialStrategy;

    @Autowired
    ResponseHandler responseHandler;

    @RequestMapping(value = "/public/get/testimonial", method = RequestMethod.GET)
    public Object getTestimonial() {
        List<Testimonial> result = null;
        try{
            result = testimonialStrategy.getListData();
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS, null, result);
        } catch (Exception e){
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/get_by_id/testimonial/{idTestimonial}", method = RequestMethod.GET)
    public Object getTestimonialById(@PathVariable(value = "idTestimonial") Integer idTestimonial) {
        List<Testimonial> result = null;
        try{
            result = testimonialStrategy.getListDataById(idTestimonial);
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS, null, result);
        } catch (Exception e){
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/get/testimonial", method = RequestMethod.GET)
    public Object getTestimonialPerPage(
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
        List<Testimonial> testimonialList = null;

        Map<String,Object> result = new HashMap();
        result.put("draw", draw);
        result.put("search[value]", search);
        try{
            testimonialList = testimonialStrategy.getListDataPerPage(parameters);
            result.put("data", testimonialList);
            if(testimonialList.size() > 0){
                result.put("recordsTotal", testimonialList.get(0).getTotal_count());
                result.put("recordsFiltered", testimonialList.get(0).getTotal_count());
            }else{
                result.put("recordsTotal", 0);
                result.put("recordsFiltered", 0);
            }

            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS,null,result);
        } catch (Exception e){
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),result);
        }
    }

    @RequestMapping(value = "/public/insert/testimonial", method = RequestMethod.POST)
    public Object saveTestimonial(
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "age") int age
    ) {
        Testimonial testimonial = new Testimonial();
        testimonial.setSubject(subject);
        testimonial.setDescription(description);
        testimonial.setAge(age);
        try {
            testimonialStrategy.saveData(testimonial);
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/update/testimonial", method = RequestMethod.PUT)
    public Object updateTestimonial(
            @RequestParam(value = "idTestimonial") int idTestimonial,
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "age") int age
    ) {
        Testimonial testimonial = new Testimonial();
        testimonial.setSubject(subject);
        testimonial.setDescription(description);
        testimonial.setAge(age);
        testimonial.setIdTestimonial(idTestimonial);
        try {
            testimonialStrategy.updateData(testimonial);
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/delete/testimonial/{idTestimonial}", method = RequestMethod.DELETE)
    public Object deleteTestimonial(
            @PathVariable(value = "idTestimonial") Integer idTestimonial
    ) {
        try {
            testimonialStrategy.deleteData(idTestimonial);
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
        }
    }
}
