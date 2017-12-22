package com.haliri.israj.appcore.strategy.content.impl;

import com.haliri.israj.appcore.domain.content.Attachment;
import com.haliri.israj.appcore.strategy.content.DeleteDataStrategy;
import com.haliri.israj.appcore.strategy.content.GetDataStrategy;
import com.haliri.israj.appcore.strategy.content.SaveOrUpdateDataStrategy;
import com.haliri.israj.appcore.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by israjhaliri on 8/28/17.
 */
@Repository
public class AttachmentStrategy {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SaveOrUpdateDataStrategy<Attachment> saveOrUpdateDataStrategy;

    private GetDataStrategy getDataStrategy;

    private DeleteDataStrategy<Map> deleteDataStrategy;

    public List<Attachment> getListDataPerPage(Object allparameters) {
        getDataStrategy = (parameters) -> {
            Map<String, Object> param = (Map<String, Object>) parameters;
            List<Attachment> attachmentList = new ArrayList<>();

            String sql = "SELECT t.*\n" +
                    "   FROM\n" +
                    "  (SELECT row_number() over() as rn,t.*\n" +
                    "      FROM\n" +
                    "          (SELECT t.*\n" +
                    "              FROM\n" +
                    "                  (SELECT COUNT(id_attachment) OVER() TOTAL_COUNT,id_attachment,content_id,file,type\n" +
                    "                  FROM barber.attachment \n" +
                    "                   WHERE file LIKE  '%%' and type = '"+param.get("type")+"'\n" +
                    "                   ORDER BY barber.attachment.id_attachment DESC)\n" +
                    "            t)\n" +
                    "    t)\n" +
                    "t\n" +
                    "WHERE t.rn BETWEEN "+param.get("start")+"::integer AND "+param.get("length")+"::integer";


            attachmentList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Attachment.class));
            AppUtils.getLogger(this).debug("GET ATTACHMENT LOG : {}", attachmentList.toString());
            return attachmentList;
        };
        return (List<Attachment>) getDataStrategy.process(allparameters);
    }

    public String getFileNameById(Map param) {
        getDataStrategy = (parameters) -> {
            Map<String,Object> objParam = (Map<String, Object>) parameters;
            List<Attachment> attachmentList = new ArrayList<>();

            String sql = "SELECT file \n" +
                    "FROM barber.attachment where id_attachment = ? and content_id = ? and type = ?;";

            Attachment attachment = (Attachment) jdbcTemplate.queryForObject(sql, new Object[]{objParam.get("idAttachment"),objParam.get("idContent"),objParam.get("type")}, new BeanPropertyRowMapper(Attachment.class));
            AppUtils.getLogger(this).debug("CONTENT LOG GET DATA: {}", attachmentList.toString());
            return attachment.getFile().toString();
        };
        return (String) getDataStrategy.process(param);
    }

    @Transactional
    public void saveData(Attachment attachment) {
        saveOrUpdateDataStrategy = (Attachment parameters) -> {
            String sql = "INSERT INTO barber.attachment(\n" +
                    "content_id, file, type)\n" +
                    "VALUES ( ?, ?, ?)";
            jdbcTemplate.update(sql, parameters.getItemId(), parameters.getFile(),parameters.getContentType().name());
        };
        saveOrUpdateDataStrategy.process(attachment);
    }

    @Transactional
    public void updateData(Attachment attachment) {
        saveOrUpdateDataStrategy = (Attachment parameters) -> {
            String sql = "UPDATE barber.attachment\n" +
                    "SET file=?\n" +
                    "WHERE id_attachment=? and content_id=? and type=?";
            jdbcTemplate.update(sql, parameters.getFile(),parameters.getIdAttachment(),parameters.getItemId(),parameters.getContentType().name());
        };
        saveOrUpdateDataStrategy.process(attachment);
    }

    @Transactional
    public void deleteData(Map param) {
        deleteDataStrategy = (parameters) -> {
            Map objParam = parameters;
            String sql = "DELETE FROM barber.attachment\n" +
                    "WHERE id_attachment=? and type =? and content_id =?";
            jdbcTemplate.update(sql, parameters.get("idAttachment"),objParam.get("type"),objParam.get("idContent"));
        };
        deleteDataStrategy.process(param);
    }


}
