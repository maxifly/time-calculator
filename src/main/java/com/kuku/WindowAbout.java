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
      panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));


      addComponent(new JLabel("<html> <br><br> </html>"),panel);

      JLabel label = new JLabel("Timer calculator", SwingConstants.CENTER);
      addComponent(label,panel);
      panel.add(label);
      JLabel label1 = new JLabel(Constants.version, SwingConstants.CENTER);
      addComponent(label1,panel);


      this.add(panel);
      this.setModalityType(ModalityType.APPLICATION_MODAL);
      this.setSize(factory.getMainFrame().getSize());
      this.setLocation(factory.getMainFrame().getLocation());

  }

    private void addComponent(JComponent component, Container panel ) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(component);
    }


}
