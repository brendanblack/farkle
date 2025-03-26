import java.util.*;

public class ThresholdPolicySimulator extends FarkleSimulator {
    final static int NUM_POLICIES = 100;
    final static int LOWER_THRESHOLD_LIMIT = 400;
    final static int UPPER_THRESHOLD_LIMIT = 4000;

    public ThresholdPolicySimulator(List<Policy> policies, int numGames) {
        super(policies, numGames);
    }

    private List<Integer> getThresholdNumbers() {
        List<Integer> thresholdsList = new ArrayList<>();
        int thresholdRange = UPPER_THRESHOLD_LIMIT - LOWER_THRESHOLD_LIMIT;

        for (int i = 0; i < NUM_POLICIES; i++) {
            int threshold = LOWER_THRESHOLD_LIMIT + (thresholdRange * i / (NUM_POLICIES - 1));
            thresholdsList.add(threshold);
        }

        return thresholdsList;
    }

    private void populatePolicies() {
        List<Integer> thresholdNumbers = getThresholdNumbers();
        for (Integer i : thresholdNumbers) {
            policies.add(new ThresholdPolicy(i));
        }
    }

    private double[] simulatePolicy(Policy policy) {
        int totalTurns = 0;
        int totalScore = 0;

        for (int i = 0; i < numGames; i++) {
            Player player = new Player(policy);
            Game game = new Game(player);
            game.play();
            totalTurns += game.getTurnCount();
            totalScore += player.getTotalScore();
        }

        double avgTurns = totalTurns / (double) numGames;
        double avgPointsPerTurn = totalScore / (double) totalTurns;
        return new double[] { avgTurns, avgPointsPerTurn };
    }


    public void runSimulations() {
        populatePolicies();
        List<Double> avgTurnsList = new ArrayList<>();
        List<Double> avgPointsPerTurnList = new ArrayList<>();

        for (Policy policy : policies) {
            double[] results = simulatePolicy(policy);
            avgTurnsList.add(results[0]);
            avgPointsPerTurnList.add(results[1]);
        }

        printPolicyResults(policies, avgTurnsList, avgPointsPerTurnList);
    }

    private int findIndexOfMin(List<Double> list) {
        double min = Collections.min(list);
        return list.indexOf(min);
    }

    private int findIndexOfMax(List<Double> list) {
        double max = Collections.max(list);
        return list.indexOf(max);
    }

    private void printPolicyResults(List<Policy> policies, List<Double> avgTurnsList, List<Double> avgPointsPerTurnList) {
        for (Policy policy : policies) {
            System.out.println(policy.getName());
        }

        int lowestIndex = findIndexOfMin(avgTurnsList);
        System.out.println("Threshold policy with the lowest average turns: " + policies.get(lowestIndex).getName());
        System.out.println("  Avg Turns: " + avgTurnsList.get(lowestIndex));
        System.out.println("  Avg Points/Turn: " + avgPointsPerTurnList.get(lowestIndex));

        int highestIndex = findIndexOfMax(avgPointsPerTurnList);
        System.out.println("Threshold policy with the highest avg points/turn: " + policies.get(highestIndex).getName());
        System.out.println("  Avg Turns: " + avgTurnsList.get(highestIndex));
        System.out.println("  Avg Points/Turn: " + avgPointsPerTurnList.get(highestIndex));
    }
}
