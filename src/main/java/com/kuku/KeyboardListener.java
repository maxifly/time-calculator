package com.kuku;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Maxim.Pantuhin on 14.10.2014.
 */
public class KeyboardListener implements KeyEventDispatcher {
    private CalclLogic calclLogic;
    private String strAlt = "Alt";

    public void setCalclLogic(CalclLogic calclLogic) {
        this.calclLogic = calclLogic;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        System.out.println(e);

        if (e.getID() == KeyEvent.KEY_PRESSED) {
            Character c = e.getKeyChar();


            switch (e.getKeyChar()) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    int i = Integer.valueOf(Character.toString(c));
                    System.out.println(i);
                    calclLogic.pressNum(i);
                    break;
                case ':':
                    calclLogic.pressColon(false);
                    break;
                case '+':
                    calclLogic.pressOperation(OperationType.add);
                    break;
                case '-':
                    calclLogic.pressOperation(OperationType.sub);
                    break;
                case '*':
                    calclLogic.pressOperation(OperationType.mult_on_dig);
                    break;
                case '/':
                    if (strAlt.equals(KeyEvent.getKeyModifiersText(e.getModifiers()))) {
                        calclLogic.pressOperation(OperationType.div_on_dig);
                    } else {
                        calclLogic.pressOperation(OperationType.div);
                    }
                    break;
                case '=':
                case KeyEvent.VK_ENTER:
                    calclLogic.pressCommand(CommandType.eq);
                    break;

            }

            if (e.isActionKey()) {
                int keyCode = e.getKeyCode();
                if (keyCode == e.VK_RIGHT || keyCode == e.VK_KP_RIGHT) {
                    calclLogic.pressColon(false);
                } else if (keyCode == e.VK_LEFT || keyCode == e.VK_KP_LEFT) {
                    calclLogic.pressColon(true);
                }

            }


            System.out.println(e.getKeyChar());


        }

        return false;
    }
}
