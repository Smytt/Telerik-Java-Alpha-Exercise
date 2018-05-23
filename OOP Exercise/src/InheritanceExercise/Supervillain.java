package InheritanceExercise;

public class Supervillain extends Hero {
    public void destroy() {

    }

    @Override
    public void addSuperpower(String power) {
        System.out.println("overridden through supervillain");
        listOfPowers.add(power);
    }

    @Override
    public void canFly() {
        System.out.println("No.");
    }
}
