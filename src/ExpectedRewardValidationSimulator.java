import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpectedRewardValidationSimulator extends FarkleSimulator {
    public ExpectedRewardValidationSimulator(List<Policy> policies, int numGames) {
        super(policies, numGames);
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
        int lowestIndex = findIndexOfMax(avgTurnsList);
        System.out.println(policies.get(lowestIndex).getName());
        System.out.println("  Avg Turns: " + avgTurnsList.get(lowestIndex));
        System.out.println("  Avg Points/Turn: " + avgPointsPerTurnList.get(lowestIndex));

        int highestIndex = findIndexOfMax(avgPointsPerTurnList);
        System.out.println(policies.get(highestIndex).getName());
        System.out.println("  Avg Turns: " + avgTurnsList.get(highestIndex));
        System.out.println("  Avg Points/Turn: " + avgPointsPerTurnList.get(highestIndex));
    }
}
