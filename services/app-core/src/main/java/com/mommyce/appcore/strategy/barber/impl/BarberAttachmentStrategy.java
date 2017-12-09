package com.mommyce.appcore.strategy.barber.impl;

import com.mommyce.appcore.constant.Type;
import com.mommyce.appcore.domain.barber.BarberAttachment;
import com.mommyce.appcore.domain.barber.BarberContent;
import com.mommyce.appcore.strategy.barber.DeleteDataStrategy;
import com.mommyce.appcore.strategy.barber.GetDataStrategy;
import com.mommyce.appcore.strategy.barber.SaveOrUpdateDataStrategy;
import com.mommyce.appcore.strategy.common.impl.CommonStrategy;
import com.mommyce.appcore.utils.AppUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
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
public class BarberAttachmentStrategy {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SaveOrUpdateDataStrategy<BarberAttachment> saveOrUpdateDataStrategy;

    private GetDataStrategy getDataStrategy;

    @Transactional
    public void saveAttachment(BarberAttachment barberAttachment) {
        saveOrUpdateDataStrategy = (BarberAttachment parameters) -> {
            String sql = "INSERT INTO barber.attachment(\n" +
                    "content_id, file, type)\n" +
                    "VALUES ( ?, ?, ?)";
            jdbcTemplate.update(sql, parameters.getContentId(), parameters.getFile(),parameters.getType().name());
        };
        saveOrUpdateDataStrategy.process(barberAttachment);
    }

    public List<BarberAttachment> getListDataPerPage(Object allparameters) {
        getDataStrategy = (parameters) -> {
            Map<String, Object> param = (Map<String, Object>) parameters;
            List<BarberAttachment> barberAttachmentList = new ArrayList<>();

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


            barberAttachmentList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BarberAttachment.class));
            AppUtils.getLogger(this).debug("GET CONTENT LOG : {}", barberAttachmentList.toString());
            return barberAttachmentList;
        };
        return (List<BarberAttachment>) getDataStrategy.process(allparameters);
    }
}
