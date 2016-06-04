package com.kuku;

import com.kuku.downloader.Download;
import com.kuku.downloader.Downloader;
import com.kuku.downloader.ErrorTimeout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutionException;

/**
 * Created by Maximus on 04.06.2016.
 */
public class UpdateAction extends AbstractAction {
    private String url;
    private JFrame parentFrame;
    public UpdateAction(String url, JFrame parentFrame ){

        super("Update");
        this.url = url;
        this.parentFrame = parentFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Updare action");

        try {
            File destFile = File.createTempFile("timeclc_",null);

            Download download = new Download(new URL(url), destFile);
            Downloader downloader = new Downloader();
            downloader.startTask(download);

           long timeoutTime = System.currentTimeMillis() + Constants.updateTimeout;
            boolean loadComplete = false;

            while ( timeoutTime > System.currentTimeMillis() ) {
                if (downloader.checkTasks()) {
                    loadComplete = true;
                    break;
                }
                Thread.sleep(500);
            }

            if (!loadComplete) {
                System.out.println("timeout");
                throw new ErrorTimeout("timeout load file ("+ Constants.updateTimeout/1000 +" sec.)");
            } else {
                System.out.println("loaded");
            }


            String currentPath = Constants.getFilePath(App.class.getName());
            System.out.println(currentPath);
            String programPath = currentPath.substring(0,currentPath.indexOf("!"));

            File programFile = new File(programPath);
            Files.move(destFile.toPath(),programFile.toPath(), StandardCopyOption.REPLACE_EXISTING);


            System.out.println("Path \n"+currentPath);
            JOptionPane.showMessageDialog(parentFrame,
                    "Load new version completed.\n" + currentPath);


        } catch (IOException | InterruptedException | ErrorTimeout| ExecutionException e1) {
            e1.printStackTrace();
            String message = e1.getMessage();
            if (message == null) {
                message = e1.getClass().getSimpleName();
            }


            JOptionPane.showMessageDialog(parentFrame,
                    "Error when update: \n\n " + message, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
