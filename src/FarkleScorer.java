import java.util.*;

public class FarkleScorer {
    public static boolean isFarkle(List<Integer> dice) {
        return calculateScore(dice) == 0;
    }

    private static boolean allValuesEqual(Map<Integer, Integer> counts) {
        boolean allEqual = true;
        Integer firstValue = null;

        for (Integer value : counts.values()) {
            if (firstValue == null) {
                firstValue = value; // set reference value
            } else if (!firstValue.equals(value)) {
                allEqual = false;
                break;
            }
        }
        return allEqual;
    }


    public static int calculateScore(List<Integer> dice) {
        // Basic scoring for demo: 1 = 100, 5 = 50
        int score = 0;
        Map<Integer, Integer> counts = new HashMap<>();
        for (int die : dice) {
            counts.put(die, counts.getOrDefault(die, 0) + 1);
        }

        //1-6 straight
        if (counts.size() == 6) {
            score += 1500;
        }

        //3 pairs
        else if (counts.size() == 3 & allValuesEqual(counts)) {
            score += 1500;
        }

        //2 triplets
        else if (counts.size() == 2 & allValuesEqual(counts)) {
            score += 2500;
        }

        //4 of a kind and 2 of a kind
        else if (counts.size() == 2 & counts.containsValue(2) & counts.containsValue(4)) {
            score += 1500;
        }

        else {
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                int val = entry.getKey();
                int count = entry.getValue();

                if (count == 6) {
                    score += 3000;
                    count-= 6;
                }

                if (count == 5) {
                    score += 2000;
                    count -= 5;
                }

                if (count == 4) {
                    score += 1000;
                    count -= 4;
                }

                if (count == 3) {
                    score += (val == 1) ? 300 : val * 100;
                    count -= 3;
                }

                if (val == 1) score += count * 100;
                if (val == 5) score += count * 50;


            }
        }

        return score;
    }
}
