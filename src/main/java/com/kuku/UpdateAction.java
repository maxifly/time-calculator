package com.kuku;

import com.google.gson.Gson;
import com.kuku.downloader.Download;
import com.kuku.downloader.Downloader;
import com.kuku.downloader.ErrorLoadUpdater;
import com.kuku.downloader.ErrorTimeout;
import com.kuku.rest.RestSender;
import com.kuku.rest.model.Asset;
import com.kuku.rest.model.LatestVersion;
import com.kuku.rest.model.RestResponse;

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

    public UpdateAction(String url, JFrame parentFrame) {

        super("Update");
        this.url = url;
        this.parentFrame = parentFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Updare action");

        try {

            String updaterUrl = getUpdaterUrl();

            if (updaterUrl == null) {
                throw new ErrorLoadUpdater("Can not get updater url");
            }


            Path destDir = Files.createTempDirectory("calc_");
            File destUpdater = new File(destDir.toString(), "updater.jar");
            File destFile = File.createTempFile("timeclc_", null, destDir.toFile());

            Download download = new Download(new URL(url), destFile);
            Download download1 = new Download(new URL(updaterUrl), destUpdater);

            Downloader downloader = new Downloader();
            downloader.startTask(download);
            downloader.startTask(download1);

            long timeoutTime = System.currentTimeMillis() + Constants.updateTimeout;
            boolean loadComplete = false;

            while (timeoutTime > System.currentTimeMillis()) {
                if (downloader.checkTasks()) {
                    loadComplete = true;
                    break;
                }
                Thread.sleep(500);
            }

            if (!loadComplete) {
                System.out.println("timeout");
                throw new ErrorTimeout("timeout load file (" + Constants.updateTimeout / 1000 + " sec.)");
            } else {
                System.out.println("loaded");
            }


            String currentPath = Constants.getFilePath(App.class.getName());
            System.out.println("CurrentPath: " + currentPath);
            String programPath = currentPath.substring(0, currentPath.indexOf("!"));


            File oldFile = new File(programPath);

            String execute_arg = null;
            if (oldFile.toString().contains(".exe")) {
                execute_arg = oldFile.toString();
            } else {
                execute_arg = "\"" + "java -jar " + oldFile.toString() + "\"";
            }


            String execute = "java -jar " +
                    destUpdater
                    + " --old " + oldFile.toString()
                    + " --new " + destFile.toString()
                    + " --execute " + execute_arg;
            System.out.println(execute);


//            File programFile = new File(programPath);
//            Files.move(destFile.toPath(),programFile.toPath(), StandardCopyOption.REPLACE_EXISTING);


            System.out.println("Path \n" + currentPath);
            JOptionPane.showMessageDialog(parentFrame,
                    "Load new version completed.\n " + currentPath +
                            "\n\nNow the program will be restarted.");

            Runtime runtime = Runtime.getRuntime();
            runtime.exec(execute);
            System.exit(0);


        } catch (IOException | InterruptedException | ErrorTimeout | ErrorLoadUpdater | ExecutionException e1) {
            e1.printStackTrace();
            String message = e1.getMessage();
            if (message == null) {
                message = e1.getClass().getSimpleName();
            }


            JOptionPane.showMessageDialog(parentFrame,
                    "Error when update: \n\n " + message, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private String getUpdaterUrl() {
        RestSender restSender = new RestSender();
        RestResponse restResponse = restSender.sendGet("https://api.github.com/repos/maxifly/updater/releases/latest");

        LatestVersion latestVersion = null;
        Gson gson = new Gson();

        if (restResponse.getResponseCode() == 200) {
            latestVersion = gson.fromJson(restResponse.getResponseBody().toString(), LatestVersion.class);

            String newVersionUrl = null;
            for (Asset asset : latestVersion.assets) {
                if (asset.name.contains("jar")) {
                    newVersionUrl = asset.browser_download_url;
                }
            }

            return newVersionUrl;
        }

        return null;
    }


}
