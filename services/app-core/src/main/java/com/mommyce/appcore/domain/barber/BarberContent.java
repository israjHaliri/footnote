package com.mommyce.appcore.domain.barber;

import com.mommyce.appcore.constant.Type;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Created by israjhaliri on 11/23/17.
 */
public class BarberContent {
    private Integer idContent;
    private String title;
    private String description;
    private Date createDate;
    private Date updateDate;
    private String createBy;
    private String updateBy;
    private Type type;
    private BigDecimal price;
    private List<BarberAttachment> barberAttachmentList;

    public Integer getIdContent() {
        return idContent;
    }

    public void setIdContent(Integer idContent) {
        this.idContent = idContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<BarberAttachment> getBarberAttachmentList() {
        return barberAttachmentList;
    }

    public void setBarberAttachmentList(List<BarberAttachment> barberAttachmentList) {
        this.barberAttachmentList = barberAttachmentList;
    }

    @Override
    public String toString() {
        return "BarberContent{" +
                "idContent=" + idContent +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", barberAttachmentList=" + barberAttachmentList +
                '}';
    }
}
