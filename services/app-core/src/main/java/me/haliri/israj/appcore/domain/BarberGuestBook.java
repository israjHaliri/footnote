package me.haliri.israj.appcore.domain;

import java.sql.Date;

/**
 * Created by israjhaliri on 11/23/17.
 */
public class BarberGuestBook {
    private String username;
    private String idGuestBook;
    private Date createDate;

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

    @Override
    public String toString() {
        return "BarberGuestBook{" +
                "username='" + username + '\'' +
                ", idGuestBook='" + idGuestBook + '\'' +
                ", date=" + createDate +
                '}';
    }
}
