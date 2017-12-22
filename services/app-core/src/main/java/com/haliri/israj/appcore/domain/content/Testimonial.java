package com.haliri.israj.appcore.domain.content;

import java.sql.Date;

/**
 * Created by israjhaliri on 11/23/17.
 */
public class Testimonial {
    private Integer idTestimonial;
    private String subject;
    private String description;
    private Integer age;
    private Date createDate;
    private Date updateDate;
    private Integer rn;
    private Integer total_count;

    public Integer getIdTestimonial() {
        return idTestimonial;
    }

    public void setIdTestimonial(Integer idTestimonial) {
        this.idTestimonial = idTestimonial;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
        return "Testimonial{" +
                "idTestimonial='" + idTestimonial + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", age=" + age +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", rn=" + rn +
                ", total_count=" + total_count +
                '}';
    }
}
