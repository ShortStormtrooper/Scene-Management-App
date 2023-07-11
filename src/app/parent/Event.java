package app.parent;

import app.newDataTypes.DateAndTime;

import java.io.Serializable;

public class Event implements Serializable {

    private String name;
    private DateAndTime dateAndTime;
    private boolean cancelled;

    public Event() {

    }

    public Event(String name, DateAndTime dateAndTime, boolean cancelled) {
        this.name = name;
        this.dateAndTime = dateAndTime;
        this.cancelled = cancelled;
    }

    @Override
    public String toString(){
        return name + ", " + dateAndTime + ", " + cancelled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateAndTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(DateAndTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
