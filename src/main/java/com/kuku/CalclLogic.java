package com.kuku;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public class CalclLogic {
    ElementFactory factory;
    private int fps = 1;
    private Time_POJO registerDisp = null;
    private Time_POJO registerReg = null;

    private OperationType operation = null;
    private CalcState calcState = CalcState.waitH;

    private CalcDisplay calcDisplay;
    private NumButtons numButtons;


    public CalclLogic(ElementFactory factory) {
        this.factory = factory;
        this.numButtons = factory //TODO Get num buttons
    }

    public void setCalcDisplay(CalcDisplay calcDisplay) {
        this.calcDisplay = calcDisplay;
    }

    public void init() {

        this.calcDisplay.init();
        this.fps = this.factory.getFpsCombo().getSelectedFPS();
    }

    public void clearAll() {
        this.registerDisp = new Time_POJO(0, fps);
        this.registerReg = new Time_POJO(0, fps);
        this.operation = null;
        this.calcState = CalcState.waitH;

        calcDisplay.showDisplay(registerDisp);
        calcDisplay.showRegistrAndOper(null, null);
        calcDisplay.showStatus(calcState, fps);
    }

    public void pressNum(int num) {
        int newValue = 0;
        switch (calcState) {
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
                registerDisp.F = validate(registerDisp.F, this.fps - 1, newValue, num);
                break;
        }


        calcDisplay.showDisplay(registerDisp);


    }

    public void pressColon(boolean toBack) {
        if (!toBack) {
            switch (calcState) {
                case waitH:
                    this.calcState = CalcState.waitM;
                    break;
                case waitM:
                    this.calcState = CalcState.waitS;
                    break;
                case waitS:
                    if (this.fps != 1) {
                        this.calcState = CalcState.waitF;
                    }
                    break;
                default:
                    break;
            }
        } else {
            switch (calcState) {
                case waitF:
                    this.calcState = CalcState.waitS;
                    break;
                case waitS:
                    this.calcState = CalcState.waitM;
                    break;
                case waitM:
                    this.calcState = CalcState.waitH;
                    break;
                default:
                    break;
            }
        }

        calcDisplay.showStatus(calcState, this.fps);

    }


    public void chooseTimePart(CalcState calcState) {
        this.calcState = calcState;
        calcDisplay.showStatus(calcState, this.fps);
    }


    public void pressOperation(OperationType operation) {
        this.calcState = CalcState.waitH;
        this.registerReg = this.registerDisp;
        this.operation = operation;
        this.registerDisp = new Time_POJO(0, fps);

        calcDisplay.showDisplay(registerDisp);
        calcDisplay.showRegistrAndOper(registerReg, operation);
        calcDisplay.showStatus(calcState, this.fps);
    }

    public void pressCommand(CommandType command) {

        switch (command) {
            case clear:
                clearAll();
                break;
            case chngSign:
                registerDisp.sign = registerDisp.sign * (-1);
                calcDisplay.showDisplay(registerDisp);
                break;
            case eq:
                if (operation != null) {

                    switch (operation) {
                        case add:
                        case sub:
                            Time_POJO result = (Time_POJO) calculate(this.registerReg, this.registerDisp, operation);
                            registerDisp = result;
                            calcDisplay.showDisplay(registerDisp);
                            break;
                        case div:
                            Float resultDigit = (Float) calculate(this.registerReg, this.registerDisp, operation);
                            System.out.println("result "+ resultDigit);
                            calcDisplay.showDisplay(resultDigit);
                            // TODO надо выставить состояние ожидания для сброса калькулятора
                    }


                    calcState = CalcState.waitH;

                    operation = null;

                    calcDisplay.showRegistrAndOper(null, null);
                    calcDisplay.showStatus(calcState, this.fps);
                }
                break;
            case clearDisp:
                registerDisp = new Time_POJO(0, fps);
                calcDisplay.showDisplay(registerDisp);
                break;
            case restart:
                clearAll();
                break;

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


    private int modifyArg(Integer arg, int num) {
        if (arg == 0 && num == 0) {
            return 0;
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
        if (this.fps != newFPS) {
            this.fps = newFPS;
            clearAll();
        }
    }


}


