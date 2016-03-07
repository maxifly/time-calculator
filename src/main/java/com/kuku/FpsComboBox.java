package com.kuku;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maximus on 06.03.2016.
 */
public class FpsComboBox extends JComboBox<String> {

    private Map<String, Integer> fpsMap = new HashMap<>();
    private Map<Integer, String> fpsMapToShow = new HashMap<>();


    public FpsComboBox() {
        super();

        String[] show = {"none", "24", "25", "30", "50", "60", "100"};
        Integer[] values = {1, 24, 25, 30, 50, 60, 100};

        for (int i = 0; i < values.length; i++) {
            this.fpsMap.put(show[i], values[i]);
            this.fpsMapToShow.put(values[i], show[i]);
            this.addItem(show[i]);
        }

        AppState appState = AppState.load();
        try {
            if (appState != null) {
                this.setSelectedItem(fpsMapToShow.get(appState.getFps()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Integer getSelectedFPS() {
        String SelectedItem = (String) this.getSelectedItem();
        return this.fpsMap.get(SelectedItem);
    }


}
