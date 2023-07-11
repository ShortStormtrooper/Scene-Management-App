package app.newDataTypes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime implements Serializable {

    private LocalDateTime dateAndTime;

    public DateAndTime() {

    }

    public DateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(dateAndTime);
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

}
