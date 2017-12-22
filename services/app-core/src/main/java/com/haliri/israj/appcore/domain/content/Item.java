package com.haliri.israj.appcore.domain.content;

import com.haliri.israj.appcore.constant.ContentType;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Created by israjhaliri on 11/23/17.
 */
public class Item {
    private Integer idContent;
    private String title;
    private String description;
    private Date createDate;
    private Date updateDate;
    private String createBy;
    private String updateBy;
    private ContentType contentType;
    private String infomation;
    private Integer rn;
    private Integer total_count;

    private List<Attachment> attachmentList;

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

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getInfomation() {
        return infomation;
    }

    public void setInfomation(String infomation) {
        this.infomation = infomation;
    }

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public Integer getRn() {
        return rn;
    }

    public void setRn(Integer rn) {
        this.rn = rn;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    @Override
    public String toString() {
        return "Item{" +
                "idContent=" + idContent +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", contentType=" + contentType +
                ", infomation=" + infomation +
                ", rn=" + rn +
                ", total_count=" + total_count +
                ", attachmentList=" + attachmentList +
                '}';
    }
}
