package com.kuku;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Maximus on 06.03.2016.
 */
public class FpsCombo_ActionListener implements ActionListener {
    CalclLogic calclLogic;

    public FpsCombo_ActionListener(CalclLogic calclLogic){
        this.calclLogic = calclLogic;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       FpsComboBox fpsCombo = (FpsComboBox) e.getSource();
       Integer fps = fpsCombo.getSelectedFPS();

       System.out.println("Action fps" + fps);
       calclLogic.changeFPS(fps);
    }
}
