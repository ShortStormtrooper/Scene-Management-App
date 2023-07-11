package app.child;

import app.parent.Scene;

import java.io.Serializable;
import java.math.BigInteger;

public class Stadium extends Scene implements Serializable {

    public Stadium(String name, BigInteger phone, int capacity) {
        super(name, phone, capacity);
    }

    @Override
    public int returnCapacity() {

        return listOfMatches.length;
    }

}
