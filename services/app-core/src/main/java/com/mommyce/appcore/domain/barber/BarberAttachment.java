package com.mommyce.appcore.domain.barber;

import com.mommyce.appcore.constant.Type;

/**
 * Created by israjhaliri on 12/9/17.
 */
public class BarberAttachment {

    private Integer idAttachment;
    private Integer contentId;
    private String file;
    private Type type;
    private Integer rn;
    private Integer total_count;

    public Integer getIdAttachment() {
        return idAttachment;
    }

    public void setIdAttachment(Integer idAttachment) {
        this.idAttachment = idAttachment;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
        return "BarberAttachment{" +
                "idAttachment=" + idAttachment +
                ", contentId=" + contentId +
                ", file='" + file + '\'' +
                ", type=" + type +
                ", rn=" + rn +
                ", total_count=" + total_count +
                '}';
    }
}
