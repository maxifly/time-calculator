package com.kuku;

import com.google.gson.Gson;
import com.kuku.rest.RestSender;
import com.kuku.rest.model.Asset;
import com.kuku.rest.model.LatestVersion;
import com.kuku.rest.model.RestResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Maxim.Pantuhin on 18.03.2016.
 */
public class WindowAbout extends JDialog {
// See http://stackoverflow.com/questions/527719/how-to-add-hyperlink-in-jlabel

    private static final String A_VALID_LINK = "http://github.com/maxifly/time-calculator/releases";
    private static final String A_HREF = "<a href=\"";
    private static final String HREF_CLOSED = "\">";
    private static final String HREF_END = "</a>";
    private static final String HTML = "<html>";
    private static final String HTML_END = "</html>";

    private RestSender restSender = new RestSender();
    private Gson gson = new Gson();

    public WindowAbout(ElementFactory factory) {
        super();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        RestResponse restResponse = restSender.sendGet("https://api.github.com/repos/maxifly/time-calculator/releases/latest");
        String latestVersionName = "unknown";
        String currentVersionName = (new Constants()).version();

        float latestVersionNum = 0;
        float currentVersionNum =  Float.parseFloat(currentVersionName);
        LatestVersion latestVersion = null;

        if (restResponse.getResponseCode() == 200) {
           latestVersion = gson.fromJson(restResponse.getResponseBody().toString(), LatestVersion.class);
           latestVersionName = latestVersion.tag_name.substring(1);
           latestVersionNum = Float.parseFloat(latestVersionName);
        }



        String text = "<html> <br><br> " +
                "Timer calculator<br>" +
                "Current version: <b> " +
                currentVersionName +
                "</b><br>" +
                "Latest version: <b> " +
                latestVersionName +
                "</b><br>" +
                "For further information visit: <br> </html>";

        addComponent(new JLabel(text, SwingConstants.CENTER), panel);

        JLabel hrefLabel = new JLabel(A_VALID_LINK, SwingConstants.CENTER);

        addComponent(hrefLabel,panel);

        if (isBrowsingSupported()) {
            makeLinkable(hrefLabel, new LinkMouseListener());
        }


        if (latestVersionNum > currentVersionNum || true) { //TODO Убрать заглушку

            String newVersionUrl = null;
            for (Asset asset : latestVersion.assets) {
                if (asset.name.contains("exe")) {
                    newVersionUrl = asset.browser_download_url;
                }
            }



            Action updateAction = new UpdateAction(newVersionUrl, factory.getMainFrame());
            JButton jbLoad = new JButton(updateAction);
            addComponent(jbLoad,panel);
        }


        this.add(panel);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setSize(factory.getMainFrame().getSize());
        this.setLocation(factory.getMainFrame().getLocation());

    }

    private void addComponent(JComponent component, Container panel) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(component);
    }

    private static void makeLinkable(JLabel c, MouseListener ml) {
        assert ml != null;
        c.setText(htmlIfy(linkIfy(c.getText())));
        c.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        c.addMouseListener(ml);
    }

    private static boolean isBrowsingSupported() {
        if (!Desktop.isDesktopSupported()) {
            return false;
        }
        boolean result = false;
        Desktop desktop = java.awt.Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.BROWSE)) {
            result = true;
        }
        return result;

    }



    private static String getPlainLink(String s) {
        return s.substring(s.indexOf(A_HREF) + A_HREF.length(), s.indexOf(HREF_CLOSED));
    }

    //WARNING
//This method requires that s is a plain string that requires
//no further escaping
    private static String linkIfy(String s) {
        return A_HREF.concat(s).concat(HREF_CLOSED).concat(s).concat(HREF_END);
    }

    //WARNING
//This method requires that s is a plain string that requires
//no further escaping
    private static String htmlIfy(String s) {
        return HTML.concat(s).concat(HTML_END);
    }



    private static class LinkMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            JLabel l = (JLabel) evt.getSource();
            try {
                URI uri = new java.net.URI(WindowAbout.getPlainLink(l.getText()));
                (new LinkRunner(uri)).execute();
            } catch (URISyntaxException use) {
                throw new AssertionError(use + ": " + l.getText()); //NOI18N
            }
        }
    }

    private static class LinkRunner extends SwingWorker<Void, Void> {

        private final URI uri;

        private LinkRunner(URI u) {
            if (u == null) {
                throw new NullPointerException();
            }
            uri = u;
        }

        @Override
        protected Void doInBackground() throws Exception {
            Desktop desktop = java.awt.Desktop.getDesktop();
            desktop.browse(uri);
            return null;
        }

        @Override
        protected void done() {
            try {
                get();
            } catch (ExecutionException ee) {
                handleException(uri, ee);
            } catch (InterruptedException ie) {
                handleException(uri, ie);
            }
        }

        private static void handleException(URI u, Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry, a problem occurred while trying to open this link in your system's standard browser.", "A problem occured", JOptionPane.ERROR_MESSAGE);
        }
    }


}
