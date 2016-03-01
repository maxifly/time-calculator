package com.kuku;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public class DisplyHelp extends JLabel implements DisplyElementI{
    public DisplyHelp() {
        super();
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setFont(new Font(this.getName(), Font.PLAIN, this.getFont().getSize()));
        this.setText(" ");
        this.setBackground(Color.WHITE);

    }

    public void showValue(CalcState calcState)
    {
        String text = null;
        switch (calcState) {
            case waitH:
                text = "H:m:s";
                break;
            case waitM:
                text = "h:M:s";
                break;
            case waitS:
                text = "h:m:S";
        }

        this.setText(text);
    }

    @Override
    public void startInitialProcess() {
        this.setForeground(Color.WHITE);
        this.setText("Hh:Mm:Ss");
    }

    @Override
    public void endInitialProcess() {
        Dimension d = this.getSize();
        this.setPreferredSize(d);
        this.setForeground(Color.BLACK);
    }
}
