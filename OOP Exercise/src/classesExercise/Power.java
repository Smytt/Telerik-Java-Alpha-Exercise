package classesExercise;

public class Power {
    String name;
    PowerType type;

    public Power(String name, PowerType type) throws Exception {
        setName(name);
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name.length() < 3 || name.length() > 20) {
            throw new Exception("Invalid power name length");
        }
        this.name = name;
    }

    public PowerType getType() {
        return type;
    }

    public void setType(PowerType type) {
        this.type = type;
    }
}
