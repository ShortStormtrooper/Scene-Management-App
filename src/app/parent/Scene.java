package app.parent;

import app.newDataTypes.SportIndustry;
import app.child.Sportsman;
import app.child.Match;
import app.ui.mainFrame;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalTime;

public abstract class Scene implements Serializable {

    public class AddingMatchException extends Exception {
        public AddingMatchException(String s)
        {
            // Call constructor of parent Exception
            super(s);
        }
    }

    public class TypeOfIndustryException extends Exception {
        public TypeOfIndustryException(String s)
        {
            // Call constructor of parent Exception
            super(s);
        }
    }

    private String name;
    private BigInteger phone;
    protected Match[] listOfMatches;

    private int counterMatches = 0;

    private SportIndustry.Industry si;

    public Scene() {

    }

    public Scene(String name, BigInteger phone, int capacity) {
        this(name);
        if(capacity < 0){
            throw new IllegalArgumentException("The capacity of matches is negative");
        }
        this.phone = phone;
        listOfMatches = new Match[capacity];
    }

    public Scene(String name, BigInteger phone) {
        this(name);
        this.phone = phone;
    }

    private Scene(String name) {
        this.name = name;
    }

    @Override
    public String toString(){

        return name + ", " + phone + ", " + si + ", " + listOfMatches.length;
    }

    public void addMatch(Match match) throws AddingMatchException, TypeOfIndustryException {

        Sportsman[] array = match.getListOfAthletes();
        for(int i = 0; i < array.length; i++){
            if(array[i] != null){
                if(!array[i].getSi().equals(si)){
                    mainFrame.log(mainFrame.Severity.WARNING, "The match cannot be added to the scene because the scene accepts matches of the industry " + si + ", to the match " + match.getName() + " has been assigned an athlete with the industry " + array[i].getSi());
                    throw new TypeOfIndustryException("The match cannot be added to the scene because the scene accepts matches of the industry " + si + ", to the match " + match.getName() + " has been assigned an athlete with the industry " + array[i].getSi());
                }
            }

        }

        if (match instanceof Match && match.getDateAndTime().getDateAndTime().toLocalTime().isAfter(LocalTime.of(20, 0))) {
            mainFrame.log(mainFrame.Severity.WARNING, "Tekme ni mogoce dodati, ker se zacne po 20:00");
            throw new AddingMatchException("It is not possible to add a match because it starts after 20:00");
        }

        for(int i = 0; i < listOfMatches.length; i++){
            if(listOfMatches[i] == null){
                listOfMatches[i] = match;
                counterMatches++;
                break;
            }
        }
    }

    public void removeMatches(){
        for(int i = 0; i < listOfMatches.length; i++){
            listOfMatches[i] = null;
        }
        counterMatches = 0;
    }

    public double gardenOccupancy(){
        double counterMatches = 0;
        int size = returnCapacity();
        for(int i = 0; i < listOfMatches.length; i++){
            if(listOfMatches[i] != null){
                counterMatches++;
            }
        }

        return (counterMatches/size)*100;
    }

    public abstract int returnCapacity();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getPhone() {
        return phone;
    }

    public void setPhone(BigInteger phone) {
        this.phone = phone;
    }

    public SportIndustry.Industry getSi() {
        return si;
    }

    public void setSi(SportIndustry.Industry si) {
        this.si = si;
    }

    public Match[] getListOfMatches() {
        return listOfMatches;
    }

    public void setListOfMatches(Match[] listOfMatches) {
        this.listOfMatches = listOfMatches;
    }

    public int getCounterMatches() {
        return counterMatches;
    }

    public void setCounterMatches(int counterMatches) {
        this.counterMatches = counterMatches;
    }
}
