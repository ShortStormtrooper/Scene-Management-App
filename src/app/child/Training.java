package app.child;

import app.parent.Event;
import app.newDataTypes.DateAndTime;

import java.io.Serializable;

public class Training extends Event implements Serializable {

    private String coachName;


    public Training() {

    }

    public Training(String name) {
        super.setName(name);
    }

    public Training(String name, DateAndTime dateAndTime, boolean cancelled) {
        super(name, dateAndTime, cancelled);
    }

    @Override
    public String toString(){
        return super.toString() + ", " + coachName;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

}
