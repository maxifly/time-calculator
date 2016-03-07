package com.kuku;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Maximus on 07.03.2016.
 */
public class MainFrame_WindowListener implements WindowListener {
    ElementFactory factory;

    public MainFrame_WindowListener(ElementFactory factory){
        this.factory = factory;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("closing ");
        AppState appState = new AppState();

        MainFrame mf = (MainFrame) e.getSource();
        Dimension dm = mf.getSize();
        Point point = mf.getLocation();
        FpsComboBox fpsCombo = factory.getFpsCombo();

        appState.setDimension(dm);
        appState.setPoint(point);
        appState.setFps(fpsCombo.getSelectedFPS());
        AppState.save(appState);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
