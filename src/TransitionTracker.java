import java.util.Map;
import java.util.HashMap;

public class TransitionTracker {
    private static class Transition {
        int count = 0;
        int totalReward = 0;
    }

    private final Map<Integer, Map<String, Transition>> transitionData = new HashMap<>();

    public void record(int diceCount, String outcome, int reward) {
        transitionData.putIfAbsent(diceCount, new HashMap<>());
        Map<String, Transition> outcomeMap = transitionData.get(diceCount);
        outcomeMap.putIfAbsent(outcome, new Transition());

        Transition t = outcomeMap.get(outcome);
        t.count++;
        t.totalReward += reward;
    }

    public void printResults() {
        for (int diceCount : transitionData.keySet()) {
            System.out.println("Current Dice Count: " + diceCount);
            Map<String, Transition> outcomes = transitionData.get(diceCount);

            int total = outcomes.values().stream().mapToInt(t -> t.count).sum();

            for (Map.Entry<String, Transition> entry : outcomes.entrySet()) {
                String outcome = entry.getKey();
                Transition t = entry.getValue();

                double probability = (double) t.count / total;
                double expectedReward = (double) t.totalReward / t.count;

                System.out.printf("  -> %s | Prob: %.3f | Avg Reward: %.2f | Count: %d\n",
                        outcome, probability, expectedReward, t.count);
            }

            System.out.println();
        }
    }
    public void merge(TransitionTracker other) {
        for (Map.Entry<Integer, Map<String, Transition>> entry : other.transitionData.entrySet()) {
            int diceCount = entry.getKey();
            Map<String, Transition> otherTransitions = entry.getValue();
    
            this.transitionData.putIfAbsent(diceCount, new HashMap<>());
            Map<String, Transition> thisTransitions = this.transitionData.get(diceCount);
    
            for (Map.Entry<String, Transition> transEntry : otherTransitions.entrySet()) {
                String outcome = transEntry.getKey();
                Transition otherT = transEntry.getValue();
    
                thisTransitions.putIfAbsent(outcome, new Transition());
                Transition thisT = thisTransitions.get(outcome);
    
                thisT.count += otherT.count;
                thisT.totalReward += otherT.totalReward;
            }
        }
    }
    
}
