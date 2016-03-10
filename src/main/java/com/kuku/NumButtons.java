package com.kuku;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public class NumButtons extends JPanel {

    private ElementFactory factory;

    public NumButtons( ElementFactory factory) throws HeadlessException {
        this.factory = factory;

        GridBagLayout gb = new GridBagLayout();

        this.setLayout(gb);
        this.add(factory.getButton(ButtonsType.num7), getConstraint(0,0));
        this.add(factory.getButton(ButtonsType.num8), getConstraint(1, 0));
        this.add(factory.getButton(ButtonsType.num9), getConstraint(2, 0));

        this.add(factory.getButton(ButtonsType.num4), getConstraint(0, 1));
        this.add(factory.getButton(ButtonsType.num5), getConstraint(1, 1));
        this.add(factory.getButton(ButtonsType.num6), getConstraint(2, 1));

        this.add(factory.getButton(ButtonsType.num1), getConstraint(0, 2));
        this.add(factory.getButton(ButtonsType.num2), getConstraint(1, 2));
        this.add(factory.getButton(ButtonsType.num3), getConstraint(2, 2));
        this.add(factory.getButton(ButtonsType.num0), getConstraint(0, 3));
        this.add(factory.getButton(ButtonsType.colon), getConstraint(1, 3));


        GridBagConstraints commandCons = getConstraint(4,0);
        commandCons.ipadx = 5;
        this.add(new JLabel(" "),commandCons);



        //commandCons.ipadx = commandCons.ipadx + 30;
        //commandCons.fill = GridBagConstraints.VERTICAL;

        this.add(factory.getButton(ButtonsType.cmd_CE),getConstraint(5,0));
        this.add(factory.getButton(ButtonsType.cmd_C),getConstraint(6,0));

        this.add(factory.getButton(ButtonsType.changeSgn),getConstraint(5,1));
        this.add(factory.getButton(ButtonsType.add),getConstraint(5,2));
        this.add(factory.getButton(ButtonsType.sub), getConstraint(6,2));
        this.add(factory.getButton(ButtonsType.mult_on_dg),getConstraint(5,3));
        this.add(factory.getButton(ButtonsType.div), getConstraint(6,3));
        this.add(factory.getButton(ButtonsType.div_on_dg),getConstraint(5,4));

        this.add(factory.getButton(ButtonsType.result), getConstraint(7,2,3));



    }

    public void resizeButtonsFonts () {
        Map<ButtonsType,JButton> allButtons = factory.getAllButtons();

        int MaxSize = getMaxSizeForControls(allButtons) - 1;

        for (JButton jb :  allButtons.values()) {
            jb.setFont(jb.getFont().deriveFont((float) MaxSize));
        }
        // b.setFont(b.getFont().deriveFont((float) ii))

    }

    public void disableButtons(ButtonsType[] excludeButtons) {
        Map<ButtonsType,JButton> allButtons = factory.getAllButtons();
        Set<ButtonsType> exclude = new HashSet<>(Arrays.asList(excludeButtons));

        for (Map.Entry<ButtonsType,JButton> btn:  allButtons.entrySet()){
          if (!exclude.contains(btn.getKey()) && btn.getValue().isEnabled()) {
              btn.getValue().setEnabled(false);
          } else {
              btn.getValue().requestFocus();
          }
        }

    }

    public void enableButtons() {
        Map<ButtonsType,JButton> allButtons = factory.getAllButtons();

        for (Map.Entry<ButtonsType,JButton> btn:  allButtons.entrySet()){
            if (!btn.getValue().isEnabled()) {
                btn.getValue().setEnabled(true);
            }
        }
    }


    private int getMaxSizeForControls( Map<ButtonsType,JButton> allButtons) {
        BufferedImage textImage = new BufferedImage(
                100, 100,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = textImage.createGraphics();
        FontRenderContext frc = g.getFontRenderContext();

        int MaxSize = 500;
        for (JButton jb :  allButtons.values()) {
            int S = getMaxFontSizeForControl(jb,frc);
            if (MaxSize > S) {
                MaxSize = S;
            }


             System.out.println("FS: " + S + "   " + jb.getText());
            // System.out.println("FS: " + S); // + "   " + jb.getText());
            System.out.println("MaxSize: " + MaxSize);
        }
        System.out.println("MaxSize: " + MaxSize);
        return MaxSize;
    }

    private int getMaxFontSizeForControl(JButton button, FontRenderContext frc) {
        Rectangle r = button.getBounds();
        Insets m = button.getMargin();
        Insets i = button.getBorder().getBorderInsets(button);
        Rectangle viewableArea = new Rectangle(
                r.width -
                        (m.right + m.left + i.left + i.right),
                r.height -
                        (m.top + m.bottom + i.top + i.bottom)
        );
        Font font = button.getFont();
        int size = 1;
        boolean tooBig = false;
        while (!tooBig) {
            Font f = font.deriveFont((float) size);
            GlyphVector gv = f.createGlyphVector(frc, button.getText());
            Rectangle2D box = gv.getVisualBounds();
            if (box.getHeight() > viewableArea.getHeight()
                    || box.getWidth() > viewableArea.getWidth()) {
                tooBig = true;
                size--;
            }
            size++;
        }
        return size;
    }



    private GridBagConstraints getConstraint(int x, int y) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.weightx = 20;
        gridBagConstraints.weighty = 20;
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        return gridBagConstraints;
    }

    private GridBagConstraints getConstraint(int x, int y, int gh) {
        GridBagConstraints gbc = getConstraint(x, y);
        gbc.gridheight = gh;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        return gbc;
    }



}
