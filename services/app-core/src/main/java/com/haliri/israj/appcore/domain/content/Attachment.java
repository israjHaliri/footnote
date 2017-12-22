package com.haliri.israj.appcore.domain.content;

import com.haliri.israj.appcore.constant.ContentType;

/**
 * Created by israjhaliri on 12/9/17.
 */
public class Attachment {

    private Integer idAttachment;
    private Integer itemId;
    private String file;
    private ContentType contentType;
    private Integer rn;
    private Integer total_count;

    public Integer getIdAttachment() {
        return idAttachment;
    }

    public void setIdAttachment(Integer idAttachment) {
        this.idAttachment = idAttachment;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
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

    @Override
    public String toString() {
        return "Attachment{" +
                "idAttachment=" + idAttachment +
                ", itemId=" + itemId +
                ", file='" + file + '\'' +
                ", contentType=" + contentType +
                ", rn=" + rn +
                ", total_count=" + total_count +
                '}';
    }
}
