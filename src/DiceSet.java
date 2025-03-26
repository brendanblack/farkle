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
        for (Integer val : diceToRemove) {
            for (int i = 0; i < dice.size(); i++) {
                if (dice.get(i).getValue() == val) {
                    dice.remove(i);
                    break;
                }
            }
        }
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
