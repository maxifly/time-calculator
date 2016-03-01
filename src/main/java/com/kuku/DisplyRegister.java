package com.kuku;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public class DisplyRegister extends JLabel implements DisplyElementI {
    public DisplyRegister() {
        super();
        this.setHorizontalAlignment(SwingConstants.RIGHT);
        this.setFont(new Font(this.getFont().getName(), Font.BOLD, this.getFont().getSize()));
        this.setText("0");
    }

    private void showValue(int pi_sgn, int h, int m, int s, OperationType operationType) {
            String sgn = (pi_sgn < 0) ? " - " : "   ";
            this.setText(String.format("%s %02d:%02d:%02d  %s", sgn, h, m, s, operationType));
    }

    public void showValue(Time_POJO t, OperationType operationType) {

        if (t == null) {
            this.setText(" ");
        } else {
            showValue(t.sign,t.H,t.M,t.S,operationType);
        }
    }

    @Override
    public void startInitialProcess() {
        this.setForeground(Color.WHITE);
        this.showValue(-1,99,99,99,OperationType.add);
    }

    @Override
    public void endInitialProcess() {
        Dimension d = this.getSize();
        this.setPreferredSize(d);
        this.setForeground(Color.BLACK);
    }
}
