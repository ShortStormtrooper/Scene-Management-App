package app.child;

import app.Competition;
import app.child.Match;
import app.newDataTypes.SportIndustry;
import app.parent.Scene;

import java.io.Serializable;
import java.time.LocalDate;


public class Sportsman implements Competition, Serializable {

    private String name;
    private String lastName;
    private transient int sportsmanNumber;
    private LocalDate DOB;
    private SportIndustry.Industry si;
    private Match[] listOfOwnMatches;

    public Sportsman() {

    }

    public Sportsman(String name, String lastName, int sportsmanNumber, LocalDate DOB){
        this(name, lastName);
        if(sportsmanNumber < 0){
            throw new IllegalArgumentException("The athlete's number is negative");
        }
        this.sportsmanNumber = sportsmanNumber;
        this.DOB = DOB;
        this.listOfOwnMatches = new Match[5];
    }

    private Sportsman(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return name + ", " + lastName + ", " + sportsmanNumber + ", " + DOB + ", " + si;
    }

    public void addMatch(Match match){
        for(int i = 0; i<listOfOwnMatches.length; i++){
            if (listOfOwnMatches[i] == null){
                listOfOwnMatches[i] = match;
                break;
            }
        }
    }
    @Override
    public String checkValidity(Scene scene) {
        Match[] array = scene.getListOfMatches();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].isThereAthlete(lastName)) {
                this.addMatch(array[i]);
                return "The athlete is assigned to the match";
            }
        }
        return "The athlete is not assigned to the match";
    }

    public boolean isThereAthlete(String lastName) {
        return this.lastName.equals(lastName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSportsmanNumber() {
        return sportsmanNumber;
    }

    public void setSportsmanNumber(int sportsmanNumber) {
        this.sportsmanNumber = sportsmanNumber;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public SportIndustry.Industry getSi() {
        return si;
    }

    public void setSi(SportIndustry.Industry si) {
        this.si = si;
    }


}
