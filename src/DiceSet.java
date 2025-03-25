import java.util.*;

public class DiceSet {
    private List<Die> dice;

    public DiceSet() {
        dice = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            dice.add(new Die());
        }
    }

    public void setAside(List<Integer> diceToRemove) {
        dice.removeIf(die -> diceToRemove.contains(die.getValue()));
    }

    public List<Integer> roll() {
        for (Die die : dice) {
            die.roll();
        }
        return getValues();
    }

    public List<Integer> getValues() {
        List<Integer> values = new ArrayList<>();
        for (Die die : dice) {
            values.add(die.getValue());
        }
        return values;
    }
}
