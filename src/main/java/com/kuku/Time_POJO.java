package com.kuku;

public class Time_POJO{
        int H = 0;
        int M = 0;
        int S = 0;
        int sign = 1;

    public Time_POJO(int pi_sec) {

        int sec = Math.abs(pi_sec);
        if (pi_sec < 0) {
            sign = -1;
        } else {
            sign = 1;
        }

        H = sec/3600;
        M = (sec-(H*3600) )/60;
        S = (sec - (H*3600) - M * 60 );


    }
}