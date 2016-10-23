package com.example.android.smartrail;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by pooja on 22/10/16.
 */
public class TrainDetails {
    private static final String TAG = TrainDetails.class.getSimpleName();

    String name;
    String number, dtime, atime, ttime;
    String from, to;
    ArrayList<SeatClass> s;
    ArrayList<DayClass> d;
    //public void TrainDetails(String name;
      //      String number, dtime, atime, ttime;
        //                             String from, to;
          //                           ArrayList<SeatClass> s;
            //                         ArrayList<DayClass> d;)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public String getTtime() {
        return ttime;
    }

    public void setTtime(String ttime) {
        this.ttime = ttime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public ArrayList<SeatClass> getS() {
        return s;
    }

    public void setS(ArrayList<SeatClass> s) {
        this.s = s;
    }

    public ArrayList<DayClass> getD() {
        return d;
    }

    public void setD(ArrayList<DayClass> d) {
        this.d = d;
    }

    public void print(TrainDetails t)
    {

        Log.d("test", t.getName());
        Log.d("test", t.getNumber());
        Log.d("test", t.getDtime());
        Log.d("test", t.getAtime());
        Log.d("test", t.getTtime());
        Log.d("test", t.getFrom());
        Log.d("test", t.getTo());
        for(int i=0; i<t.s.size();i++){
            Log.d("test", t.s.get(i).toString());
        }

    }
}
