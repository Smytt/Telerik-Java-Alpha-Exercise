package InheritanceExercise;

import java.util.ArrayList;

public class Hero extends Person {

    protected String secretIdentity;
    protected StringBuilder backStory;
    protected boolean isGood;
    protected ArrayList<String> listOfPowers;


    public String getSecretIdentity() {
        return secretIdentity;
    }

    public void setSecretIdentity(String secretIdentity) {
        this.secretIdentity = secretIdentity;
    }

    public StringBuilder getBackStory() {
        return backStory;
    }

    public void setBackStory(StringBuilder backStory) {
        this.backStory = backStory;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    public ArrayList<String> getListOfPowers() {
        return listOfPowers;
    }

    public void setListOfPowers(ArrayList<String> listOfPowers) {
        this.listOfPowers = listOfPowers;
    }

    public void addSuperpower (String power) {
        listOfPowers.add(power);
    }

    public void canFly() {
        System.out.println("Yes!");
    }
}
