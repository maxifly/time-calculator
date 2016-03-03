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
        this.add(fpsCombo);
// TODO Сделать расположение как в NumButtons

    }
}
