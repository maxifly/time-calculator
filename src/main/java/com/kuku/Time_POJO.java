package com.kuku;

public class Time_POJO{
        int H = 0;
        int M = 0;
        int S = 0;
        int sign = 1;
        int F = 0; // Количество кадров
        int fps = 1; // Количество кадров в секунду

    public Time_POJO(int pi_sec, int pi_fps) {

        int sec = Math.abs(pi_sec);
        if (pi_sec < 0) {
            sign = -1;
        } else {
            sign = 1;
        }

        fps = pi_fps;
        H = sec/(3600*fps);
        M = (sec-(H*3600*fps) )/(60*fps);
        S = (sec - (H*3600*fps) - M * 60 * fps )/fps;
        F = (sec - (H*3600*fps) - M * 60 * fps - S * fps );

    }
}