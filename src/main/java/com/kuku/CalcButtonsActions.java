package com.kuku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public class CalcButtonsActions implements ActionListener {
    private CalclLogic calclLogic;

    public CalcButtonsActions(CalclLogic calclLogic) {
        this.calclLogic = calclLogic;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(e);

        String action = e.getActionCommand();

        switch (action) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                Integer num = new Integer(action);
                calclLogic.pressNum(num);
                break;
            case ":":
                calclLogic.pressColon(false);
                break;
            case "+":
                calclLogic.pressOperation(OperationType.add);
                break;
            case "-":
                calclLogic.pressOperation(OperationType.sub);
                break;
            case "/":
                calclLogic.pressOperation(OperationType.div);
                break;
            case "/dg":
                calclLogic.pressOperation(OperationType.div_on_dig);
                break;
            case "*":
                calclLogic.pressOperation(OperationType.mult_on_dig);
                break;
            case "=":
                calclLogic.pressCommand(CommandType.eq);
                break;
            case "\u00B1":
                calclLogic.pressCommand(CommandType.chngSign);
                break;
            case "C":
                calclLogic.pressCommand(CommandType.restart);
                break;
            case "CE":
                calclLogic.pressCommand(CommandType.clearDisp);
                break;
        }


    }
}
