package com.kuku;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Maxim.Pantuhin on 10.10.2014.
 */
public class DispMouseListener implements MouseListener{

    private JLabel h;
    private JLabel m;
    private JLabel s;
    private JLabel f;
    private CalclLogic calclLogic;


    public void setH(JLabel h) {
        this.h = h;
    }

    public void setM(JLabel m) {
        this.m = m;
    }

    public void setS(JLabel s) {
        this.s = s;
    }
    public void setF(JLabel f) {
        this.f = f;
    }
    public void setCalclLogic(CalclLogic calclLogic) {
        this.calclLogic = calclLogic;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e);

        if (h.equals(e.getComponent())) {
            calclLogic.chooseTimePart(CalcResultRegionState.waitH);
        } else if (m.equals(e.getComponent())) {
            calclLogic.chooseTimePart(CalcResultRegionState.waitM);
        } else if(s.equals(e.getComponent())) {
            calclLogic.chooseTimePart(CalcResultRegionState.waitS);
        } else if(f.equals(e.getComponent()) && f.isVisible() ) {
            calclLogic.chooseTimePart(CalcResultRegionState.waitF);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
