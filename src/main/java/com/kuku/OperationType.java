package com.kuku;

/**
 * Created by Maxim.Pantuhin on 08.10.2014.
 */
public enum OperationType {
    add,
    sub;

    @Override
    public String toString() {
        switch (this) {
            case add:
                return "+";
            case sub:
                return "-";
        }
        return super.toString();
    }


}
