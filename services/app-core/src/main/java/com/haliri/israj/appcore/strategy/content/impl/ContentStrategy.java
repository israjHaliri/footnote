package com.haliri.israj.appcore.strategy.content.impl;

import com.haliri.israj.appcore.domain.content.Item;
import com.haliri.israj.appcore.handler.impl.ResponseHandlerImpl;
import com.haliri.israj.appcore.utils.AppUtils;
import com.haliri.israj.appcore.strategy.content.DeleteDataStrategy;
import com.haliri.israj.appcore.strategy.content.GetDataStrategy;
import com.haliri.israj.appcore.strategy.content.SaveOrUpdateDataStrategy;
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
public class ContentStrategy {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GetDataStrategy getDataStrategy;

    private SaveOrUpdateDataStrategy<Item> saveOrUpdateDataStrategy;

    private DeleteDataStrategy<Integer> deleteDataStrategy;

    @Autowired
    ResponseHandlerImpl responseHandlerImpl;

    public List<Item> getListData(String type) {
        getDataStrategy = (parameters) -> {
            List<Item> itemList = new ArrayList<>();

            String sql = "SELECT id_content, title, description, create_date, update_date, create_by, update_by, type, price\n" +
                    "FROM barber.content where type = ? limit 5";

            itemList = jdbcTemplate.query(sql, new Object[]{parameters}, new BeanPropertyRowMapper(Item.class));
            AppUtils.getLogger(this).debug("CONTENT LOG GET DATA: {}", itemList.toString());
            return itemList;
        };
        return (List<Item>) getDataStrategy.process(type);
    }

    public List<Item> getListDataById(Map param) {
        getDataStrategy = (parameters) -> {
            Map<String,Object> objParam = (Map<String, Object>) parameters;
            List<Item> itemList = new ArrayList<>();

            String sql = "SELECT id_content, title, description, create_date, update_date, create_by, update_by, type, price\n" +
                    "FROM barber.content where type = ? and id_content = ? limit 5";

            itemList = jdbcTemplate.query(sql, new Object[]{objParam.get("type"),objParam.get("idContent")}, new BeanPropertyRowMapper(Item.class));
            AppUtils.getLogger(this).debug("CONTENT LOG GET DATA: {}", itemList.toString());
            return itemList;
        };
        return (List<Item>) getDataStrategy.process(param);
    }

    public List<Item> getListDataPerPage(Object allparameters) {
        getDataStrategy = (parameters) -> {
            Map<String, Object> param = (Map<String, Object>) parameters;
            List<Item> itemList = new ArrayList<>();

            String sql = "SELECT t.*" +
                    "FROM\n" +
                    "   (SELECT row_number() over() as rn,t.*\n" +
                    "       FROM\n" +
                    "           (SELECT t.*\n" +
                    "               FROM\n" +
                    "                   (SELECT COUNT(id_content) OVER() TOTAL_COUNT,id_content,title,description,create_date,update_date,create_by,update_by,type,price\n" +
                    "                    FROM barber.content \n" +
                    "                    WHERE title LIKE  '%" + param.get("search") + "%' and type = '"+param.get("type")+"' \n" +
                    "                    ORDER BY barber.content.id_content DESC) " +
                    "             t)\n" +
                    "     t)\n" +
                    "t\n" +
                    "WHERE t.rn BETWEEN " + param.get("start") + "::integer AND " + param.get("length") + "::integer";


            itemList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Item.class));
            AppUtils.getLogger(this).debug("CONTENT LOG GET DATA: {}", itemList.toString());
            return itemList;
        };
        return (List<Item>) getDataStrategy.process(allparameters);
    }


    @Transactional
    public void saveData(Item Item) {
        saveOrUpdateDataStrategy = (Item parameters) -> {
            String sql = "INSERT INTO barber.content(\n" +
                    "title, description, create_date, create_by, type, price)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, parameters.getTitle(), parameters.getDescription(),
                    parameters.getCreateDate(),parameters.getCreateBy(),
                    parameters.getContentType().name(),parameters.getInfomation());
        };
        saveOrUpdateDataStrategy.process(Item);
    }

    @Transactional
    public void updateData(Item Item) {
        saveOrUpdateDataStrategy = (Item parameters) -> {
            String sql = "UPDATE barber.content\n" +
                    "SET title=?, description=?, update_date=?, update_by=?, type=?, price=?\n" +
                    "WHERE id_content=?";
            jdbcTemplate.update(sql, parameters.getTitle(), parameters.getDescription(),
                    parameters.getUpdateDate(),parameters.getUpdateBy(),
                    parameters.getContentType().name(),parameters.getInfomation(), Item.getIdContent());
        };
        saveOrUpdateDataStrategy.process(Item);
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
