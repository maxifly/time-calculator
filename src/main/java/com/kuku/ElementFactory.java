package com.kuku;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public class ElementFactory {
    private Map<ButtonsType,JButton> allButtons = new HashMap<>();
    private DisplyResult displyResult;
    private DisplyHelp helpRow = new DisplyHelp();
    private DisplyRegister registrRow = new DisplyRegister();

    String[] fpsType = { "off", "25", "50", "30", "60" };

    private JComboBox fpsCombo = new JComboBox(fpsType);




    private CalclLogic calclLogic;

    private CalcButtonsActions calcButtonsActions = new CalcButtonsActions(calclLogic);

    public ElementFactory() {
        super();
        this.calclLogic = new CalclLogic(this);
        this.calcButtonsActions = new CalcButtonsActions(calclLogic);
        this.displyResult =  new DisplyResult(calclLogic);
        calclLogic.setCalcDisplay(new CalcDisplay(this));



    }

    public Map<ButtonsType, JButton> getAllButtons() {
        return allButtons;
    }

    public JButton getButton(ButtonsType buttonsType) {

       JButton btn = allButtons.get(buttonsType);
        if (btn == null) {
            btn = createButton(buttonsType);
            allButtons.put(buttonsType, btn);
        }

        return btn;
    }

    public DisplyResult getDisplayResult(){
        return this.displyResult;
    }

    public DisplyHelp getHelpRow() {
        return helpRow;
    }

    public DisplyRegister getRegistrRow() {
        return registrRow;
    }

    public CalclLogic getCalclLogic() {
        return calclLogic;
    }

    private JButton createButton(ButtonsType buttonsType) {

        JButton jb = new JButton();
        jb.setText(buttonsType.getText());
        jb.addActionListener(calcButtonsActions);

        switch (buttonsType) {
            case cmd_C:
            case cmd_CE:
                jb.setForeground(Color.RED);
        }

        return jb;

    }


    public JComboBox getFpsCombo() {
        return fpsCombo;
    }
}
