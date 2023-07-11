package app.child;

import app.Competition;
import app.parent.Event;
import app.parent.Scene;
import app.newDataTypes.DateAndTime;
import app.ui.mainFrame;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

public class Match extends Event implements Competition, Serializable {

    public class AddingSportsmanException extends Exception {
        public AddingSportsmanException(String s)
        {
            // Call constructor of parent Exception
            super(s);
        }
    }

    private Sportsman[] listOfAthletes = new Sportsman[10];
    private int counterSportsman = 0;

    public Match() {

    }

    public Match(String name, DateAndTime dateAndTime, boolean cancelled) {
        super(name, dateAndTime, cancelled);

    }

    @Override
    public String toString(){
        return super.toString() + ", Athletes: \n" + Arrays.toString(listOfAthletes);
    }
    /**
     * Function to add sportsman
     *
     * @param sportsman sportsman that needs to be added
     */
    public void addSportsmanToMatch(Sportsman sportsman) throws AddingSportsmanException {

        if(!this.canCompete(sportsman)){
            mainFrame.log(mainFrame.Severity.ERROR, "The athlete cannot play");
            throw new AddingSportsmanException("The athlete cannot play");
        }
        for(int i = 0; i<listOfAthletes.length; i++){
            if (listOfAthletes[i] == null){
                listOfAthletes[i] = sportsman;
                counterSportsman++;
                break;
            }
        }
    }
    /**
     * Function that deletes sportsman
     * @param sportsman sportsman that needs to be removed
     */
    public void removeSportsmanFromMatch(Sportsman sportsman){

        for(int i = 0; i<listOfAthletes.length; i++){
            if (listOfAthletes[i] == sportsman){
                listOfAthletes[i] = null;
                counterSportsman--;
                break;
            }
        }
    }
    /**
     * Function that deletes sportsman by number
     *
     * @param number number of sportsman that needs to be removed
     * @return true if sportsman is remove, false if it is not
     */
    public boolean removeSportsmanFromMatch(int number){
        if(number < 0){
            throw new IllegalArgumentException("The athlete's number is negative");
        }
        for(int i = 0; i< listOfAthletes.length; i++){
            if(listOfAthletes[i] != null){
                if(listOfAthletes[i].getSportsmanNumber() == number){
                    listOfAthletes[i] = null;
                    counterSportsman--;
                    return true;
                }
            }

        }
        return false;
    }
    /**
     * Function that checks if sportsman exist
     * @param lastName lastName of sportsman
     * @return true if sportsman exists, false if it does not
     */
    public boolean isThereAthlete(String lastName){

        for(int i = 0; i < listOfAthletes.length; i++){
            if(listOfAthletes[i] != null){
                if(listOfAthletes[i].getLastName().equals(lastName)){
                    return true;
                }
            }

        }

        return false;
    }

    public int returnNumberOfAthletesAtMatch(){
        return counterSportsman;
    }

    public boolean canCompete(Sportsman sportsman){
        LocalDate datum = sportsman.getDOB();
        LocalDate now = LocalDate.now();

        int year = Period.between(datum, now).getYears();

        return year >= 14 && year <= 18;
    }

    @Override
    public String checkValidity(Scene scene) {
        Match[] array = scene.getListOfMatches();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i] == this) {
                return "Match can be played";
            }
        }
        return "The match has not been added to the scene";
    }

    public Sportsman[] getListOfAthletes() {
        return listOfAthletes;
    }

    public void setListOfAthletes(Sportsman[] listOfAthletes) {
        this.listOfAthletes = listOfAthletes;
    }


}
