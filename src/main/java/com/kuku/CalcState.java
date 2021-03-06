package com.kuku;

/**
 * Created by Maximus on 11.03.2016.
 *
 * CalculatorState
 */
public class CalcState {
    private CalcResultRegionState resultRegionState = CalcResultRegionState.waitH;
    private boolean waitReset = false;
    private boolean waitDigit = false;
    private int fps;


    public CalcResultRegionState getResultRegionState() {
        return resultRegionState;
    }

    public void setResultRegionState(CalcResultRegionState resultRegionState) {
        this.resultRegionState = resultRegionState;
    }

    public boolean isWaitReset() {
        return waitReset;
    }

    public void setWaitReset(boolean waitReset) {
        this.waitReset = waitReset;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public boolean isWaitDigit() {
        return waitDigit;
    }

    public void setWaitDigit(boolean waitDigit) {
        this.waitDigit = waitDigit;
    }
}
