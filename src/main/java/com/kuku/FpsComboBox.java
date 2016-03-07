package com.kuku;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maximus on 06.03.2016.
 */
public class FpsComboBox extends JComboBox<String> {

    private Map<String, Integer> fpsMap = new HashMap<>();


    public FpsComboBox() {
        super();

        this.fpsMap.put("none", 1);
        this.addItem("none");
        this.fpsMap.put("24", 24);
        this.addItem("24");
        this.fpsMap.put("25", 25);
        this.addItem("25");
        this.fpsMap.put("30", 30);
        this.addItem("30");
        this.fpsMap.put("50", 50);
        this.addItem("50");
        this.fpsMap.put("60", 60);
        this.addItem("60");
        this.fpsMap.put("100", 100);
        this.addItem("100");
    }

    public Integer getSelectedFPS() {
        String SelectedItem = (String) this.getSelectedItem();
        return this.fpsMap.get(SelectedItem);

    }

}
