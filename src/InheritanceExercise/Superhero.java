package InheritanceExercise;

public class Superhero extends Hero {

    public void saveWorld() {

    }

    @Override
    public void addSuperpower(String power) {
        System.out.println("overridden through superhero");
        listOfPowers.add(power);
    }

    @Override
    public void canFly() {
        System.out.println("Yes! Superheroes fly!");
    }
}
