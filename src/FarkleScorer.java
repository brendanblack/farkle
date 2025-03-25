import java.util.*;

public class FarkleScorer {
    public static boolean isFarkle(List<Integer> dice) {
        return calculateScore(dice) == 0;
    }

    //TODO: Add full scoring functionality

    public static int calculateScore(List<Integer> dice) {
        // Basic scoring for demo: 1 = 100, 5 = 50
        int score = 0;
        Map<Integer, Integer> counts = new HashMap<>();
        for (int die : dice) {
            counts.put(die, counts.getOrDefault(die, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int val = entry.getKey();
            int count = entry.getValue();

            if (count >= 3) {
                score += (val == 1) ? 300 : val * 100;
                count -= 3;
            }

            if (val == 1) score += count * 100;
            if (val == 5) score += count * 50;
        }

        return score;
    }
}
