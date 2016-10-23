package com.example.android.smartrail;

import android.util.Log;

/**
 * Created by pooja on 22/10/16.
 */
public class SeatClass {
    private static final String TAG = MainActivity.class.getSimpleName();
    String code;
    String avai;

    public SeatClass(String a, String b) {
        this.code= a;
        this.avai= b;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAvai() {
        return avai;
    }

    public void setAvai(String avai) {
        this.avai = avai;
    }

    public void print(SeatClass s){
        Log.d("test", s.getAvai()+"dvfdvfd"+s.getCode());

    }
}
