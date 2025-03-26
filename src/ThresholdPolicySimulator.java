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

    private Double findLowestNum(List<Double> list) {
        Double lowestEntry = list.getFirst();
        for (Double entry : list) {
            if (entry<lowestEntry) { lowestEntry = entry; }
        }
        return lowestEntry;
    }

    private Double findHighestNum(List<Double> list) {
        Double lowestEntry = list.getFirst();
        for (Double entry : list) {
            if (entry>lowestEntry) { lowestEntry = entry; }
        }
        return lowestEntry;
    }

    public void runSimulations() {
        populatePolicies();
        List<Double> avgTurnsList = new ArrayList<>();
        List<Double> avgPointsPerTurnList = new ArrayList<>();

        for (Policy policy : policies) {
            int totalTurns = 0;
            int totalScore = 0;


            //play the games, tallying total turns and total score
            for (int i = 0; i < numGames; i++) {
                Player player = new Player(policy);
                Game game = new Game(player);
                game.play();
                totalTurns += game.getTurnCount();
                totalScore += player.getTotalScore();
            }

            //calculate stats for the policy and add them to respective lists
            double avgTurns = totalTurns / (double) numGames;
            avgTurnsList.add(avgTurns);
            double avgPointsPerTurn = totalScore / (double) totalTurns;
            avgPointsPerTurnList.add(avgPointsPerTurn);
        }

        //policy with the lowest average turns
        double lowestAvgTurns = findLowestNum(avgTurnsList);
        int lowestIndex = avgTurnsList.indexOf(lowestAvgTurns);
        double avgPointsPerTurnForLowestAvgTurns = avgPointsPerTurnList.get(lowestIndex);
        Policy lowestAvgTurnsPolicy = policies.get(lowestIndex);


        //policy with the highest average points/turn
        double highestAvgPointsPerTurn = findHighestNum(avgPointsPerTurnList);
        int highestIndex = avgPointsPerTurnList.indexOf(highestAvgPointsPerTurn);
        double avgTurnsforHighestPointsPerTurn = avgTurnsList.get(highestIndex);
        Policy highestAvgPointsPerTurnPolicy = policies.get(highestIndex);


        //print results
        for (Policy policy : policies) {
            System.out.println(policy.getName());
        }
        System.out.println("Threshold policy with the lowest average turns: " + lowestAvgTurnsPolicy.getName());
        System.out.println("  Avg Turns: " + lowestAvgTurns);
        System.out.println("  Avg Points/Turn: " + avgPointsPerTurnForLowestAvgTurns);

        System.out.println("Threshold policy with the lowest avg points/turn: " + highestAvgPointsPerTurnPolicy.getName());
        System.out.println("  Avg Turns: " + avgTurnsforHighestPointsPerTurn);
        System.out.println("  Avg Points/Turn: " + highestAvgPointsPerTurn);
    }
}
