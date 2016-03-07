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

    private void showValue(int pi_sgn, int h, int m, int s, int f, int fps, OperationType operationType) {
        System.out.println("disply fps"+fps);
        String sgn = (pi_sgn < 0) ? " - " : "   ";
        if (fps > 100) {
            this.setText(String.format("%s %02d:%02d:%02d:%03d  %s", sgn, h, m, s, f, operationType));
        } else {
            this.setText(String.format("%s %02d:%02d:%02d:%02d  %s", sgn, h, m, s, f, operationType));
        }
    }

    public void showValue(Time_POJO t, OperationType operationType) {

        if (t == null) {
            this.setText(" ");
        } else {
            if (t.fps == 1) {
                showValue(t.sign, t.H, t.M, t.S, operationType);
            } else {
                showValue(t.sign, t.H, t.M, t.S, t.F, t.fps, operationType);
            }
        }

    }

    @Override
    public void startInitialProcess() {
        this.setForeground(Color.WHITE);
        this.showValue(-1, 99, 99, 99, 999,101, OperationType.add);
    }

    @Override
    public void endInitialProcess() {
        Dimension d = this.getSize();
        this.setPreferredSize(d);
        this.setForeground(Color.BLACK);
    }
}
