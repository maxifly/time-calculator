package com.kuku;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public class DisplyResult_old extends JLabel {
    public DisplyResult_old() {
        super();
        this.setHorizontalAlignment(SwingConstants.RIGHT);
        this.setFont(new Font(this.getName(), Font.BOLD, 25));
        this.setText("0");
    }

    public void showValue(int H, int M, int S) {
        this.setText(String.format("%02d:%02d:%02d",H,M,S));
    }

    public void showValue(Time_POJO t) {
        String sgn = (t.sign < 0)?" - ":"   ";
        this.setText(String.format("%s %02d:%02d:%02d",sgn, t.H,t.M,t.S));
    }


}
