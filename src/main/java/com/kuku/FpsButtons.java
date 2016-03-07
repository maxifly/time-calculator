package com.kuku;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Maximus on 03.03.2016.
 */
public class FpsButtons extends JPanel {
    private ElementFactory factory;
    private JComboBox fpsCombo;
    GridBagLayout gridBagLayout = new GridBagLayout();

    public FpsButtons(ElementFactory factory) {
        this.factory = factory;
        this.setLayout(gridBagLayout);

        fpsCombo = factory.getFpsCombo();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor = GridBagConstraints.LINE_START;


        this.add(new JLabel("  FPS: "),gbc);
        gbc = new GridBagConstraints();
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.anchor = GridBagConstraints.LINE_START;
        this.add(fpsCombo,gbc);

        gbc = new GridBagConstraints();
        gbc.gridx=2;
        gbc.gridy=0;
        gbc.weightx=15;
        gbc.anchor = GridBagConstraints.LINE_START;
        this.add(new JLabel(" "),gbc);

    }
}
