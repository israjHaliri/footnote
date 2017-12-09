package com.mommyce.appservice.controller;

import com.mommyce.appcore.AppCore;
import com.mommyce.appcore.constant.ResponseStatus;
import com.mommyce.appcore.constant.Type;
import com.mommyce.appcore.domain.barber.BarberAttachment;
import com.mommyce.appcore.domain.barber.BarberContent;
import com.mommyce.appcore.strategy.barber.impl.BarberAttachmentStrategy;
import com.mommyce.appcore.strategy.common.impl.CommonStrategy;
import com.mommyce.appcore.utils.AppUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
public class BarberAttachmentController {

    @Autowired
    BarberAttachmentStrategy barberAttachmentStrategy;

    @Autowired
    CommonStrategy commonStrategy;

    final String uuid = UUID.randomUUID().toString().replace("-", "");

    @Value("${patFile}")
    String pathFile;

    @RequestMapping(value = "/secret/barber/get/attachment", method = RequestMethod.GET)
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
        parameters.put("start", (start + 1));
        parameters.put("length", length + start);
        parameters.put("search", search);
        parameters.put("type", type);
        List<BarberAttachment> barberAttachmentList = null;

        Map<String, Object> result = new HashMap();
        result.put("draw", draw);
        result.put("search[value]", search);
        try {
            barberAttachmentList = barberAttachmentStrategy.getListDataPerPage(parameters);
            result.put("data", barberAttachmentList);
            if (barberAttachmentList.size() > 0) {
                result.put("recordsTotal", barberAttachmentList.get(0).getTotal_count());
                result.put("recordsFiltered", barberAttachmentList.get(0).getTotal_count());
            } else {
                result.put("recordsTotal", 0);
                result.put("recordsFiltered", 0);
            }

            return commonStrategy.setResultMessage(ResponseStatus.SUCCESS, null, result);
        } catch (Exception e) {
            return commonStrategy.setResultMessage(ResponseStatus.FAILED, e.getMessage(), result);
        }
    }

    @RequestMapping(value = "/secret/barber/insert/attachment", method = RequestMethod.POST)
    public Object saveTestimonial(
            @RequestParam(value = "contentId") Integer contentId,
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "type") Type type
    ) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        BarberAttachment barberAttachment = new BarberAttachment();
        barberAttachment.setContentId(contentId);
        barberAttachment.setFile(uuid+"."+extension);
        barberAttachment.setType(type);
        try {
            if(saveFile(contentId,file,barberAttachment.getFile())){
                barberAttachmentStrategy.saveData(barberAttachment);
                return commonStrategy.setResultMessage(ResponseStatus.SUCCESS, null, null);
            }else{
                return commonStrategy.setResultMessage(ResponseStatus.FAILED, "Failed To Save File Make Sure Paramter is Correct", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return commonStrategy.setResultMessage(ResponseStatus.FAILED, e.getMessage(), null);
        }
    }

    @RequestMapping(value = "/secret/barber/delete/attachment/{idAttachment}/{idContent}/{type}", method = RequestMethod.DELETE)
    public Object insertTestimonial(
            @PathVariable(value = "idAttachment") Integer idAttachment,
            @PathVariable(value = "idContent") Integer idContent,
            @PathVariable(value = "type") String type
    ) {
        Map parameter = new HashMap();
        parameter.put("type", type);
        parameter.put("idAttachment", idAttachment);
        parameter.put("idContent", idContent);
        try {
            String nameFile = barberAttachmentStrategy.getFileNameById(parameter);
            if (deleletFile(nameFile)) {
                barberAttachmentStrategy.deleteData(parameter);
                return commonStrategy.setResultMessage(ResponseStatus.SUCCESS, null, null);
            } else {
                return commonStrategy.setResultMessage(ResponseStatus.FAILED, "Failed Delete File Make Sure Paramter is Correct", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return commonStrategy.setResultMessage(ResponseStatus.FAILED, e.getMessage(), null);
        }
    }

    private Boolean saveFile(Object id,MultipartFile file,String fileName) throws IOException {
        AppUtils.getLogger(this).debug("PATH FILE : {}", pathFile);
        File tempDir = new File(pathFile);
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }

        File finalDestination = new File(tempDir + File.separator + fileName);
        try {
            file.transferTo(finalDestination);
            return Boolean.TRUE;
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    private Boolean deleletFile(String nameFile) {
        File tempDir = new File(pathFile);
        File finalDestination = new File(tempDir + File.separator + nameFile);

        if (finalDestination.delete()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
