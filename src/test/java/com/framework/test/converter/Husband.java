package com.framework.test.converter;

import cn.hutool.core.date.DateTime;

import java.time.LocalDate;
import java.util.Date;

public class Husband {

    private String wifiName;

    private DateTime marriageDate;

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public DateTime getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(DateTime marriageDate) {
        this.marriageDate = marriageDate;
    }

    @Override
    public String toString() {
        return "Husband{" +
                "wifiName='" + wifiName + '\'' +
                ", marriageDate=" + marriageDate +
                '}';
    }

}
