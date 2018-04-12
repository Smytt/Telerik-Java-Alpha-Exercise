package classesExercise;

public class Main {
    public static void main(String[] args) throws Exception {

        Superhero batman = new Superhero("Batman", "Bruce Wayne", Alignment.GOOD, 95);
        Superhero joker = new Superhero("Joker", "WhoCares", Alignment.EVIL, 70);
        Superhero superman = new Superhero("Superman", "Clark Kent", Alignment.GOOD, 60);

        Power intelligence = new Power("Intelligence", PowerType.INTELLECT);
        Power destruction = new Power("Destruction", PowerType.CHEMICAL);
        Power xRay = new Power("X-Ray", PowerType.TECH);
        Power hacking = new Power("Hacking", PowerType.TECH);
        Power telekinesis = new Power("Telekinesis", PowerType.MAGIC);
        Power kryptonite = new Power("Kryptonite", PowerType.OTHER);

        batman.addPower(intelligence);
        batman.addPower(destruction);

        joker.addPower(hacking);
        joker.addPower(intelligence);
        joker.addPower(kryptonite);

        superman.addPower(xRay);
        superman.addPower(telekinesis);
        superman.addPower(destruction);


        batman.addImmunity(hacking);
        batman.addImmunity(xRay);

        joker.addImmunity(intelligence);
        joker.addImmunity(destruction);

        superman.addImmunity(telekinesis);
        superman.addImmunity(destruction);

        joker.attack(batman, hacking);
        joker.attack(batman, kryptonite);
//        joker.attack(batman, telekinesis);

        System.out.println(batman);
        System.out.println(joker);
        System.out.println(superman);
    }
}
