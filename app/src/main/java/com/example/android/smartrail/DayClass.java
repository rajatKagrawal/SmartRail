package com.example.android.smartrail;

/**
 * Created by pooja on 22/10/16.
 */
public class DayClass {
    String daycode;
    String status;
    int l;
    public DayClass(int l)
    {
        this.l= l;
    }

    public DayClass(String a,String b)
    {
        this.daycode=a;
        this.status=b;
    }
    public String getDaycode() {
        return daycode;
    }

    public void setDaycode(String daycode) {
        this.daycode = daycode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
