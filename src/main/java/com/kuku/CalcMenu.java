package com.kuku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Maxim.Pantuhin on 14.03.2016.
 */
public class CalcMenu implements ActionListener{
    private JMenuBar menuBar;
    private ElementFactory factory;


    public CalcMenu() {
        super();
        this.factory = factory;
        menuBar = new JMenuBar();
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(this);
        helpMenu.add(about);
    }


    public JMenuBar getMenuBar() {
        return menuBar;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
       JMenuItem mi = (JMenuItem) e.getSource();
       switch (mi.getText()) {
           case "About":

               WindowAbout windowAbout = new WindowAbout(this.factory);
               windowAbout.setVisible(true);

               System.out.println("About");
               break;
       }
    }
}
