import java.util.*;

public class StandardFarkleScorer extends FarkleScorer {

    public int calculateScore(List<Integer> dice) {
        Map<Integer, Integer> counts = getCounts(dice);

        int specialScore = scoreSpecialPatterns(counts);
        if (specialScore > 0) return specialScore;

        return scoreMultiplesAndSingles(counts);
    }


    //TODO: implement
    public List<Integer> chooseScoringDice(List<Integer> dice) {
        return dice;
    }

    public String getName() {
        return "Standard";
    }

    private int scoreSpecialPatterns(Map<Integer, Integer> counts) {
        if (counts.size() == 6) {
            return 1500; // 1–6 straight
        }

        if (counts.size() == 3 && allValuesEqual(counts)) {
            return 1500; // 3 pairs
        }

        if (counts.size() == 2 && allValuesEqual(counts)) {
            return 2500; // 2 triplets
        }

        if (counts.size() == 2 && counts.containsValue(4) && counts.containsValue(2)) {
            return 1500; // 4 of a kind + pair
        }

        return 0; // No special pattern
    }

    private int scoreMultiplesAndSingles(Map<Integer, Integer> counts) {
        int score = 0;

        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int val = entry.getKey();
            int count = entry.getValue();

            if (count >= 6) {
                score += 3000;
                count -= 6;
            } else if (count == 5) {
                score += 2000;
                count -= 5;
            } else if (count == 4) {
                score += 1000;
                count -= 4;
            } else if (count == 3) {
                score += (val == 1) ? 300 : val * 100;
                count -= 3;
            }

            if (val == 1) score += count * 100;
            if (val == 5) score += count * 50;
        }

        return score;
    }
}
