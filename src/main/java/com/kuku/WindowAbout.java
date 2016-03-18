package com.kuku;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Maxim.Pantuhin on 18.03.2016.
 */
public class WindowAbout extends JDialog {

  public WindowAbout(ElementFactory factory) {
      super();
      JPanel panel = new JPanel();
      panel.setLayout(new BorderLayout(10,10));

      JLabel label = new JLabel("test about text");

      panel.add(label);
      this.add(panel);
      this.setModalityType(ModalityType.APPLICATION_MODAL);
//      this.setSize(factory.getMainFrame().getSize());
//      this.setLocation(factory.getMainFrame().getLocation());

  }

}
