package com.kuku;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public class MainFrame extends JFrame {
    private  CalclLogic calclLogic;
    private  KeyboardListener keyboardListener = new KeyboardListener();
    private static final Insets insets = new Insets(5, 5, 5, 5);   // Задает отступы расположения кнопок и экрана внутри своей ячейки

    NumButtons numButtons;
    FpsButtons fpsButtons;

    public MainFrame()  {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }


        ElementFactory factory = new ElementFactory();

        CalcDisplay calcDisplay = new CalcDisplay(factory);
        numButtons = new NumButtons(factory);
        fpsButtons = new FpsButtons(factory);

        calcDisplay.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10,10));

//        ВАРИАНТ ПЕРВЫЙ
//        Кнопки не меняют размер при изменении размера окна
//
//        mainPanel.add(calcDisplay,BorderLayout.PAGE_START);
//               mainPanel.add(numButtons, BorderLayout.PAGE_END);
//
//        JPanel borderPanel = new JPanel();
//        borderPanel.add(mainPanel);
//
//        this.add(borderPanel);


//        ВАРИАНТ ВТОРОЙ
//        КНОПКИ РЕСАЙЗЯЦА
//
//        mainPanel.add(calcDisplay,BorderLayout.PAGE_START);
//        mainPanel.add(numButtons, BorderLayout.CENTER);
//        this.add(mainPanel);


//       ВАРИАНТ ТРЕТИЙ
//       Все ресайзится
//
       mainPanel.setLayout(new GridBagLayout());

       addComponent(mainPanel,calcDisplay,0,0,1,1,GridBagConstraints.PAGE_START, GridBagConstraints.HORIZONTAL);
       addComponent(mainPanel,fpsButtons,0,1,1,1,GridBagConstraints.CENTER, GridBagConstraints.BOTH);
       addComponent(mainPanel,numButtons,0,2,1,1,GridBagConstraints.CENTER, GridBagConstraints.BOTH);

       this.add(mainPanel);


       AppState appState = AppState.load();

       if (appState == null) {
           this.setSize(480,500); // TODO Надо как-то рассчитать размер окна
           this.setLocationRelativeTo(null);
       } else
       {
          this.setSize(appState.getDimension());
          this.setLocation(appState.getPoint());

       }
        //this.setSize(240,280);

        this.setResizable(true); // TODO Надо как-то рассчитать размер шрифта






        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(keyboardListener);


       // this.addKeyListener(keyboardListener);

        //this.setLocationByPlatform(true);

        UIManager.LookAndFeelInfo[] lfi = UIManager.getInstalledLookAndFeels();
        calclLogic = factory.getCalclLogic();

        keyboardListener.setCalclLogic(calclLogic);
        this.addWindowListener(new MainFrame_WindowListener());

        //YourClass.class.getResource("image/button1.png")

        ImageIcon img = new ImageIcon(this.getClass().getResource("/images/ccc_256.png")); //new ImageIcon()  //ImageIcon.class.getResource("images/ccc_ico.ico");
        this.setIconImage(img.getImage());

        System.out.println(UIManager.getInstalledLookAndFeels());
        System.out.println(this.isFocusable());




    }

    public void start() throws InterruptedException {
        setVisible(true);
//        setVisible(false);
////        Thread.sleep(500);
        numButtons.resizeButtonsFonts();
        setVisible(true);
        calclLogic.init();
        calclLogic.clearAll();



    }



    private static void addComponent(Container container, Component component, int gridx, int gridy,
                                     int gridwidth, int gridheight, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
                anchor, fill, insets, 100, 100);
        container.add(component, gbc);
    }





}
