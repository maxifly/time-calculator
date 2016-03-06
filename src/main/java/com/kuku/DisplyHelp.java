package com.kuku;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public class DisplyHelp extends JLabel implements DisplyElementI {
    private int fps;

    public DisplyHelp() {
        super();
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setFont(new Font(this.getName(), Font.PLAIN, this.getFont().getSize()));
        this.setText(" ");
        this.setBackground(Color.WHITE);

    }

    public void showValue(CalcState calcState) {
        String text = null;

        if (this.fps == 1) {

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
        } else {
            switch (calcState) {
                case waitH:
                    text = "H:m:s:f";
                    break;
                case waitM:
                    text = "h:M:s:f";
                    break;
                case waitS:
                    text = "h:m:S:f";
                    break;
                case waitF:
                    text = "h:m:s:F";
            }

            text = "fps: " + fps + "  " + text;
        }
            this.setText(text);
        }

        @Override
        public void startInitialProcess () {
            this.setForeground(Color.WHITE);
            this.setText("fps: 999 Hh:Mm:Ss:Ff");
        }

        @Override
        public void endInitialProcess () {
            Dimension d = this.getSize();
            this.setPreferredSize(d);
            this.setForeground(Color.BLACK);
        }

    public void setFps(int fps) {
        this.fps = fps;
    }
}
