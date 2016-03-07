package com.kuku;

import java.awt.*;
import java.io.*;

/**
 * Created by Maximus on 07.03.2016.
 */
public class AppState implements Serializable {
    private Integer fps;
    private Dimension dimension;
    private Point point;



    public void setFps(Integer fps) {
        this.fps = fps;
    }

    public static void save(AppState appState) {
        ObjectOutputStream oos = null;
        try {
            String userDir = System.getProperty("user.dir");
            FileOutputStream fout = new FileOutputStream(userDir + System.getProperty("file.separator") + "app_state.txt");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(appState);
            oos.close();
            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static AppState load() {
        AppState appState = null;
        ObjectInputStream objectinputstream = null;
        try {
            String userDir = System.getProperty("user.dir");

            FileInputStream streamIn = new FileInputStream(userDir + System.getProperty("file.separator") + "app_state.txt");
            objectinputstream = new ObjectInputStream(streamIn);
            appState = (AppState) objectinputstream.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectinputstream != null) {
                try {
                    objectinputstream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return appState;
    }


    public Integer getFps() {
        return fps;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
