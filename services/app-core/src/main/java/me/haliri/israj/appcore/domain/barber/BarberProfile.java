package me.haliri.israj.appcore.domain.barber;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by israjhaliri on 11/21/17.
 */
public class BarberProfile {
    private String address;
    private String phone;
    private String email;
    private Float lat;
    private Float lon;
    private Timestamp createDate;
    private Timestamp updateDate;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "BarberProfile{" +
                "address='" + address + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", creataDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
