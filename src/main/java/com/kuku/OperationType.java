package com.kuku;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public enum OperationType {
    add,
    sub,
    div,
    div_on_dig,
    mult_on_dig;

    @Override
    public String toString() {
        switch (this) {
            case add:
                return "+";
            case sub:
                return "-";
            case div:
                return "/";
            case div_on_dig:
                return "/dg";
            case mult_on_dig:
                return "*";
        }
        return super.toString();
    }


}
