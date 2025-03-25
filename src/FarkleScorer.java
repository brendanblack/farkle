import java.util.*;

public abstract class FarkleScorer {

    public abstract int calculateScore(List<Integer> dice);

    public abstract List<Integer> chooseScoringDice(List<Integer> dice);

    public abstract String getName();

    public boolean isFarkle(List<Integer> dice) {
        return calculateScore(dice) == 0;
    }

    protected Map<Integer, Integer> getCounts(List<Integer> dice) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int die : dice) {
            counts.put(die, counts.getOrDefault(die, 0) + 1);
        }
        return counts;
    }

    protected boolean allValuesEqual(Map<Integer, Integer> counts) {
        Integer first = null;
        for (int value : counts.values()) {
            if (first == null) first = value;
            else if (!first.equals(value)) return false;
        }
        return true;
    }
} 