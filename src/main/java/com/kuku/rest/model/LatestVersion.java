package com.kuku.rest.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Maximus on 26.05.2016.
 */
public class LatestVersion {
    public String url;
    public String tag_name;
    public List<Asset> assets = new ArrayList<>();
}
