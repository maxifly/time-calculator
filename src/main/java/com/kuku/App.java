package com.kuku;

import javax.swing.*;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public class App {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, InterruptedException {
        System.out.println("kuku");
        ElementFactory factory = new ElementFactory();
        MainFrame mf = factory.getMainFrame();

        mf.start();
        System.out.println("tutu");

    }

}
