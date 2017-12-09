package com.mommyce.appcore.domain.barber;

import java.sql.Date;

/**
 * Created by israjhaliri on 11/23/17.
 */
public class BarberGuestBook {
    private String username;
    private String idGuestBook;
    private Date createDate;
    private Integer rn;
    private Integer total_count;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdGuestBook() {
        return idGuestBook;
    }

    public void setIdGuestBook(String idGuestBook) {
        this.idGuestBook = idGuestBook;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
        return "BarberGuestBook{" +
                "username='" + username + '\'' +
                ", idGuestBook='" + idGuestBook + '\'' +
                ", createDate=" + createDate +
                ", rn=" + rn +
                ", total_count=" + total_count +
                '}';
    }
}
