package com.mommyce.appcore.barber;

import com.mommyce.appcore.constant.Type;
import com.mommyce.appcore.domain.barber.BarberAttachment;
import com.mommyce.appcore.domain.barber.BarberContent;
import com.mommyce.appcore.domain.barber.BarberTestimonial;
import com.mommyce.appcore.strategy.barber.impl.BarberAttachmentStrategy;
import com.mommyce.appcore.strategy.barber.impl.BarberContentStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BarberContentStartegyTests {

    @Autowired
    BarberContentStrategy barberContentStrategy;

    @Autowired
    BarberAttachmentStrategy barberAttachmentStrategy;

    @Test
    public void getListData() throws SQLException {
        barberContentStrategy.getListData(Type.PRICE.name());
    }

    @Test
    public void getListDataPerPage() throws SQLException {
        Map<String,Object> param = new HashMap<>();
        param.put("start",1);
        param.put("length",10);
        param.put("search","");
        barberContentStrategy.getListDataPerPage(param);
    }

    @Test
    public void getListDataPerPageAttachment() throws SQLException {
        Map<String,Object> param = new HashMap<>();
        param.put("start",1);
        param.put("length",10);
        param.put("search","");
        param.put("type",Type.PRICE.name());
        barberAttachmentStrategy.getListDataPerPage(param);
    }

    @Test
    public void saveData() throws SQLException {
        BarberContent barberContent = new BarberContent();
        barberContent.setTitle("Test insert");
        barberContent.setDescription("Testing insert");
        barberContent.setCreateDate(new Date(2017-10-1));
        barberContent.setUpdateDate(new Date(2017-10-1));
        barberContent.setCreateBy("admin.barber@gmail.com");
        barberContent.setUpdateBy("admin.barber@gmail.com");
        barberContent.setType(Type.PRICE);
        barberContent.setPrice(new BigDecimal(100000));
        barberContent.setIdContent(null);
        barberContentStrategy.saveData(barberContent);
    }

    @Test
    public void updateData() throws SQLException {
        BarberContent barberContent = new BarberContent();
        barberContent.setTitle("Test update");
        barberContent.setDescription("Testing update");
        barberContent.setCreateDate(new Date(2017-10-2));
        barberContent.setUpdateDate(new Date(2017-10-2));
        barberContent.setCreateBy("admin.barber@gmail.com");
        barberContent.setUpdateBy("admin.barber@gmail.com");
        barberContent.setType(Type.PRICE);
        barberContent.setPrice(new BigDecimal(150000));
        barberContent.setIdContent(2);
        barberContentStrategy.updateData(barberContent);
    }

    @Test
    public void saveAttachment() throws SQLException {
        BarberAttachment barberAttachment = new BarberAttachment();
        barberAttachment.setIdAttachment(1);
        barberAttachment.setContentId(2);
        barberAttachment.setFile("file.txt");
        barberAttachment.setType(Type.PRICE);
        barberAttachmentStrategy.saveAttachment(barberAttachment);
    }

    @Test
    public void deleteData() throws SQLException {
        barberContentStrategy.deleteData(1);
    }
}
