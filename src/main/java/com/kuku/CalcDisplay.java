package com.kuku;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public class CalcDisplay extends JPanel {
    private DisplyHelp dispHelp;
    private DisplyResult dispResult;
    private DisplyRegister dispRegister;

    public CalcDisplay( ElementFactory factory) {

        dispHelp = factory.getHelpRow();
        dispResult = factory.getDisplayResult();
        dispRegister = factory.getRegistrRow();

        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);

       // gridBagLayout.



        GridBagConstraints hlpCons = new GridBagConstraints();
        hlpCons.gridx = 0;
        hlpCons.gridy = 0;
        hlpCons.anchor = GridBagConstraints.WEST;
        //hlpCons.gridwidth = 50;
        this.add(dispHelp,hlpCons);

//        GridBagConstraints delimiterCons = new GridBagConstraints();
//        hlpCons.gridx = 1;
//        hlpCons.gridy = 0;
//        this.add(new JLabel("             "),delimiterCons);

        //hlpCons.anchor = GridBagConstraints.WEST;
        //hlpCons.ipadx = 70;
        //hlpCons.gridwidth = 50;



        GridBagConstraints registrCons = new GridBagConstraints();
        registrCons.gridx = 2;
        registrCons.gridy = 0;
        registrCons.gridwidth = 2;
       // registrCons.ipadx=40;
        registrCons.fill = GridBagConstraints.BOTH;
        registrCons.anchor = GridBagConstraints.EAST;

        this.add(dispRegister,registrCons);



        GridBagConstraints rsltCons = new GridBagConstraints();
        rsltCons.gridx = 2;
        rsltCons.gridy = 1;
        rsltCons.gridwidth = 2;
//        rsltCons.ipadx=40;
        rsltCons.fill =GridBagConstraints.NONE; //  null; //GridBagConstraints.BOTH;
        rsltCons.anchor = GridBagConstraints.EAST;

        this.add(dispResult,rsltCons);

        GridBagConstraints chooseCons = new GridBagConstraints();
        chooseCons.gridx = 2;
        chooseCons.gridy = 2;
        chooseCons.gridwidth = 2;
//        rsltCons.ipadx=40;
        chooseCons.fill =GridBagConstraints.NONE; //  null; //GridBagConstraints.BOTH;
        chooseCons.anchor = GridBagConstraints.EAST;

        this.setBackground(Color.WHITE);
    }

    public void showStatus(CalcState state) {
        dispHelp.showValue(state);
        dispResult.setChooseRegion(state);
    }

    public void showRegistrAndOper(Time_POJO time, OperationType operation) {
        dispRegister.showValue(time,operation);
    }
//    public void showRegistr(Time_POJO time) {
//
//    }
//
//    public void showOperation(OperationType operationType) {
//
//    }

    public void showDisplay(Time_POJO time) {
        dispResult.showValue(time);
    }


    public void init() {
        dispResult.startInitialProcess();
        dispRegister.startInitialProcess();
        dispHelp.startInitialProcess();

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        dispHelp.endInitialProcess();
        dispResult.endInitialProcess();
        dispRegister.endInitialProcess();

    }

}
