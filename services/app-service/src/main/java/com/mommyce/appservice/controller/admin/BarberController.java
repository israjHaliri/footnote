package com.mommyce.appservice.controller.admin;

import me.haliri.israj.appcore.constant.Response;
import me.haliri.israj.appcore.domain.barber.BarberProfile;
import me.haliri.israj.appcore.domain.barber.BarberTestimonial;
import me.haliri.israj.appcore.domain.common.ResultMessage;
import me.haliri.israj.appcore.strategy.BarberProfileDao;
import me.haliri.israj.appcore.strategy.BarberTestimonialDao;
import me.haliri.israj.appcore.utils.AppUtils;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
@RequestMapping("/api/barber")
public class BarberController {

    @Autowired
    BarberProfileDao barberProfileDao;

    @Autowired
    BarberTestimonialDao barberTestimonialDao;

    @RequestMapping(value = "/update/profile", method = RequestMethod.PUT)
    public Object updateProfile(@RequestBody BarberProfile barberProfile) {
        ResultMessage.getInstance().setMessage(Response.SUCCESS_SAVE);
        ResultMessage.getInstance().setStatus(HttpStatus.SC_OK);
        try {
            barberProfileDao.saveOrUpdate(barberProfile);
        } catch (Exception e) {
            AppUtils.getLogger(this).error("ERROR PROFILE LOG SAVE OR UPDATE: {}", e.getMessage());
            ResultMessage.getInstance().setMessage(Response.FAILED_SAVE);
            ResultMessage.getInstance().setError(e.getMessage());
            ResultMessage.getInstance().setStatus(HttpStatus.SC_METHOD_FAILURE);
        }
        return ResultMessage.getInstance().getResponse();
    }

    @RequestMapping("/get/testimonial")
    public Map getTestimonial(
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
        List<BarberTestimonial> list = barberTestimonialDao.getListDataByParameters(parameters);

        Map<String,Object> result = new HashMap();
        result.put("draw", draw);
        result.put("search[value]", search);
        result.put("data", list);
        if (list.size() < 1 || list.isEmpty()) {
            result.put("recordsTotal", 0);
            result.put("recordsFiltered", 0);
        } else {
            result.put("recordsTotal", list.get(0).getTotal_count());
            result.put("recordsFiltered", list.get(0).getTotal_count());
        }
        return result;
    }

    @RequestMapping(value = "/update/testimonial", method = RequestMethod.PUT)
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
        ResultMessage.getInstance().setMessage(Response.SUCCESS_UPDATE);
        ResultMessage.getInstance().setStatus(HttpStatus.SC_OK);
        try {
            barberTestimonialDao.updateData(barberTestimonial);
        } catch (Exception e) {
            e.printStackTrace();
            ResultMessage.getInstance().setMessage(Response.FAILED_SAVE);
            ResultMessage.getInstance().setError(e.getMessage());
            ResultMessage.getInstance().setStatus(HttpStatus.SC_METHOD_FAILURE);
        }
        return ResultMessage.getInstance().getResponse();
    }

    @RequestMapping(value = "/delete/testimonial/{idTestimonial}", method = RequestMethod.DELETE)
    public Object insertTestimonial(
            @PathVariable(value = "idTestimonial") int idTestimonial
    ) {
        ResultMessage.getInstance().setMessage(Response.SUCCESS_DELETE);
        ResultMessage.getInstance().setStatus(HttpStatus.SC_OK);
        try {
            barberTestimonialDao.deleteData(idTestimonial);
        } catch (Exception e) {
            e.printStackTrace();
            ResultMessage.getInstance().setMessage(Response.FAILED_DELETE);
            ResultMessage.getInstance().setError(e.getMessage());
            ResultMessage.getInstance().setStatus(HttpStatus.SC_METHOD_FAILURE);
        }
        return ResultMessage.getInstance().getResponse();
    }
}
