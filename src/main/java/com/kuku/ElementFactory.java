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


    private FpsComboBox fpsCombo = new FpsComboBox();
    private FpsCombo_ActionListener fpsCombo_actionListener;


    private CalcDisplay calcDisplay;
    private CalclLogic calclLogic;

    private CalcButtonsActions calcButtonsActions = new CalcButtonsActions(calclLogic);
    private NumButtons numButtons;

    private MainFrame mainFrame;

    public ElementFactory() {
        super();
        this.calclLogic = new CalclLogic(this);
        this.calcButtonsActions = new CalcButtonsActions(calclLogic);
        this.displyResult =  new DisplyResult(calclLogic);
        this.calcDisplay = new CalcDisplay(this);
        calclLogic.setCalcDisplay(this.calcDisplay);
        this.fpsCombo_actionListener = new FpsCombo_ActionListener(calclLogic);
        this.fpsCombo.addActionListener(this.fpsCombo_actionListener);
        this.numButtons = new NumButtons(this);
        this.calclLogic.setNumButtons(this.numButtons);

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


    public FpsComboBox getFpsCombo() {
        return fpsCombo;
    }

    public CalcDisplay getCalcDisplay() {
        return calcDisplay;
    }

    public NumButtons getNumButtons() {
        return numButtons;
    }

    public MainFrame getMainFrame() {
        if (mainFrame == null) {
            this.mainFrame = new MainFrame(this);
        }
        return mainFrame;
    }
}
