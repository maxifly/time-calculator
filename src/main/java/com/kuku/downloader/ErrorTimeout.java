package com.kuku.downloader;

/**
 * Created by Maximus on 04.06.2016.
 */
public class ErrorTimeout extends Exception {

    public ErrorTimeout() {
        super();
    }
    public ErrorTimeout(String message) {
        super(message);
    }

}
