package com.kuku;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Maxim.Pantuhin on 18.03.2016.
 */
public class WindowAbout extends JDialog {
//TODO See http://stackoverflow.com/questions/527719/how-to-add-hyperlink-in-jlabel
    public WindowAbout(ElementFactory factory) {
        super();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        String text = "<html> <br><br> " +
                "Timer calculator<br>" +
                "Current version: <b> " +
                (new Constants()).version() +
                "</b><br>" +
                "Download last version: <a href=\"http://github.com/maxifly/time-calculator/releases\">http://github.com/maxifly/time-calculator/releases" +
                "</a></html>";

        addComponent(new JLabel(text, SwingConstants.CENTER), panel);


        this.add(panel);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setSize(factory.getMainFrame().getSize());
        this.setLocation(factory.getMainFrame().getLocation());

    }

    private void addComponent(JComponent component, Container panel) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(component);
    }


}
