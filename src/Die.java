import java.util.Random;

public class Die {
    private int value;
    private static Random rand = new Random();

    public void roll() {
        value = rand.nextInt(6) + 1;
    }

    public int getValue() {
        return value;
    }
}

