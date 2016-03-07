package com.kuku;

import java.io.Serializable;

/**
 * Created by Maximus on 07.03.2016.
 */
public class AppState implements Serializable{
    private String fps;
    private int DimensionH;
    private int DimensionW;


    public void setFps(String fps) {
        this.fps = fps;
    }

    public void setDimensionH(int dimensionH) {
        DimensionH = dimensionH;
    }

    public void setDimensionW(int dimensionW) {
        DimensionW = dimensionW;
    }
}
