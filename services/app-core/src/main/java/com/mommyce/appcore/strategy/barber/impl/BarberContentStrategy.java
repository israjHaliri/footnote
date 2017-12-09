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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by israjhaliri on 8/28/17.
 */
@Repository
public class BarberContentStrategy {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GetDataStrategy getDataStrategy;

    private SaveOrUpdateDataStrategy<BarberContent> saveOrUpdateDataStrategy;

    private DeleteDataStrategy<Integer> deleteDataStrategy;

    @Autowired
    CommonStrategy commonStrategy;

    public List<BarberContent> getListData(String type) {
        getDataStrategy = (parameters) -> {
            List<BarberContent> barberContentList = new ArrayList<>();

            String sql = "SELECT id_content, title, description, create_date, update_date, create_by, update_by, type, price\n" +
                    "FROM barber.content where type = ? limit 5";

            barberContentList = jdbcTemplate.query(sql, new Object[]{parameters}, new BeanPropertyRowMapper(BarberContent.class));
            AppUtils.getLogger(this).debug("CONTENT LOG GET DATA: {}", barberContentList.toString());
            return barberContentList;
        };
        return (List<BarberContent>) getDataStrategy.process(type);
    }

    public List<BarberContent> getListDataPerPage(Object allparameters) {
        getDataStrategy = (parameters) -> {
            Map<String, Object> param = (Map<String, Object>) parameters;
            List<BarberContent> barberContentList = new ArrayList<>();

            String sql = "SELECT t.*" +
                    "FROM\n" +
                    "   (SELECT row_number() over() as rn,t.*\n" +
                    "       FROM\n" +
                    "           (SELECT t.*\n" +
                    "               FROM\n" +
                    "                   (SELECT COUNT(id_content) OVER() TOTAL_COUNT,id_content,title,description,create_date,update_date,create_by,update_by,type,price\n" +
                    "                    FROM barber.content \n" +
                    "                    WHERE title LIKE  '%" + param.get("search") + "%' \n" +
                    "                    ORDER BY barber.content.id_content DESC) " +
                    "             t)\n" +
                    "     t)\n" +
                    "t\n" +
                    "WHERE t.rn BETWEEN " + param.get("start") + "::integer AND " + param.get("length") + "::integer";


            barberContentList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BarberContent.class));
            AppUtils.getLogger(this).debug("GET CONTENT LOG : {}", barberContentList.toString());
            return barberContentList;
        };
        return (List<BarberContent>) getDataStrategy.process(allparameters);
    }


    @Transactional
    public void saveData(BarberContent barberContent) {
        saveOrUpdateDataStrategy = (BarberContent parameters) -> {
            String sql = "INSERT INTO barber.content(\n" +
                    "title, description, create_date, create_by, type, price)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, parameters.getTitle(), parameters.getDescription(),
                    parameters.getCreateDate(),parameters.getCreateBy(),
                    parameters.getType().name(),parameters.getPrice());
        };
        saveOrUpdateDataStrategy.process(barberContent);
    }

    @Transactional
    public void updateData(BarberContent barberContent) {
        saveOrUpdateDataStrategy = (BarberContent parameters) -> {
            String sql = "UPDATE barber.content\n" +
                    "SET title=?, description=?, update_date=?, update_by=?, type=?, price=?\n" +
                    "WHERE id_content=?";
            jdbcTemplate.update(sql, parameters.getTitle(), parameters.getDescription(),
                    parameters.getUpdateDate(),parameters.getUpdateBy(),
                    parameters.getType().name(),parameters.getPrice(),barberContent.getIdContent());
        };
        saveOrUpdateDataStrategy.process(barberContent);
    }

    @Transactional
    public void deleteData(Integer id) {
        deleteDataStrategy = (parameters) -> {
            String sql = "DELETE FROM barber.content\n" +
                    "WHERE id_content=?";
            jdbcTemplate.update(sql, parameters);
        };
        deleteDataStrategy.process(id);
    }
}
