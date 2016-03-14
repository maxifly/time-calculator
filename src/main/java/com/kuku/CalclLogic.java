package com.kuku;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public class CalclLogic {
    ElementFactory factory;
    //private int fps = 1;
    private Time_POJO registerDisp = null;
    private Time_POJO registerReg = null;
    private Float registerDisp_Float = 0f;

    private OperationType operation = null;
    private CalcState calcState = new CalcState();

    private CalcDisplay calcDisplay;
    private NumButtons numButtons;
    private ButtonsType[] clearButtons = {ButtonsType.cmd_C, ButtonsType.cmd_CE};

    public CalclLogic(ElementFactory factory) {
        this.factory = factory;
    }

    public void setCalcDisplay(CalcDisplay calcDisplay) {
        this.calcDisplay = calcDisplay;
    }

    public void init() {

        this.calcDisplay.init();
        this.calcState.setFps(this.factory.getFpsCombo().getSelectedFPS());
    }

    public void clearAll() {
        this.registerDisp = new Time_POJO(0, calcState.getFps());
        this.registerReg = new Time_POJO(0, calcState.getFps());
        this.registerDisp_Float = 0f;
        this.operation = null;
        this.calcState.setResultRegionState(CalcResultRegionState.waitH);
        this.calcState.setWaitReset(false);

        numButtons.enableButtons();

        calcDisplay.showDisplay(registerDisp);
        calcDisplay.showRegistrAndOper(null, null);
        calcDisplay.showStatus(calcState);
    }

    public void pressNum(int num) {
        if (calcState.isWaitReset()) {
            return;
        }

        if (calcState.isWaitDigit()) {
            registerDisp_Float = modifyArg(registerDisp_Float, num);
            calcDisplay.showDisplay(registerDisp_Float);
        } else {
            int newValue = 0;
            switch (calcState.getResultRegionState()) {
                case waitH:
                    newValue = modifyArg(registerDisp.H, num);
                    registerDisp.H = validate(registerDisp.H, 23, newValue, num);
                    break;
                case waitM:
                    newValue = modifyArg(registerDisp.M, num);
                    registerDisp.M = validate(registerDisp.M, 59, newValue, num);
                    break;
                case waitS:
                    newValue = modifyArg(registerDisp.S, num);
                    registerDisp.S = validate(registerDisp.S, 59, newValue, num);
                    break;
                case waitF:
                    newValue = modifyArg(registerDisp.F, num);
                    registerDisp.F = validate(registerDisp.F, this.calcState.getFps() - 1, newValue, num);
                    break;
            }
            calcDisplay.showDisplay(registerDisp);
        }


    }

    public void pressColon(boolean toBack) {
        if (calcState.isWaitReset() || calcState.isWaitDigit()) {
            return;
        }
        if (!toBack) {
            switch (calcState.getResultRegionState()) {
                case waitH:
                    this.calcState.setResultRegionState(CalcResultRegionState.waitM);
                    break;
                case waitM:
                    this.calcState.setResultRegionState(CalcResultRegionState.waitS);
                    break;
                case waitS:
                    if (this.calcState.getFps() != 1) {
                        this.calcState.setResultRegionState(CalcResultRegionState.waitF);
                    }
                    break;
                default:
                    break;
            }
        } else {
            switch (calcState.getResultRegionState()) {
                case waitF:
                    this.calcState.setResultRegionState(CalcResultRegionState.waitS);
                    break;
                case waitS:
                    this.calcState.setResultRegionState(CalcResultRegionState.waitM);
                    break;
                case waitM:
                    this.calcState.setResultRegionState(CalcResultRegionState.waitH);
                    break;
                default:
                    break;
            }
        }

        calcDisplay.showStatus(calcState);

    }


    public void chooseTimePart(CalcResultRegionState calcState) {
        this.calcState.setResultRegionState(calcState);
        calcDisplay.showStatus(this.calcState);
    }


    public void pressOperation(OperationType operation) {
        if (this.calcState.isWaitReset()) {
            return;
        }

        this.calcState.setResultRegionState(CalcResultRegionState.waitH);
        this.registerReg = this.registerDisp; // Так можно делать потому, что известно, что первым аргументом всегда набирается время
        this.operation = operation;

        switch (operation) {
            case div_on_dig:
            case mult_on_dig:
                this.calcState.setWaitDigit(true);
                registerDisp_Float = 0f;
                calcDisplay.showDisplay(registerDisp_Float);
                break;
            default:
                this.registerDisp = new Time_POJO(0, calcState.getFps());
                calcDisplay.showDisplay(registerDisp);
        }

        calcDisplay.showRegistrAndOper(registerReg, operation);
        calcDisplay.showStatus(calcState);
    }

    public void pressCommand(CommandType command) {
        switch (command) {
            case clear:
                clearAll();
                break;
            case clearDisp:
                calcState.setWaitReset(false);
                registerDisp = new Time_POJO(0, calcState.getFps());
                calcDisplay.showDisplay(registerDisp);
                numButtons.enableButtons();
                break;
            case restart:
                clearAll();
                break;
            default:
                break;
        }

        if (!this.calcState.isWaitReset()) {
            switch (command) {
                case chngSign:
                    registerDisp.sign = registerDisp.sign * (-1);
                    calcDisplay.showDisplay(registerDisp);
                    break;
                case eq:
                    if (operation != null) {

                        switch (operation) {
                            case add:
                            case sub:
                                registerDisp = (Time_POJO) calculate(this.registerReg, this.registerDisp, operation);
                                calcDisplay.showDisplay(registerDisp);
                                break;
                            case div:
                                Float resultDigit = (Float) calculate(this.registerReg, this.registerDisp, operation);
                                System.out.println("result " + resultDigit);
                                calcDisplay.showDisplay(resultDigit);

                                numButtons.disableButtons(clearButtons);
                                calcState.setWaitReset(true);
                                break;
                            case mult_on_dig:
                            case div_on_dig:
                                registerDisp = (Time_POJO) calculate(this.registerReg, this.registerDisp_Float, operation);
                                calcDisplay.showDisplay(registerDisp);
                                calcState.setWaitDigit(false);
                        }
                        calcState.setResultRegionState(CalcResultRegionState.waitH);
                        operation = null;
                        calcDisplay.showRegistrAndOper(null, null);
                        calcDisplay.showStatus(calcState);
                    }
                    break;
            }
        }
    }


    /**
     * @return new Time_POJO
     */
    private Object calculate(Time_POJO argA, Time_POJO argB, OperationType operation) {
        int frames_argA = argA.getFrames();
        int frames_argB = argB.getFrames();
        int result;

        switch (operation) {
            case add:
                result = frames_argA + frames_argB;
                return new Time_POJO(result, argA.fps);
            case sub:
                result = frames_argA - frames_argB;
                return new Time_POJO(result, argA.fps);
            case div:
                return new Float(new Float(frames_argA) / new Float(frames_argB));
            default:
                result = 0;
                return new Time_POJO(result, argA.fps);
        }
    }

    private Object calculate(Time_POJO argA, Float argB, OperationType operation) {
        int frames_argA = argA.getFrames();
        int result;

        switch (operation) {
            case div_on_dig:
                result = Math.round((float) frames_argA / argB);
                return new Time_POJO(result, argA.fps);
            case mult_on_dig:
                result = Math.round((float) frames_argA * argB);
                return new Time_POJO(result, argA.fps);
            default:
                result = 0;
                return new Time_POJO(result, argA.fps);
        }
    }

    private int modifyArg(Integer arg, int num) {
        if (arg == 0 && num == 0) {
            return 0;
        }

        return (arg * 10) + num;

    }

    private float modifyArg(Float arg, int num) {
        if (arg == 0f && num == 0) {
            return 0f;
        }

        return (arg * 10) + num;

    }

    private int validate(int defaultValue, int maxValue, int value, int pressNum) {

        if (value <= maxValue) {
            return value;
        } else if (pressNum == 0) {
            return 0;
        }
        return defaultValue;
    }


    public void changeFPS(
            int newFPS) {
        if (this.calcState.getFps() != newFPS) {
            this.calcState.setFps(newFPS);
            clearAll();
        }
    }


    public void setNumButtons(NumButtons numButtons) {
        this.numButtons = numButtons;
    }
}


