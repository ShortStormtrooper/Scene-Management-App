package app.child;

import app.parent.Scene;

import java.io.Serializable;
import java.math.BigInteger;

public class Hall extends Scene implements Serializable {

    private int numberOfAdditionalHalls;

    public Hall() {
    }

    public Hall(String name, BigInteger phone, int capacity, int numberOfAdditionalHalls) {

        super(name, phone, capacity);
        if(capacity < 0){
            throw new IllegalArgumentException("The capacity of matches is negative");
        }else if(numberOfAdditionalHalls < 0){
            throw new IllegalArgumentException("The number of additional halls is negative");
        }
        this.numberOfAdditionalHalls = numberOfAdditionalHalls;
    }

    @Override
    public String toString(){

        return super.toString() + ", " + numberOfAdditionalHalls;
    }

    @Override
    public int returnCapacity() {
        if(numberOfAdditionalHalls > 1){
            return listOfMatches.length + numberOfAdditionalHalls;
        }

        return listOfMatches.length;
    }

    public int getNumberOfAdditionalHalls() {
        return numberOfAdditionalHalls;
    }

    public void setNumberOfAdditionalHalls(int numberOfAdditionalHalls) {
        this.numberOfAdditionalHalls = numberOfAdditionalHalls;
    }

}
