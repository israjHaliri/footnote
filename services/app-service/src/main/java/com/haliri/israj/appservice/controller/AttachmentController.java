package com.haliri.israj.appservice.controller;

import com.haliri.israj.appcore.domain.content.Attachment;
import com.haliri.israj.appcore.utils.AppUtils;
import com.haliri.israj.appcore.constant.ContentType;
import com.haliri.israj.appcore.strategy.content.impl.AttachmentStrategy;
import com.haliri.israj.appcore.handler.impl.ResponseHandlerImpl;
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
public class AttachmentController {

    @Autowired
    AttachmentStrategy attachmentStrategy;

    @Autowired
    ResponseHandlerImpl responseHandlerImpl;

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
        List<Attachment> attachmentList = null;

        Map<String, Object> result = new HashMap();
        result.put("draw", draw);
        result.put("search[value]", search);
        try {
            attachmentList = attachmentStrategy.getListDataPerPage(parameters);
            result.put("data", attachmentList);
            if (attachmentList.size() > 0) {
                result.put("recordsTotal", attachmentList.get(0).getTotal_count());
                result.put("recordsFiltered", attachmentList.get(0).getTotal_count());
            } else {
                result.put("recordsTotal", 0);
                result.put("recordsFiltered", 0);
            }

            return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS, null, result);
        } catch (Exception e) {
            return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED, e.getMessage(), result);
        }
    }

    @RequestMapping(value = "/secret/barber/insert/attachment", method = RequestMethod.POST)
    public Object saveTestimonial(
            @RequestParam(value = "contentId") Integer contentId,
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "contentType") ContentType contentType
    ) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        Attachment attachment = new Attachment();
        attachment.setItemId(contentId);
        attachment.setFile(uuid+"."+extension);
        attachment.setContentType(contentType);
        try {
            if(saveFile(contentId,file, attachment.getFile())){
                attachmentStrategy.saveData(attachment);
                return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS, null, null);
            }else{
                return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED, "Failed To Save File Make Sure Paramter is Correct", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED, e.getMessage(), null);
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
            String nameFile = attachmentStrategy.getFileNameById(parameter);
            if (deleletFile(nameFile)) {
                attachmentStrategy.deleteData(parameter);
                return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS, null, null);
            } else {
                return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED, "Failed Delete File Make Sure Paramter is Correct", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandlerImpl.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED, e.getMessage(), null);
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
