package com.kuku;

import javax.swing.*;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public class App {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, InterruptedException {
        System.out.println("kuku");

        MainFrame mf = new MainFrame();

        mf.start();
        System.out.println("tutu");

    }

}
