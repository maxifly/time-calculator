package com.kuku.rest;

import com.google.gson.Gson;
import org.junit.Test;

/**
 * Created by Maximus on 24.05.2016.
 */
public class TestJson {

    @Test
    public void testSendGet() throws Exception {
        RestSender restSender = new RestSender();
        restSender.sendGet("https://api.github.com/repos/maxifly/time-calculator/releases/latest");



    }


    @Test
    public void jjj() {

        Gson g = new Gson();

        Person person = g.fromJson("{\"name\": \"John\", \"fam\":\"kuku\"}", Person.class);
        System.out.println(person.name); //John

        System.out.println(g.toJson(person)); // {"name":"John"}


    }




    private class Person {
        public String name;
        public String fam;

        public Person(String name) {
            this.name = name;
        }
    }
}




