package com.kuku.rest;

import com.google.gson.Gson;
import com.kuku.rest.model.LatestVersion;
import com.kuku.rest.model.RestResponse;
import org.junit.Test;

/**
 * Created by Maximus on 24.05.2016.
 */
public class TestJson {

    @Test
    public void testSendGet() throws Exception {
        RestSender restSender = new RestSender();
        RestResponse restResponse =  restSender.sendGet(
                "https://api.github.com/repos/maxifly/time-calculator/releases/latest");

        Gson g = new Gson();
        LatestVersion latestVersion =
                g.fromJson(restResponse.getResponseBody().toString(),LatestVersion.class);
        System.out.print(latestVersion);

    }


    @Test
    public void jjj() {

        Gson g = new Gson();

        Person person = g.fromJson("{\"name\": \"John\", \"fam\":\"kuku\", \"kuku\":[{\"kkk\":\"tutu\"}]}", Person.class);
        System.out.println(person.name); //John

        System.out.println(g.toJson(person)); // {"name":"John"}


    }


    private class Kuku {
        public String kkk;
    }

    private class Person {
        public String name;
        public String fam;

        public Kuku[] kuku;

    }
}




