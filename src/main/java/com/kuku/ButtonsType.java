package com.kuku;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public enum ButtonsType {
    num0("0"),
    num1("1"),
    num2("2"),
    num3("3"),
    num4("4"),
    num5("5"),
    num6("6"),
    num7("7"),
    num8("8"),
    num9("9"),
    colon(":"),
    add("+"),
    sub("-"),
    result("="),
    changeSgn("\u00B1"),
    cmd_C("C"),
    cmd_CE("CE");

    private String text;

    private ButtonsType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
