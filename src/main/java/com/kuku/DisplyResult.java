package com.kuku;

import com.sun.org.apache.xpath.internal.operations.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.lang.String;
import java.util.Map;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public class DisplyResult extends JPanel implements DisplyElementI {

    JLabel h, m, s, f;
    private JLabel sgn, c1, c2, cf;
    private DispMouseListener ml = new DispMouseListener();
    private Font font;
    private Border border, borderEmpty;

    public DisplyResult(CalclLogic calclLogic) {
        super();

        GridBagLayout gb = new GridBagLayout();

        this.setLayout(gb);
        UIDefaults uid = UIManager.getDefaults();

         font = new Font(uid.getFont("TextPane.font").getFontName(), Font.BOLD, 25);
         border = BorderFactory.createLineBorder(Color.BLACK);
         borderEmpty = BorderFactory.createEmptyBorder();

        h = new JLabel();
        h.setFont(font);
        h.setFocusable(true);
        m = new JLabel();
        m.setFont(font);
        s = new JLabel();
        s.setFont(font);
        f = new JLabel();
        f.setFont(font);
        sgn = new JLabel();
        sgn.setFont(font);


        h.addMouseListener(ml);
        m.addMouseListener(ml);
        s.addMouseListener(ml);
        f.addMouseListener(ml);

        ml.setCalclLogic(calclLogic);
        ml.setH(h);
        ml.setM(m);
        ml.setS(s);
        ml.setF(f);


        c1 = new JLabel(":");
        c2 = new JLabel(":");
        cf = new JLabel(":");

        c1.setFont(font);
        c2.setFont(font);
        cf.setFont(font);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;

        this.add(sgn, gbc);
        this.add(h);
        this.add(c1);
        this.add(m);
        this.add(c2);
        this.add(s);
        this.add(cf);
        this.add(f);

        // this.setPreferredSize(new Dimension(60,20));

        this.setBackground(Color.WHITE);


//        this.setHorizontalAlignment(SwingConstants.RIGHT);
//        this.setFont(new Font(this.getName(), Font.BOLD, 25));
//        this.setText("0");
    }


    private void showValue(int SGN, int H, int M, int S) {
        h.setText(String.format("%02d", H));
        m.setText(String.format("%02d", M));
        s.setText(String.format("%02d", S));
        String sgn = (SGN < 0) ? " - " : "";
        this.sgn.setText(sgn);

        Dimension d = this.getSize();
        System.out.println(d);
    }

    public void showValue(Time_POJO t) {
        showValue(t.sign, t.H, t.M, t.S);
    }

    public void setChooseRegion(CalcState state) {
        h.setBorder(borderEmpty);
        m.setBorder(borderEmpty);
        s.setBorder(borderEmpty);

        switch (state) {
            case waitH:
                h.setBorder(border);
                break;
            case waitM:
                m.setBorder(border);
                break;
            case waitS:
                s.setBorder(border);
                break;
        }
    }


    @Override
    public void startInitialProcess() {
        h.setForeground(Color.WHITE);
        m.setForeground(Color.WHITE);
        s.setForeground(Color.WHITE);
        sgn.setForeground(Color.WHITE);
        c1.setForeground(Color.WHITE);
        c2.setForeground(Color.WHITE);

        showValue(-1, 99, 99, 99);
        h.setBorder(border);
        m.setBorder(border);
        s.setBorder(border);

//        h.setFont(ul_font);
//        m.setFont(ul_font);
//        s.setFont(ul_font);

    }

    @Override
    public void endInitialProcess() {
        Dimension sgnD = sgn.getSize();
        sgn.setPreferredSize(sgnD);
        Dimension d = this.getSize();
        this.setPreferredSize(d);

        h.setFont(font);
        m.setFont(font);
        s.setFont(font);

        h.setBorder(borderEmpty);
        m.setBorder(borderEmpty);
        s.setBorder(borderEmpty);

        h.setForeground(Color.BLACK);
        m.setForeground(Color.BLACK);
        s.setForeground(Color.BLACK);
        sgn.setForeground(Color.BLACK);
        c1.setForeground(Color.BLACK);
        c2.setForeground(Color.BLACK);

    }
}
