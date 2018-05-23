package classesExercise;

import java.util.ArrayList;

public class Superhero {
    private String name;
    private String identity;
    private Alignment alignment;
    private int lifePoints;
    private ArrayList<Power> immunities;
    private ArrayList<Power> powers;

    public Superhero(String name, String identity, Alignment alignment, int lifePoints) throws Exception {
        setName(name);
        setIdentity(identity);
        setAlignment(alignment);
        setLifePoints(lifePoints);
        setImmunities();
        setPowers();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name.length() < 3 || name.length() > 60) {
            throw new Exception("Invalid name length");
        }
        this.name = name;
    }

    public String getIdentity() {
        return "The identity is a secret!";
    }

    public void setIdentity(String identity) throws Exception {
        if (identity.length() < 3 || identity.length() > 60) {
            throw new Exception("Invalid identity length");
        }
        this.identity = identity;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) throws Exception {
        if (lifePoints < 0 || lifePoints > 100) {
            throw new Exception("Invalid life points quantity");
        }
        this.lifePoints = lifePoints;
    }

    public ArrayList<Power> getImmunities() {
        return immunities;
    }

    public void setImmunities() {
        this.immunities = new ArrayList<>();
    }

    public ArrayList<Power> getPowers() {
        return powers;
    }

    public void setPowers() {
        this.powers = new ArrayList<>();
    }

    public void addPower(Power power) {
        powers.add(power);
    }

    public void addImmunity(Power power) {
        immunities.add(power);
    }

    public void attack(Superhero hero, Power power) throws Exception {
        if (!powers.contains(power)) {
            throw new Exception("No such power!");
        }
        if (!hero.immunities.contains(power)) {
            hero.lifePoints -= 10;
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(name)
                .append(" - ")
                .append(lifePoints);

        return String.valueOf(output);
    }
}
