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


    public CalclLogic(ElementFactory factory) {
        this.factory = factory;
    }

    public void setCalcDisplay(CalcDisplay calcDisplay) {
        this.calcDisplay = calcDisplay;
    }

    public void init() {

       this.calcDisplay.init();
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
        }


        calcDisplay.showDisplay(registerDisp);


    }

    public void pressColon(boolean toBack) {
        if (! toBack) {
            switch (calcState) {
                case waitH:
                    this.calcState = CalcState.waitM;
                    break;
                case waitM:
                    this.calcState = CalcState.waitS;
                    break;
                default:
                    break;
            }
        } else {
            switch (calcState) {
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
                    int result = calculate();
                    System.out.println(result);
                    registerDisp = new Time_POJO(result, fps);
                    calcState = CalcState.waitH;
                    operation = null;


                    calcDisplay.showDisplay(registerDisp);
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


    private int calculate() {
        int argA = registerReg.getFrames();
        int argB = registerDisp.getFrames();

        switch (operation) { //TODO Может перевести на рассчет с двумя входящими параметрами типа Time_POJO
            case add:
                return argA + argB;
            case sub:
                return argA - argB;
            default:
                return 0;
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

