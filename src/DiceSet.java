import java.util.*;

public class DiceSet {
    private final List<Die> dice;

    public DiceSet() {
        dice = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            dice.add(new Die());
        }
    }

    public DiceSet(int n) {
        dice = new ArrayList<>();
        for (int i = 0; i < n; i++) {
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


    public List<List<Integer>> getAllOutcomes(int diceCount) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> current = new ArrayList<>(Collections.nCopies(diceCount, 0));
        recurseOnThatThing(current, 0, results);
        return results;
    }

    private void recurseOnThatThing(List<Integer> current, int position, List<List<Integer>> results) {
        if (position == current.size()) {
            results.add(new ArrayList<>(current));
            return;
        }
        for (int face = 1; face <= 6; face++) {
            current.set(position, face);
            recurseOnThatThing(current, position + 1, results);
        }
    }
}
